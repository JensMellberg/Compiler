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
 * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/lang.ast:30
 * @astdecl FuncDecl : ASTNode ::= IdDecl IdParam* Block;
 * @production FuncDecl : {@link ASTNode} ::= <span class="component">{@link IdDecl}</span> <span class="component">{@link IdParam}*</span> <span class="component">{@link Block}</span>;

 */
public class FuncDecl extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect CodeGen
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:132
   */
  public void genCode(PrintStream out) {
      out.println(getIdDecl().getID() +":");
      out.println("        pushq %rbp");
      out.println("        movq %rsp, %rbp");
      out.println("        subq $"+(countDecls()*8+getNumIdParam()*8)+", %rsp");

      getBlock().genCode(out);

      out.println("        movq $0, %rax");
      out.println("        movq %rbp, %rsp");
      out.println("        popq %rbp");
      out.println("        ret");
      out.println();
   }
  /**
   * @aspect ActivationRecord
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/ActivationRecord.jrag:29
   */
  public int eval(ActivationRecord actrec){
  try {
    getBlock().eval(actrec); }
    catch (ReturnException e) {
      return e.getValue();
    }
    return 0; //actrec.get("return");
  }
  /**
   * @declaredat ASTNode:1
   */
  public FuncDecl() {
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
    setChild(new List(), 1);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"IdDecl", "IdParam", "Block"},
    type = {"IdDecl", "List<IdParam>", "Block"},
    kind = {"Child", "List", "Child"}
  )
  public FuncDecl(IdDecl p0, List<IdParam> p1, Block p2) {
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
    localIndex_reset();
    reachable_reset();
    localLookup_String_reset();
    lookup_String_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    FuncDecl_functionCalls_visited = false;
    FuncDecl_functionCalls_computed = false;
    
    FuncDecl_functionCalls_value = null;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:46
   */
  public FuncDecl clone() throws CloneNotSupportedException {
    FuncDecl node = (FuncDecl) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:51
   */
  public FuncDecl copy() {
    try {
      FuncDecl node = (FuncDecl) clone();
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
   * @declaredat ASTNode:70
   */
  @Deprecated
  public FuncDecl fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:80
   */
  public FuncDecl treeCopyNoTransform() {
    FuncDecl tree = (FuncDecl) copy();
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
   * @declaredat ASTNode:100
   */
  public FuncDecl treeCopy() {
    FuncDecl tree = (FuncDecl) copy();
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
   * @declaredat ASTNode:114
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
   * Replaces the IdParam list.
   * @param list The new list node to be used as the IdParam list.
   * @apilevel high-level
   */
  public void setIdParamList(List<IdParam> list) {
    setChild(list, 1);
  }
  /**
   * Retrieves the number of children in the IdParam list.
   * @return Number of children in the IdParam list.
   * @apilevel high-level
   */
  public int getNumIdParam() {
    return getIdParamList().getNumChild();
  }
  /**
   * Retrieves the number of children in the IdParam list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the IdParam list.
   * @apilevel low-level
   */
  public int getNumIdParamNoTransform() {
    return getIdParamListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the IdParam list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the IdParam list.
   * @apilevel high-level
   */
  public IdParam getIdParam(int i) {
    return (IdParam) getIdParamList().getChild(i);
  }
  /**
   * Check whether the IdParam list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasIdParam() {
    return getIdParamList().getNumChild() != 0;
  }
  /**
   * Append an element to the IdParam list.
   * @param node The element to append to the IdParam list.
   * @apilevel high-level
   */
  public void addIdParam(IdParam node) {
    List<IdParam> list = (parent == null) ? getIdParamListNoTransform() : getIdParamList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addIdParamNoTransform(IdParam node) {
    List<IdParam> list = getIdParamListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the IdParam list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setIdParam(IdParam node, int i) {
    List<IdParam> list = getIdParamList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the IdParam list.
   * @return The node representing the IdParam list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="IdParam")
  public List<IdParam> getIdParamList() {
    List<IdParam> list = (List<IdParam>) getChild(1);
    return list;
  }
  /**
   * Retrieves the IdParam list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the IdParam list.
   * @apilevel low-level
   */
  public List<IdParam> getIdParamListNoTransform() {
    return (List<IdParam>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the IdParam list without
   * triggering rewrites.
   */
  public IdParam getIdParamNoTransform(int i) {
    return (IdParam) getIdParamListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the IdParam list.
   * @return The node representing the IdParam list.
   * @apilevel high-level
   */
  public List<IdParam> getIdParams() {
    return getIdParamList();
  }
  /**
   * Retrieves the IdParam list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the IdParam list.
   * @apilevel low-level
   */
  public List<IdParam> getIdParamsNoTransform() {
    return getIdParamListNoTransform();
  }
  /**
   * Replaces the Block child.
   * @param node The new node to replace the Block child.
   * @apilevel high-level
   */
  public void setBlock(Block node) {
    setChild(node, 2);
  }
  /**
   * Retrieves the Block child.
   * @return The current node used as the Block child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Block")
  public Block getBlock() {
    return (Block) getChild(2);
  }
  /**
   * Retrieves the Block child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Block child.
   * @apilevel low-level
   */
  public Block getBlockNoTransform() {
    return (Block) getChildNoTransform(2);
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
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:356
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CodeGen", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:356")
  public int countDecls() {
    ASTState state = state();
    if (countDecls_computed) {
      return countDecls_value;
    }
    if (countDecls_visited) {
      throw new RuntimeException("Circular definition of attribute FuncDecl.countDecls().");
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
      return getBlock().countDecls();
    }
/** @apilevel internal */
protected boolean localIndex_visited = false;
  /** @apilevel internal */
  private void localIndex_reset() {
    localIndex_computed = false;
    localIndex_visited = false;
  }
  /** @apilevel internal */
  protected boolean localIndex_computed = false;

  /** @apilevel internal */
  protected int localIndex_value;

  /**
   * @attribute syn
   * @aspect CodeGen
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:389
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CodeGen", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:388")
  public int localIndex() {
    ASTState state = state();
    if (localIndex_computed) {
      return localIndex_value;
    }
    if (localIndex_visited) {
      throw new RuntimeException("Circular definition of attribute ASTNode.localIndex().");
    }
    localIndex_visited = true;
    state().enterLazyAttribute();
    localIndex_value = 0;
    localIndex_computed = true;
    state().leaveLazyAttribute();
    localIndex_visited = false;
    return localIndex_value;
  }
/** @apilevel internal */
protected ASTState.Cycle reachable_cycle = null;
  /** @apilevel internal */
  private void reachable_reset() {
    reachable_computed = false;
    reachable_initialized = false;
    reachable_value = null;
    reachable_cycle = null;
  }
  /** @apilevel internal */
  protected boolean reachable_computed = false;

  /** @apilevel internal */
  protected Set<FuncDeclComp> reachable_value;
  /** @apilevel internal */
  protected boolean reachable_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="FunctionCalls", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/FunctionCalls.jrag:16")
  public Set<FuncDeclComp> reachable() {
    if (reachable_computed) {
      return reachable_value;
    }
    ASTState state = state();
    if (!reachable_initialized) {
      reachable_initialized = true;
      reachable_value = new TreeSet<FuncDeclComp>();
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        reachable_cycle = state.nextCycle();
        Set<FuncDeclComp> new_reachable_value = reachable_compute();
        if (!AttributeValue.equals(reachable_value, new_reachable_value)) {
          state.setChangeInCycle();
        }
        reachable_value = new_reachable_value;
      } while (state.testAndClearChangeInCycle());
      reachable_computed = true;
      state.startLastCycle();
      Set<FuncDeclComp> $tmp = reachable_compute();

      state.leaveCircle();
    } else if (reachable_cycle != state.cycle()) {
      reachable_cycle = state.cycle();
      if (state.lastCycle()) {
        reachable_computed = true;
        Set<FuncDeclComp> new_reachable_value = reachable_compute();
        return new_reachable_value;
      }
      Set<FuncDeclComp> new_reachable_value = reachable_compute();
      if (!AttributeValue.equals(reachable_value, new_reachable_value)) {
        state.setChangeInCycle();
      }
      reachable_value = new_reachable_value;
    } else {
    }
    return reachable_value;
  }
  /** @apilevel internal */
  private Set<FuncDeclComp> reachable_compute() {
    Set<FuncDeclComp> s = new TreeSet<FuncDeclComp>();
    Set<FuncDeclComp> calls = functionCalls();
    s.addAll(calls);
    for(FuncDeclComp func : calls ){
      s.addAll(func.funcDecl.reachable());
    }
    return s;
  }
/** @apilevel internal */
protected java.util.Set localLookup_String_visited;
  /** @apilevel internal */
  private void localLookup_String_reset() {
    localLookup_String_values = null;
    localLookup_String_visited = null;
  }
  /** @apilevel internal */
  protected java.util.Map localLookup_String_values;

  /**
   * @attribute syn
   * @aspect NameAnalysis
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:55
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:55")
  public IdDecl localLookup(String name) {
    Object _parameters = name;
    if (localLookup_String_visited == null) localLookup_String_visited = new java.util.HashSet(4);
    if (localLookup_String_values == null) localLookup_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (localLookup_String_values.containsKey(_parameters)) {
      return (IdDecl) localLookup_String_values.get(_parameters);
    }
    if (localLookup_String_visited.contains(_parameters)) {
      throw new RuntimeException("Circular definition of attribute FuncDecl.localLookup(String).");
    }
    localLookup_String_visited.add(_parameters);
    state().enterLazyAttribute();
    IdDecl localLookup_String_value = localLookup_compute(name);
    localLookup_String_values.put(_parameters, localLookup_String_value);
    state().leaveLazyAttribute();
    localLookup_String_visited.remove(_parameters);
    return localLookup_String_value;
  }
  /** @apilevel internal */
  private IdDecl localLookup_compute(String name) {
  		for (IdParam p : getIdParams()) {
  			if (p.getIdDecl().getID().equals(name))
  				return p.getIdDecl();
  		}
  		return unknownDecl();
  	}
  /**
   * @attribute inh
   * @aspect NameAnalysis
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:4
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:4")
  public IdDecl lookup(String name) {
    Object _parameters = name;
    if (lookup_String_visited == null) lookup_String_visited = new java.util.HashSet(4);
    if (lookup_String_values == null) lookup_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (lookup_String_values.containsKey(_parameters)) {
      return (IdDecl) lookup_String_values.get(_parameters);
    }
    if (lookup_String_visited.contains(_parameters)) {
      throw new RuntimeException("Circular definition of attribute FuncDecl.lookup(String).");
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
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:91
   * @apilevel internal
   */
  public boolean Define_isFunction(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getIdDeclNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:81
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
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:87
   * @apilevel internal
   */
  public FuncDecl Define_function(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getIdDeclNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:82
      return this;
    }
    else {
      return getParent().Define_function(this, _callerNode);
    }
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:87
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute function
   */
  protected boolean canDefine_function(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:146
   * @apilevel internal
   */
  public int Define_getParamIndex(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getIdParamListNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:148
      int index = _callerNode.getIndexOfChild(_childNode);
      return index;
    }
    else {
      return getParent().Define_getParamIndex(this, _callerNode);
    }
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:146
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute getParamIndex
   */
  protected boolean canDefine_getParamIndex(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
/** @apilevel internal */
protected boolean FuncDecl_functionCalls_visited = false;
  /**
   * @attribute coll
   * @aspect FunctionCalls
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/FunctionCalls.jrag:27
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="FunctionCalls", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/FunctionCalls.jrag:27")
  public Set<FuncDeclComp> functionCalls() {
    ASTState state = state();
    if (FuncDecl_functionCalls_computed) {
      return FuncDecl_functionCalls_value;
    }
    if (FuncDecl_functionCalls_visited) {
      throw new RuntimeException("Circular definition of attribute FuncDecl.functionCalls().");
    }
    FuncDecl_functionCalls_visited = true;
    state().enterLazyAttribute();
    FuncDecl_functionCalls_value = functionCalls_compute();
    FuncDecl_functionCalls_computed = true;
    state().leaveLazyAttribute();
    FuncDecl_functionCalls_visited = false;
    return FuncDecl_functionCalls_value;
  }
  /** @apilevel internal */
  private Set<FuncDeclComp> functionCalls_compute() {
    ASTNode node = this;
    while (node != null && !(node instanceof Program)) {
      node = node.getParent();
    }
    Program root = (Program) node;
    root.survey_FuncDecl_functionCalls();
    Set<FuncDeclComp> _computedValue = new TreeSet<FuncDeclComp>();
    if (root.contributorMap_FuncDecl_functionCalls.containsKey(this)) {
      for (ASTNode contributor : root.contributorMap_FuncDecl_functionCalls.get(this)) {
        contributor.contributeTo_FuncDecl_functionCalls(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected boolean FuncDecl_functionCalls_computed = false;

  /** @apilevel internal */
  protected Set<FuncDeclComp> FuncDecl_functionCalls_value;

}
