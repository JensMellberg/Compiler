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
 * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/lang.ast:9
 * @astdecl If : Stmt ::= Expr First:Block [Second:Block];
 * @production If : {@link Stmt} ::= <span class="component">{@link Expr}</span> <span class="component">First:{@link Block}</span> <span class="component">[Second:{@link Block}]</span>;

 */
public class If extends Stmt implements Cloneable {
  /**
   * @aspect CodeGen
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:267
   */
  public void genCode(PrintStream out){
    getExpr().genConditionalJump(out, getLabel() + "ElseStart");
    getFirst().genCode(out);
    out.println("        jmp " + getLabel()+ "IfEnd");
    out.println(getLabel() + "ElseStart:");
    if (hasSecond())
      getSecond().genCode(out);

    out.println(getLabel() + "IfEnd:");
  }
  /**
   * @aspect ActivationRecord
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/ActivationRecord.jrag:88
   */
  public void eval(ActivationRecord actrec) throws ReturnException {
    if (getExpr().eval(actrec) == 1) {
      getFirst().eval(actrec);
    } else if(hasSecond()) {
      getSecond().eval(actrec);
    }
  }
  /**
   * @declaredat ASTNode:1
   */
  public If() {
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
    children = new ASTNode[3];
    setChild(new Opt(), 2);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Expr", "First", "Second"},
    type = {"Expr", "Block", "Opt<Block>"},
    kind = {"Child", "Child", "Opt"}
  )
  public If(Expr p0, Block p1, Opt<Block> p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:25
   */
  protected int numChildren() {
    return 3;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:29
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    countDecls_reset();
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
  public If clone() throws CloneNotSupportedException {
    If node = (If) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:43
   */
  public If copy() {
    try {
      If node = (If) clone();
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
  public If fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:72
   */
  public If treeCopyNoTransform() {
    If tree = (If) copy();
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
  public If treeCopy() {
    If tree = (If) copy();
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
   * Replaces the Expr child.
   * @param node The new node to replace the Expr child.
   * @apilevel high-level
   */
  public void setExpr(Expr node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Expr child.
   * @return The current node used as the Expr child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Expr")
  public Expr getExpr() {
    return (Expr) getChild(0);
  }
  /**
   * Retrieves the Expr child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Expr child.
   * @apilevel low-level
   */
  public Expr getExprNoTransform() {
    return (Expr) getChildNoTransform(0);
  }
  /**
   * Replaces the First child.
   * @param node The new node to replace the First child.
   * @apilevel high-level
   */
  public void setFirst(Block node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the First child.
   * @return The current node used as the First child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="First")
  public Block getFirst() {
    return (Block) getChild(1);
  }
  /**
   * Retrieves the First child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the First child.
   * @apilevel low-level
   */
  public Block getFirstNoTransform() {
    return (Block) getChildNoTransform(1);
  }
  /**
   * Replaces the optional node for the Second child. This is the <code>Opt</code>
   * node containing the child Second, not the actual child!
   * @param opt The new node to be used as the optional node for the Second child.
   * @apilevel low-level
   */
  public void setSecondOpt(Opt<Block> opt) {
    setChild(opt, 2);
  }
  /**
   * Replaces the (optional) Second child.
   * @param node The new node to be used as the Second child.
   * @apilevel high-level
   */
  public void setSecond(Block node) {
    getSecondOpt().setChild(node, 0);
  }
  /**
   * Check whether the optional Second child exists.
   * @return {@code true} if the optional Second child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasSecond() {
    return getSecondOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) Second child.
   * @return The Second child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Block getSecond() {
    return (Block) getSecondOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the Second child. This is the <code>Opt</code> node containing the child Second, not the actual child!
   * @return The optional node for child the Second child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="Second")
  public Opt<Block> getSecondOpt() {
    return (Opt<Block>) getChild(2);
  }
  /**
   * Retrieves the optional node for child Second. This is the <code>Opt</code> node containing the child Second, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child Second.
   * @apilevel low-level
   */
  public Opt<Block> getSecondOptNoTransform() {
    return (Opt<Block>) getChildNoTransform(2);
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
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:376
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CodeGen", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:368")
  public int countDecls() {
    ASTState state = state();
    if (countDecls_computed) {
      return countDecls_value;
    }
    if (countDecls_visited) {
      throw new RuntimeException("Circular definition of attribute Stmt.countDecls().");
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
      if(hasSecond()){
        return getFirst().countDecls() + getSecond().countDecls();
      }
      return getFirst().countDecls();
    }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:123
   * @apilevel internal
   */
  public Type Define_expectedType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getExprNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:124
      return boolType();
    }
    else {
      return getParent().Define_expectedType(this, _callerNode);
    }
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:123
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute expectedType
   */
  protected boolean canDefine_expectedType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
}
