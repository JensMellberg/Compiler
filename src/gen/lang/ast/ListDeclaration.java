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
 * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/lang.ast:27
 * @astdecl ListDeclaration : IdDeclaration ::= IdDecl [MyList];
 * @production ListDeclaration : {@link IdDeclaration} ::= <span class="component">{@link IdDecl}</span> <span class="component">[{@link MyList}]</span>;

 */
public class ListDeclaration extends IdDeclaration implements Cloneable {
  /**
   * @aspect CodeGen
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:336
   */
  public void genCode(PrintStream out){
      if(hasMyList()){
        getMyList().genCode(out);
        out.println("        movq %rsp, -"+ ((getIdDecl().localIndex()-1)*8) +"(%rbp)");
      } else {
        out.println("        movq $0, %rax");
      }


    }
  /**
   * @declaredat ASTNode:1
   */
  public ListDeclaration() {
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
    setChild(new Opt(), 1);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"IdDecl", "MyList"},
    type = {"IdDecl", "Opt<MyList>"},
    kind = {"Child", "Opt"}
  )
  public ListDeclaration(IdDecl p0, Opt<MyList> p1) {
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
  }
  /** @apilevel internal 
   * @declaredat ASTNode:32
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:36
   */
  public ListDeclaration clone() throws CloneNotSupportedException {
    ListDeclaration node = (ListDeclaration) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:41
   */
  public ListDeclaration copy() {
    try {
      ListDeclaration node = (ListDeclaration) clone();
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
   * @declaredat ASTNode:60
   */
  @Deprecated
  public ListDeclaration fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:70
   */
  public ListDeclaration treeCopyNoTransform() {
    ListDeclaration tree = (ListDeclaration) copy();
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
   * @declaredat ASTNode:90
   */
  public ListDeclaration treeCopy() {
    ListDeclaration tree = (ListDeclaration) copy();
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
   * @declaredat ASTNode:104
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the IdDecl child.
   * @param node The new node to replace the IdDecl child.
   * @apilevel high-level
   */
  public void setIdDecl(IdDecl node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the IdDecl child.
   * @return The current node used as the IdDecl child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="IdDecl")
  public IdDecl getIdDecl() {
    return (IdDecl) getChild(0);
  }
  /**
   * Retrieves the IdDecl child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the IdDecl child.
   * @apilevel low-level
   */
  public IdDecl getIdDeclNoTransform() {
    return (IdDecl) getChildNoTransform(0);
  }
  /**
   * Replaces the optional node for the MyList child. This is the <code>Opt</code>
   * node containing the child MyList, not the actual child!
   * @param opt The new node to be used as the optional node for the MyList child.
   * @apilevel low-level
   */
  public void setMyListOpt(Opt<MyList> opt) {
    setChild(opt, 1);
  }
  /**
   * Replaces the (optional) MyList child.
   * @param node The new node to be used as the MyList child.
   * @apilevel high-level
   */
  public void setMyList(MyList node) {
    getMyListOpt().setChild(node, 0);
  }
  /**
   * Check whether the optional MyList child exists.
   * @return {@code true} if the optional MyList child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasMyList() {
    return getMyListOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) MyList child.
   * @return The MyList child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public MyList getMyList() {
    return (MyList) getMyListOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the MyList child. This is the <code>Opt</code> node containing the child MyList, not the actual child!
   * @return The optional node for child the MyList child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="MyList")
  public Opt<MyList> getMyListOpt() {
    return (Opt<MyList>) getChild(1);
  }
  /**
   * Retrieves the optional node for child MyList. This is the <code>Opt</code> node containing the child MyList, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child MyList.
   * @apilevel low-level
   */
  public Opt<MyList> getMyListOptNoTransform() {
    return (Opt<MyList>) getChildNoTransform(1);
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:106
   * @apilevel internal
   */
  public Type Define_getType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getIdDeclNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:108
      return listType();
    }
    else {
      return getParent().Define_getType(this, _callerNode);
    }
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:106
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute getType
   */
  protected boolean canDefine_getType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:123
   * @apilevel internal
   */
  public Type Define_expectedType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getMyListOptNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:129
      return listType();
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
