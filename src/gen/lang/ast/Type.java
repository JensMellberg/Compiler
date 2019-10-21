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
 * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/lang.ast:39
 * @astdecl Type : ASTNode;
 * @production Type : {@link ASTNode};

 */
public abstract class Type extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @declaredat ASTNode:1
   */
  public Type() {
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
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:13
   */
  protected int numChildren() {
    return 0;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:17
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    compatibleType_Type_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:22
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:26
   */
  public Type clone() throws CloneNotSupportedException {
    Type node = (Type) super.clone();
    return node;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:37
   */
  @Deprecated
  public abstract Type fullCopy();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:45
   */
  public abstract Type treeCopyNoTransform();
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:53
   */
  public abstract Type treeCopy();
/** @apilevel internal */
protected java.util.Set compatibleType_Type_visited;
  /** @apilevel internal */
  private void compatibleType_Type_reset() {
    compatibleType_Type_values = null;
    compatibleType_Type_visited = null;
  }
  /** @apilevel internal */
  protected java.util.Map compatibleType_Type_values;

  /**
   * @attribute syn
   * @aspect NameAnalysis
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:131
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:131")
  public boolean compatibleType(Type t) {
    Object _parameters = t;
    if (compatibleType_Type_visited == null) compatibleType_Type_visited = new java.util.HashSet(4);
    if (compatibleType_Type_values == null) compatibleType_Type_values = new java.util.HashMap(4);
    ASTState state = state();
    if (compatibleType_Type_values.containsKey(_parameters)) {
      return (Boolean) compatibleType_Type_values.get(_parameters);
    }
    if (compatibleType_Type_visited.contains(_parameters)) {
      throw new RuntimeException("Circular definition of attribute Type.compatibleType(Type).");
    }
    compatibleType_Type_visited.add(_parameters);
    state().enterLazyAttribute();
    boolean compatibleType_Type_value = compatibleType_compute(t);
    compatibleType_Type_values.put(_parameters, compatibleType_Type_value);
    state().leaveLazyAttribute();
    compatibleType_Type_visited.remove(_parameters);
    return compatibleType_Type_value;
  }
  /** @apilevel internal */
  private boolean compatibleType_compute(Type t) {
  		if(t.getClass().isInstance(this) || t instanceof UnknownType || this instanceof UnknownType ){
  			return true;
  		}
  		return false;
  	}
}
