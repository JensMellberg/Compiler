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
 * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/lang.ast:35
 * @astdecl Block : ASTNode ::= Stmt*;
 * @production Block : {@link ASTNode} ::= <span class="component">{@link Stmt}*</span>;

 */
public class Block extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect CodeGen
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:146
   */
  public void genCode(PrintStream out) {
    for (Stmt s : getStmts()) {
      s.genCode(out);
      }
   }
  /**
   * @aspect ActivationRecord
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/ActivationRecord.jrag:24
   */
  public void eval(ActivationRecord actrec) throws ReturnException {
    for(Stmt s : getStmts()){
      s.eval(actrec);
      }
  }
  /**
   * @declaredat ASTNode:1
   */
  public Block() {
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
    children = new ASTNode[1];
    setChild(new List(), 0);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Stmt"},
    type = {"List<Stmt>"},
    kind = {"List"}
  )
  public Block(List<Stmt> p0) {
    setChild(p0, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:23
   */
  protected int numChildren() {
    return 1;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:27
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    countDecls_reset();
    localLookup_String_int_reset();
    getPrefix_reset();
    getLabel_reset();
    lookup_String_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:36
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:40
   */
  public Block clone() throws CloneNotSupportedException {
    Block node = (Block) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:45
   */
  public Block copy() {
    try {
      Block node = (Block) clone();
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
   * @declaredat ASTNode:64
   */
  @Deprecated
  public Block fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:74
   */
  public Block treeCopyNoTransform() {
    Block tree = (Block) copy();
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
   * @declaredat ASTNode:94
   */
  public Block treeCopy() {
    Block tree = (Block) copy();
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
   * @declaredat ASTNode:108
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the Stmt list.
   * @param list The new list node to be used as the Stmt list.
   * @apilevel high-level
   */
  public void setStmtList(List<Stmt> list) {
    setChild(list, 0);
  }
  /**
   * Retrieves the number of children in the Stmt list.
   * @return Number of children in the Stmt list.
   * @apilevel high-level
   */
  public int getNumStmt() {
    return getStmtList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Stmt list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the Stmt list.
   * @apilevel low-level
   */
  public int getNumStmtNoTransform() {
    return getStmtListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Stmt list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Stmt list.
   * @apilevel high-level
   */
  public Stmt getStmt(int i) {
    return (Stmt) getStmtList().getChild(i);
  }
  /**
   * Check whether the Stmt list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasStmt() {
    return getStmtList().getNumChild() != 0;
  }
  /**
   * Append an element to the Stmt list.
   * @param node The element to append to the Stmt list.
   * @apilevel high-level
   */
  public void addStmt(Stmt node) {
    List<Stmt> list = (parent == null) ? getStmtListNoTransform() : getStmtList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addStmtNoTransform(Stmt node) {
    List<Stmt> list = getStmtListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the Stmt list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setStmt(Stmt node, int i) {
    List<Stmt> list = getStmtList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the Stmt list.
   * @return The node representing the Stmt list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="Stmt")
  public List<Stmt> getStmtList() {
    List<Stmt> list = (List<Stmt>) getChild(0);
    return list;
  }
  /**
   * Retrieves the Stmt list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Stmt list.
   * @apilevel low-level
   */
  public List<Stmt> getStmtListNoTransform() {
    return (List<Stmt>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the Stmt list without
   * triggering rewrites.
   */
  public Stmt getStmtNoTransform(int i) {
    return (Stmt) getStmtListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the Stmt list.
   * @return The node representing the Stmt list.
   * @apilevel high-level
   */
  public List<Stmt> getStmts() {
    return getStmtList();
  }
  /**
   * Retrieves the Stmt list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the Stmt list.
   * @apilevel low-level
   */
  public List<Stmt> getStmtsNoTransform() {
    return getStmtListNoTransform();
  }
/** @apilevel internal */
protected boolean countDecls_visited = false;
  /** @apilevel internal */
  private void countDecls_reset() {
    countDecls_computed = false;
    countDecls_visited = false;
  }
  /** @apilevel internal */
  protected boolean countDecls_computed = false;

  /** @apilevel internal */
  protected int countDecls_value;

  /**
   * @attribute syn
   * @aspect CodeGen
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:360
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CodeGen", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:360")
  public int countDecls() {
    ASTState state = state();
    if (countDecls_computed) {
      return countDecls_value;
    }
    if (countDecls_visited) {
      throw new RuntimeException("Circular definition of attribute Block.countDecls().");
    }
    countDecls_visited = true;
    state().enterLazyAttribute();
    countDecls_value = countDecls_compute();
    countDecls_computed = true;
    state().leaveLazyAttribute();
    countDecls_visited = false;
    return countDecls_value;
  }
  /** @apilevel internal */
  private int countDecls_compute() {
      int count = 0;
      for(Stmt s : getStmts()){
        count += s.countDecls();
      }
      return count;
    }
/** @apilevel internal */
protected java.util.Set localLookup_String_int_visited;
  /** @apilevel internal */
  private void localLookup_String_int_reset() {
    localLookup_String_int_values = null;
    localLookup_String_int_visited = null;
  }
  /** @apilevel internal */
  protected java.util.Map localLookup_String_int_values;

  /**
   * @attribute syn
   * @aspect NameAnalysis
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:65
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:65")
  public IdDecl localLookup(String name, int until) {
    java.util.List _parameters = new java.util.ArrayList(2);
    _parameters.add(name);
    _parameters.add(until);
    if (localLookup_String_int_visited == null) localLookup_String_int_visited = new java.util.HashSet(4);
    if (localLookup_String_int_values == null) localLookup_String_int_values = new java.util.HashMap(4);
    ASTState state = state();
    if (localLookup_String_int_values.containsKey(_parameters)) {
      return (IdDecl) localLookup_String_int_values.get(_parameters);
    }
    if (localLookup_String_int_visited.contains(_parameters)) {
      throw new RuntimeException("Circular definition of attribute Block.localLookup(String,int).");
    }
    localLookup_String_int_visited.add(_parameters);
    state().enterLazyAttribute();
    IdDecl localLookup_String_int_value = localLookup_compute(name, until);
    localLookup_String_int_values.put(_parameters, localLookup_String_int_value);
    state().leaveLazyAttribute();
    localLookup_String_int_visited.remove(_parameters);
    return localLookup_String_int_value;
  }
  /** @apilevel internal */
  private IdDecl localLookup_compute(String name, int until) {
  		for (int i = 0; i <= until; i++) {
  			if (getStmt(i) instanceof IdReadDecl && ((IdReadDecl)getStmt(i)).getIdDecl().getID().equals(name)) {
  				return ((IdReadDecl)getStmt(i)).getIdDecl();
  			}
  			if (getStmt(i) instanceof ListDeclaration && ((ListDeclaration)getStmt(i)).getIdDecl().getID().equals(name)) {
  				return ((ListDeclaration)getStmt(i)).getIdDecl();
  			}
  		}
  		return unknownDecl();
  	}
  /**
   * @attribute inh
   * @aspect NameAnalysis
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:20
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:20")
  public String getPrefix() {
    ASTState state = state();
    if (getPrefix_computed) {
      return getPrefix_value;
    }
    if (getPrefix_visited) {
      throw new RuntimeException("Circular definition of attribute Block.getPrefix().");
    }
    getPrefix_visited = true;
    state().enterLazyAttribute();
    getPrefix_value = getParent().Define_getPrefix(this, null);
    getPrefix_computed = true;
    state().leaveLazyAttribute();
    getPrefix_visited = false;
    return getPrefix_value;
  }
/** @apilevel internal */
protected boolean getPrefix_visited = false;
  /** @apilevel internal */
  private void getPrefix_reset() {
    getPrefix_computed = false;
    
    getPrefix_value = null;
    getPrefix_visited = false;
  }
  /** @apilevel internal */
  protected boolean getPrefix_computed = false;

  /** @apilevel internal */
  protected String getPrefix_value;

  /**
   * @attribute inh
   * @aspect NameAnalysis
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:26
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:26")
  public String getLabel() {
    ASTState state = state();
    if (getLabel_computed) {
      return getLabel_value;
    }
    if (getLabel_visited) {
      throw new RuntimeException("Circular definition of attribute Block.getLabel().");
    }
    getLabel_visited = true;
    state().enterLazyAttribute();
    getLabel_value = getParent().Define_getLabel(this, null);
    getLabel_computed = true;
    state().leaveLazyAttribute();
    getLabel_visited = false;
    return getLabel_value;
  }
/** @apilevel internal */
protected boolean getLabel_visited = false;
  /** @apilevel internal */
  private void getLabel_reset() {
    getLabel_computed = false;
    
    getLabel_value = null;
    getLabel_visited = false;
  }
  /** @apilevel internal */
  protected boolean getLabel_computed = false;

  /** @apilevel internal */
  protected String getLabel_value;

  /**
   * @attribute inh
   * @aspect NameAnalysis
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:63
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:63")
  public IdDecl lookup(String name) {
    Object _parameters = name;
    if (lookup_String_visited == null) lookup_String_visited = new java.util.HashSet(4);
    if (lookup_String_values == null) lookup_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (lookup_String_values.containsKey(_parameters)) {
      return (IdDecl) lookup_String_values.get(_parameters);
    }
    if (lookup_String_visited.contains(_parameters)) {
      throw new RuntimeException("Circular definition of attribute Block.lookup(String).");
    }
    lookup_String_visited.add(_parameters);
    state().enterLazyAttribute();
    IdDecl lookup_String_value = getParent().Define_lookup(this, null, name);
    lookup_String_values.put(_parameters, lookup_String_value);
    state().leaveLazyAttribute();
    lookup_String_visited.remove(_parameters);
    return lookup_String_value;
  }
/** @apilevel internal */
protected java.util.Set lookup_String_visited;
  /** @apilevel internal */
  private void lookup_String_reset() {
    lookup_String_values = null;
    lookup_String_visited = null;
  }
  /** @apilevel internal */
  protected java.util.Map lookup_String_values;

  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:20
   * @apilevel internal
   */
  public String Define_getPrefix(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getStmtListNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:22
      int index = _callerNode.getIndexOfChild(_childNode);
      {
      		return getPrefix() + index + "_" ;
      	}
    }
    else {
      return getParent().Define_getPrefix(this, _callerNode);
    }
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:20
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute getPrefix
   */
  protected boolean canDefine_getPrefix(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:26
   * @apilevel internal
   */
  public String Define_getLabel(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getStmtListNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:27
      int index = _callerNode.getIndexOfChild(_childNode);
      {
      		return getLabel() + index + "_" ;
      	}
    }
    else {
      return getParent().Define_getLabel(this, _callerNode);
    }
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:26
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute getLabel
   */
  protected boolean canDefine_getLabel(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:63
   * @apilevel internal
   */
  public IdDecl Define_lookup(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getStmtListNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:77
      int index = _callerNode.getIndexOfChild(_childNode);
      {
      		IdDecl decl = localLookup(name, index);
      		return !decl.isUnknown() ? decl : lookup(name);
      	}
    }
    else {
      return getParent().Define_lookup(this, _callerNode, name);
    }
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:63
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookup
   */
  protected boolean canDefine_lookup(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
}
