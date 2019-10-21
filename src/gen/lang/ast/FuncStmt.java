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
 * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/lang.ast:12
 * @astdecl FuncStmt : Stmt ::= FuncCall;
 * @production FuncStmt : {@link Stmt} ::= <span class="component">{@link FuncCall}</span>;

 */
public class FuncStmt extends Stmt implements Cloneable {
  /**
   * @aspect CodeGen
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:287
   */
  public void genCode(PrintStream out){
    getFuncCall().genCode(out);
  }
  /**
   * @aspect ActivationRecord
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/ActivationRecord.jrag:38
   */
  public void eval(ActivationRecord actrec) {
    getFuncCall().eval(actrec);
  }
  /**
   * @declaredat ASTNode:1
   */
  public FuncStmt() {
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
  }
  /**
   * @declaredat ASTNode:13
   */
  @ASTNodeAnnotation.Constructor(
    name = {"FuncCall"},
    type = {"FuncCall"},
    kind = {"Child"}
  )
  public FuncStmt(FuncCall p0) {
    setChild(p0, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:22
   */
  protected int numChildren() {
    return 1;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:26
   */
  public void flushAttrCache() {
    super.flushAttrCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:30
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:34
   */
  public FuncStmt clone() throws CloneNotSupportedException {
    FuncStmt node = (FuncStmt) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:39
   */
  public FuncStmt copy() {
    try {
      FuncStmt node = (FuncStmt) clone();
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
   * @declaredat ASTNode:58
   */
  @Deprecated
  public FuncStmt fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:68
   */
  public FuncStmt treeCopyNoTransform() {
    FuncStmt tree = (FuncStmt) copy();
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
   * @declaredat ASTNode:88
   */
  public FuncStmt treeCopy() {
    FuncStmt tree = (FuncStmt) copy();
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
   * @declaredat ASTNode:102
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the FuncCall child.
   * @param node The new node to replace the FuncCall child.
   * @apilevel high-level
   */
  public void setFuncCall(FuncCall node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the FuncCall child.
   * @return The current node used as the FuncCall child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="FuncCall")
  public FuncCall getFuncCall() {
    return (FuncCall) getChild(0);
  }
  /**
   * Retrieves the FuncCall child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the FuncCall child.
   * @apilevel low-level
   */
  public FuncCall getFuncCallNoTransform() {
    return (FuncCall) getChildNoTransform(0);
  }
}
