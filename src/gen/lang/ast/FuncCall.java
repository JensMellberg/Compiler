/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.2 */
package lang.ast;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Map;
import java.lang.RuntimeException;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
/**
 * @ast node
 * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/lang.ast:13
 * @astdecl FuncCall : Expr ::= IdUse Expr*;
 * @production FuncCall : {@link Expr} ::= <span class="component">{@link IdUse}</span> <span class="component">{@link Expr}*</span>;

 */
public class FuncCall extends Expr implements Cloneable {
  /**
   * @aspect CodeGen
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:291
   */
  public void genCode(PrintStream out) {
    for(int i = getNumExpr() - 1; i >= 0; i--) {
      getExpr(i).genCode(out);
      out.println("        pushq %rax");
    }
    out.println("        call " + getIdUse().getID());
    out.println("        addq $"+getNumExpr()*8+", %rsp");
  }
  /**
   * @aspect ActivationRecord
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/ActivationRecord.jrag:41
   */
  public int eval(ActivationRecord actrec) {
    if (getIdUse().getID().equals("print")) {
      System.out.println(getExpr(0).eval(actrec));
      return 0;
    } else if(getIdUse().getID().equals("read")){
      try {
        return ActivationRecord.scan.nextInt();
      } catch(Exception e) {
        return 0;
      }
    }
    //TODO kolla listan
    ActivationRecord newActrec = new ActivationRecord();
    FuncDecl call = getIdUse().decl().function();
    for (int i = 0; i < getExprs().getNumChild(); i++) {
      newActrec.put(call.getIdParam(i).getIdDecl().getUniqueID(), getExpr(i).eval(actrec));
    }
    return call.eval(newActrec);

  }
  /**
   * @declaredat ASTNode:1
   */
  public FuncCall() {
    super();
  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @declaredat ASTNode:10
   */
  public void init$Children() {
    children = new ASTNode[2];
    setChild(new List(), 1);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"IdUse", "Expr"},
    type = {"IdUse", "List<Expr>"},
    kind = {"Child", "List"}
  )
  public FuncCall(IdUse p0, List<Expr> p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:24
   */
  protected int numChildren() {
    return 2;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:28
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    argNbrMismatch_reset();
    enclosingFunction_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:34
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public FuncCall clone() throws CloneNotSupportedException {
    FuncCall node = (FuncCall) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:43
   */
  public FuncCall copy() {
    try {
      FuncCall node = (FuncCall) clone();
      node.parent = null;
      if (children != null) {
        node.children = (ASTNode[]) children.clone();
      }
      return node;
    } catch (CloneNotSupportedException e) {
      throw new Error("Error: clone not supported for " + getClass().getName());
    }
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:62
   */
  @Deprecated
  public FuncCall fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:72
   */
  public FuncCall treeCopyNoTransform() {
    FuncCall tree = (FuncCall) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) children[i];
        if (child != null) {
          child = child.treeCopyNoTransform();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:92
   */
  public FuncCall treeCopy() {
    FuncCall tree = (FuncCall) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) getChild(i);
        if (child != null) {
          child = child.treeCopy();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:106
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the IdUse child.
   * @param node The new node to replace the IdUse child.
   * @apilevel high-level
   */
  public void setIdUse(IdUse node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the IdUse child.
   * @return The current node used as the IdUse child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="IdUse")
  public IdUse getIdUse() {
    return (IdUse) getChild(0);
  }
  /**
   * Retrieves the IdUse child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the IdUse child.
   * @apilevel low-level
   */
  public IdUse getIdUseNoTransform() {
    return (IdUse) getChildNoTransform(0);
  }
  /**
   * Replaces the Expr list.
   * @param list The new list node to be used as the Expr list.
   * @apilevel high-level
   */
  public void setExprList(List<Expr> list) {
    setChild(list, 1);
  }
  /**
   * Retrieves the number of children in the Expr list.
   * @return Number of children in the Expr list.
   * @apilevel high-level
   */
  public int getNumExpr() {
    return getExprList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Expr list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Expr list.
   * @apilevel low-level
   */
  public int getNumExprNoTransform() {
    return getExprListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Expr list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Expr list.
   * @apilevel high-level
   */
  public Expr getExpr(int i) {
    return (Expr) getExprList().getChild(i);
  }
  /**
   * Check whether the Expr list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasExpr() {
    return getExprList().getNumChild() != 0;
  }
  /**
   * Append an element to the Expr list.
   * @param node The element to append to the Expr list.
   * @apilevel high-level
   */
  public void addExpr(Expr node) {
    List<Expr> list = (parent == null) ? getExprListNoTransform() : getExprList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addExprNoTransform(Expr node) {
    List<Expr> list = getExprListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the Expr list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setExpr(Expr node, int i) {
    List<Expr> list = getExprList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the Expr list.
   * @return The node representing the Expr list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Expr")
  public List<Expr> getExprList() {
    List<Expr> list = (List<Expr>) getChild(1);
    return list;
  }
  /**
   * Retrieves the Expr list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Expr list.
   * @apilevel low-level
   */
  public List<Expr> getExprListNoTransform() {
    return (List<Expr>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the Expr list without
   * triggering rewrites.
   */
  public Expr getExprNoTransform(int i) {
    return (Expr) getExprListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Expr list.
   * @return The node representing the Expr list.
   * @apilevel high-level
   */
  public List<Expr> getExprs() {
    return getExprList();
  }
  /**
   * Retrieves the Expr list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Expr list.
   * @apilevel low-level
   */
  public List<Expr> getExprsNoTransform() {
    return getExprListNoTransform();
  }
/** @apilevel internal */
protected boolean argNbrMismatch_visited = false;
  /** @apilevel internal */
  private void argNbrMismatch_reset() {
    argNbrMismatch_computed = false;
    argNbrMismatch_visited = false;
  }
  /** @apilevel internal */
  protected boolean argNbrMismatch_computed = false;

  /** @apilevel internal */
  protected boolean argNbrMismatch_value;

  /**
   * @attribute syn
   * @aspect NameAnalysis
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:97
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:97")
  public boolean argNbrMismatch() {
    ASTState state = state();
    if (argNbrMismatch_computed) {
      return argNbrMismatch_value;
    }
    if (argNbrMismatch_visited) {
      throw new RuntimeException("Circular definition of attribute FuncCall.argNbrMismatch().");
    }
    argNbrMismatch_visited = true;
    state().enterLazyAttribute();
    argNbrMismatch_value = argNbrMismatch_compute();
    argNbrMismatch_computed = true;
    state().leaveLazyAttribute();
    argNbrMismatch_visited = false;
    return argNbrMismatch_value;
  }
  /** @apilevel internal */
  private boolean argNbrMismatch_compute() {
  		int args = getNumExpr();
  		FuncDecl func = getIdUse().decl().function();
  		if (func == null)
  			return false;
  		int params = func.getNumIdParam();
  		return args != params;
  	}
  /**
   * @attribute inh
   * @aspect FunctionCalls
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/FunctionCalls.jrag:33
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="FunctionCalls", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/FunctionCalls.jrag:33")
  public FuncDecl enclosingFunction() {
    ASTState state = state();
    if (enclosingFunction_computed) {
      return enclosingFunction_value;
    }
    if (enclosingFunction_visited) {
      throw new RuntimeException("Circular definition of attribute FuncCall.enclosingFunction().");
    }
    enclosingFunction_visited = true;
    state().enterLazyAttribute();
    enclosingFunction_value = getParent().Define_enclosingFunction(this, null);
    enclosingFunction_computed = true;
    state().leaveLazyAttribute();
    enclosingFunction_visited = false;
    return enclosingFunction_value;
  }
/** @apilevel internal */
protected boolean enclosingFunction_visited = false;
  /** @apilevel internal */
  private void enclosingFunction_reset() {
    enclosingFunction_computed = false;
    
    enclosingFunction_value = null;
    enclosingFunction_visited = false;
  }
  /** @apilevel internal */
  protected boolean enclosingFunction_computed = false;

  /** @apilevel internal */
  protected FuncDecl enclosingFunction_value;

  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:91
   * @apilevel internal
   */
  public boolean Define_isFunction(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getIdUseNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:84
      return true;
    }
    else {
      return getParent().Define_isFunction(this, _callerNode);
    }
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:91
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isFunction
   */
  protected boolean canDefine_isFunction(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /** @apilevel internal */
  protected void collect_contributors_FuncDecl_functionCalls(Program _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/FunctionCalls.jrag:29
    if (1 == 1) {
      {
        FuncDecl target = (FuncDecl) (enclosingFunction());
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_FuncDecl_functionCalls(_root, _map);
  }
  /** @apilevel internal */
  protected void collect_contributors_Program_errors(Program _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/Errors.jrag:53
    if (argNbrMismatch()) {
      {
        Program target = (Program) (program());
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_Program_errors(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_FuncDecl_functionCalls(Set<FuncDeclComp> collection) {
    super.contributeTo_FuncDecl_functionCalls(collection);
    if (1 == 1) {
      collection.add(new FuncDeclComp(getIdUse().decl().function()));
    }
  }
  /** @apilevel internal */
  protected void contributeTo_Program_errors(Set<ErrorMessage> collection) {
    super.contributeTo_Program_errors(collection);
    if (argNbrMismatch()) {
      collection.add(error("The number of arguments given to '" + getIdUse().getID() + "' does not match its parameters"));
    }
  }
}
