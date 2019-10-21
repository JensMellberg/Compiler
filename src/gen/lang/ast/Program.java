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
 * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/lang.ast:1
 * @astdecl Program : ASTNode ::= FuncDecl*;
 * @production Program : {@link ASTNode} ::= <span class="component">{@link FuncDecl}*</span>;

 */
public class Program extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect CodeGen
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/CodeGen.jrag:7
   */
  public void genCode(PrintStream out) {
    out.println(".global _start");

    out.println(".data");
    out.println("buf: .skip 1024");
    out.println();

    out.println(".text");
    out.println("_start:");
    out.println("        call main");
    out.println("        movq %rax, %rdi");
    out.println("        movq $60, %rax");
    out.println("        syscall"); // Done!

    for (FuncDecl decl: getFuncDecls()) {
      decl.genCode(out);
    }

    out.println("# Procedure to read number from stdin.");
    out.println("# C signature: long long int read(void)");
    out.println("read:");
    out.println("        pushq %rbp");
    out.println("        movq %rsp, %rbp");
    out.println("        ### R9  = sign");
    out.println("        movq $1, %r9            # sign <- 1");
    out.println("        ### R10 = sum");
    out.println("        movq $0, %r10           # sum <- 0");
    out.println("skip_ws: # skip any leading whitespace");
    out.println("        movq $0, %rdi");
    out.println("        movq $buf, %rsi");
    out.println("        movq $1, %rdx");
    out.println("        movq $0, %rax");
    out.println("        syscall                 # get one char: sys_read(0, buf, 1)");
    out.println("        cmpq $0, %rax");
    out.println("        jle atoi_done           # nchar <= 0");
    out.println("        movb (%rsi), %cl        # c <- current char");
    out.println("        cmp $32, %cl");
    out.println("        je skip_ws              # c == space");
    out.println("        cmp $13, %cl");
    out.println("        je skip_ws              # c == CR");
    out.println("        cmp $10, %cl");
    out.println("        je skip_ws              # c == NL");
    out.println("        cmp $9, %cl");
    out.println("        je skip_ws              # c == tab");
    out.println("        cmp $45, %cl            # check if negative");
    out.println("        jne atoi_loop");
    out.println("        movq $-1, %r9           # sign <- -1");
    out.println("        movq $0, %rdi");
    out.println("        movq $buf, %rsi");
    out.println("        movq $1, %rdx");
    out.println("        movq $0, %rax");
    out.println("        syscall                 # get one char: sys_read(0, buf, 1)");
    out.println("atoi_loop:");
    out.println("        cmpq $0, %rax           # while (nchar > 0)");
    out.println("        jle atoi_done           # leave loop if nchar <= 0");
    out.println("        movzbq (%rsi), %rcx     # move byte, zero extend to quad-word");
    out.println("        cmpq $0x30, %rcx        # test if < '0'");
    out.println("        jl atoi_done            # character is not numeric");
    out.println("        cmpq $0x39, %rcx        # test if > '9'");
    out.println("        jg atoi_done            # character is not numeric");
    out.println("        imulq $10, %r10         # multiply sum by 10");
    out.println("        subq $0x30, %rcx        # value of character");
    out.println("        addq %rcx, %r10         # add to sum");
    out.println("        movq $0, %rdi");
    out.println("        movq $buf, %rsi");
    out.println("        movq $1, %rdx");
    out.println("        movq $0, %rax");
    out.println("        syscall                 # get one char: sys_read(0, buf, 1)");
    out.println("        jmp atoi_loop           # loop back");
    out.println("atoi_done:");
    out.println("        imulq %r9, %r10         # sum *= sign");
    out.println("        movq %r10, %rax         # put result value in RAX");
    out.println("        popq %rbp");
    out.println("        ret");
    out.println();
    out.println("# Procedure to print number to stdout.");
    out.println("# C signature: void print(long int)");
    out.println("print:");
    out.println("        pushq %rbp");
    out.println("        movq %rsp, %rbp");
    out.println("        ### Convert integer to string (itoa).");
    out.println("        movq 16(%rbp), %rax");
    out.println("        movq $(buf+1023), %rsi  # RSI = write pointer (starts at end of buffer)");
    out.println("        movb $0x0A, (%rsi)      # insert newline");
    out.println("        movq $1, %rcx           # RCX = string length");
    out.println("        cmpq $0, %rax");
    out.println("        jge itoa_loop");
    out.println("        negq %rax               # negate to make RAX positive");
    out.println("itoa_loop:                      # do.. while (at least one iteration)");
    out.println("        movq $10, %rdi");
    out.println("        movq $0, %rdx");
    out.println("        idivq %rdi              # divide RDX:RAX by 10");
    out.println("        addb $0x30, %dl         # remainder + '0'");
    out.println("        decq %rsi               # move string pointer");
    out.println("        movb %dl, (%rsi)");
    out.println("        incq %rcx               # increment string length");
    out.println("        cmpq $0, %rax");
    out.println("        jg itoa_loop            # produce more digits");
    out.println("itoa_done:");
    out.println("        movq 16(%rbp), %rax");
    out.println("        cmpq $0, %rax");
    out.println("        jge print_end");
    out.println("        decq %rsi");
    out.println("        incq %rcx");
    out.println("        movb $0x2D, (%rsi)");
    out.println("print_end:");
    out.println("        movq $1, %rdi");
    out.println("        movq %rcx, %rdx");
    out.println("        movq $1, %rax");
    out.println("        syscall");
    out.println("        popq %rbp");
    out.println("        ret");
    out.println();
    out.println("print_string:");
    out.println("        pushq %rbp");
    out.println("        movq %rsp, %rbp");
    out.println("        movq $1, %rdi");
    out.println("        movq 16(%rbp), %rsi");
    out.println("        movq 24(%rbp), %rdx");
    out.println("        movq $1, %rax");
    out.println("        syscall");
    out.println("        popq %rbp");
    out.println("        ret");
  }
  /**
   * @aspect ActivationRecord
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/ActivationRecord.jrag:12
   */
  public void eval(){
    ActivationRecord main_rec = new ActivationRecord();
    for(FuncDecl f : getFuncDecls() ){
      if(f.getIdDecl().getID().equals("main")){
        f.eval(main_rec);
        //main_rec.print();
        return;
      }
    }
    throw new RuntimeException();
  }
  /**
   * @declaredat ASTNode:1
   */
  public Program() {
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
    name = {"FuncDecl"},
    type = {"List<FuncDecl>"},
    kind = {"List"}
  )
  public Program(List<FuncDecl> p0) {
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
    unknownDecl_reset();
    intType_reset();
    boolType_reset();
    listType_reset();
    unknownType_reset();
    predefinedFunctions_reset();
    lookup_String_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:38
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    Program_errors_visited = false;
    Program_errors_computed = false;
    
    Program_errors_value = null;
    contributorMap_Program_errors = null;
    contributorMap_FuncDecl_functionCalls = null;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:48
   */
  public Program clone() throws CloneNotSupportedException {
    Program node = (Program) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:53
   */
  public Program copy() {
    try {
      Program node = (Program) clone();
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
   * @declaredat ASTNode:72
   */
  @Deprecated
  public Program fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:82
   */
  public Program treeCopyNoTransform() {
    Program tree = (Program) copy();
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
   * @declaredat ASTNode:102
   */
  public Program treeCopy() {
    Program tree = (Program) copy();
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
   * @declaredat ASTNode:116
   */
  protected boolean is$Equal(ASTNode node) {
    return super.is$Equal(node);    
  }
  /**
   * Replaces the FuncDecl list.
   * @param list The new list node to be used as the FuncDecl list.
   * @apilevel high-level
   */
  public void setFuncDeclList(List<FuncDecl> list) {
    setChild(list, 0);
  }
  /**
   * Retrieves the number of children in the FuncDecl list.
   * @return Number of children in the FuncDecl list.
   * @apilevel high-level
   */
  public int getNumFuncDecl() {
    return getFuncDeclList().getNumChild();
  }
  /**
   * Retrieves the number of children in the FuncDecl list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the FuncDecl list.
   * @apilevel low-level
   */
  public int getNumFuncDeclNoTransform() {
    return getFuncDeclListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the FuncDecl list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the FuncDecl list.
   * @apilevel high-level
   */
  public FuncDecl getFuncDecl(int i) {
    return (FuncDecl) getFuncDeclList().getChild(i);
  }
  /**
   * Check whether the FuncDecl list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasFuncDecl() {
    return getFuncDeclList().getNumChild() != 0;
  }
  /**
   * Append an element to the FuncDecl list.
   * @param node The element to append to the FuncDecl list.
   * @apilevel high-level
   */
  public void addFuncDecl(FuncDecl node) {
    List<FuncDecl> list = (parent == null) ? getFuncDeclListNoTransform() : getFuncDeclList();
    list.addChild(node);
  }
  /** @apilevel low-level 
   */
  public void addFuncDeclNoTransform(FuncDecl node) {
    List<FuncDecl> list = getFuncDeclListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the FuncDecl list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public void setFuncDecl(FuncDecl node, int i) {
    List<FuncDecl> list = getFuncDeclList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the FuncDecl list.
   * @return The node representing the FuncDecl list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="FuncDecl")
  public List<FuncDecl> getFuncDeclList() {
    List<FuncDecl> list = (List<FuncDecl>) getChild(0);
    return list;
  }
  /**
   * Retrieves the FuncDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the FuncDecl list.
   * @apilevel low-level
   */
  public List<FuncDecl> getFuncDeclListNoTransform() {
    return (List<FuncDecl>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the FuncDecl list without
   * triggering rewrites.
   */
  public FuncDecl getFuncDeclNoTransform(int i) {
    return (FuncDecl) getFuncDeclListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the FuncDecl list.
   * @return The node representing the FuncDecl list.
   * @apilevel high-level
   */
  public List<FuncDecl> getFuncDecls() {
    return getFuncDeclList();
  }
  /**
   * Retrieves the FuncDecl list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the FuncDecl list.
   * @apilevel low-level
   */
  public List<FuncDecl> getFuncDeclsNoTransform() {
    return getFuncDeclListNoTransform();
  }
  /**
   * @aspect <NoAspect>
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/Errors.jrag:26
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_Program_errors = null;

  /** @apilevel internal */
  protected void survey_Program_errors() {
    if (contributorMap_Program_errors == null) {
      contributorMap_Program_errors = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_Program_errors(this, contributorMap_Program_errors);
    }
  }

  /**
   * @aspect <NoAspect>
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/FunctionCalls.jrag:27
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_FuncDecl_functionCalls = null;

  /** @apilevel internal */
  protected void survey_FuncDecl_functionCalls() {
    if (contributorMap_FuncDecl_functionCalls == null) {
      contributorMap_FuncDecl_functionCalls = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_FuncDecl_functionCalls(this, contributorMap_FuncDecl_functionCalls);
    }
  }

/** @apilevel internal */
protected boolean unknownDecl_visited = false;
  /** @apilevel internal */
  private void unknownDecl_reset() {
    unknownDecl_computed = false;
    
    unknownDecl_value = null;
    unknownDecl_visited = false;
  }
  /** @apilevel internal */
  protected boolean unknownDecl_computed = false;

  /** @apilevel internal */
  protected UnknownDecl unknownDecl_value;

  /**
   * @attribute syn
   * @aspect UnknownDecl
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:2
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="UnknownDecl", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:2")
  public UnknownDecl unknownDecl() {
    ASTState state = state();
    if (unknownDecl_computed) {
      return unknownDecl_value;
    }
    if (unknownDecl_visited) {
      throw new RuntimeException("Circular definition of attribute Program.unknownDecl().");
    }
    unknownDecl_visited = true;
    state().enterLazyAttribute();
    unknownDecl_value = new UnknownDecl("<unknown>");
    unknownDecl_value.setParent(this);
    unknownDecl_computed = true;
    state().leaveLazyAttribute();
    unknownDecl_visited = false;
    return unknownDecl_value;
  }
/** @apilevel internal */
protected boolean intType_visited = false;
  /** @apilevel internal */
  private void intType_reset() {
    intType_computed = false;
    
    intType_value = null;
    intType_visited = false;
  }
  /** @apilevel internal */
  protected boolean intType_computed = false;

  /** @apilevel internal */
  protected IntType intType_value;

  /**
   * @attribute syn
   * @aspect Type
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:12
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="Type", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:12")
  public IntType intType() {
    ASTState state = state();
    if (intType_computed) {
      return intType_value;
    }
    if (intType_visited) {
      throw new RuntimeException("Circular definition of attribute Program.intType().");
    }
    intType_visited = true;
    state().enterLazyAttribute();
    intType_value = new IntType();
    intType_value.setParent(this);
    intType_computed = true;
    state().leaveLazyAttribute();
    intType_visited = false;
    return intType_value;
  }
/** @apilevel internal */
protected boolean boolType_visited = false;
  /** @apilevel internal */
  private void boolType_reset() {
    boolType_computed = false;
    
    boolType_value = null;
    boolType_visited = false;
  }
  /** @apilevel internal */
  protected boolean boolType_computed = false;

  /** @apilevel internal */
  protected BoolType boolType_value;

  /**
   * @attribute syn
   * @aspect Type
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:13
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="Type", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:13")
  public BoolType boolType() {
    ASTState state = state();
    if (boolType_computed) {
      return boolType_value;
    }
    if (boolType_visited) {
      throw new RuntimeException("Circular definition of attribute Program.boolType().");
    }
    boolType_visited = true;
    state().enterLazyAttribute();
    boolType_value = new BoolType();
    boolType_value.setParent(this);
    boolType_computed = true;
    state().leaveLazyAttribute();
    boolType_visited = false;
    return boolType_value;
  }
/** @apilevel internal */
protected boolean listType_visited = false;
  /** @apilevel internal */
  private void listType_reset() {
    listType_computed = false;
    
    listType_value = null;
    listType_visited = false;
  }
  /** @apilevel internal */
  protected boolean listType_computed = false;

  /** @apilevel internal */
  protected ListType listType_value;

  /**
   * @attribute syn
   * @aspect Type
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:14
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="Type", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:14")
  public ListType listType() {
    ASTState state = state();
    if (listType_computed) {
      return listType_value;
    }
    if (listType_visited) {
      throw new RuntimeException("Circular definition of attribute Program.listType().");
    }
    listType_visited = true;
    state().enterLazyAttribute();
    listType_value = new ListType();
    listType_value.setParent(this);
    listType_computed = true;
    state().leaveLazyAttribute();
    listType_visited = false;
    return listType_value;
  }
/** @apilevel internal */
protected boolean unknownType_visited = false;
  /** @apilevel internal */
  private void unknownType_reset() {
    unknownType_computed = false;
    
    unknownType_value = null;
    unknownType_visited = false;
  }
  /** @apilevel internal */
  protected boolean unknownType_computed = false;

  /** @apilevel internal */
  protected UnknownType unknownType_value;

  /**
   * @attribute syn
   * @aspect Type
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:15
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="Type", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:15")
  public UnknownType unknownType() {
    ASTState state = state();
    if (unknownType_computed) {
      return unknownType_value;
    }
    if (unknownType_visited) {
      throw new RuntimeException("Circular definition of attribute Program.unknownType().");
    }
    unknownType_visited = true;
    state().enterLazyAttribute();
    unknownType_value = new UnknownType();
    unknownType_value.setParent(this);
    unknownType_computed = true;
    state().leaveLazyAttribute();
    unknownType_visited = false;
    return unknownType_value;
  }
/** @apilevel internal */
protected boolean predefinedFunctions_visited = false;
  /** @apilevel internal */
  private void predefinedFunctions_reset() {
    predefinedFunctions_computed = false;
    
    predefinedFunctions_value = null;
    predefinedFunctions_visited = false;
  }
  /** @apilevel internal */
  protected boolean predefinedFunctions_computed = false;

  /** @apilevel internal */
  protected List<FuncDecl> predefinedFunctions_value;

  /**
   * @attribute syn
   * @aspect NameAnalysis
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:6
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:6")
  public List<FuncDecl> predefinedFunctions() {
    ASTState state = state();
    if (predefinedFunctions_computed) {
      return predefinedFunctions_value;
    }
    if (predefinedFunctions_visited) {
      throw new RuntimeException("Circular definition of attribute Program.predefinedFunctions().");
    }
    predefinedFunctions_visited = true;
    state().enterLazyAttribute();
    predefinedFunctions_value = predefinedFunctions_compute();
    predefinedFunctions_value.setParent(this);
    predefinedFunctions_computed = true;
    state().leaveLazyAttribute();
    predefinedFunctions_visited = false;
    return predefinedFunctions_value;
  }
  /** @apilevel internal */
  private List<FuncDecl> predefinedFunctions_compute() {
  		List<FuncDecl> list = new List<FuncDecl>();
  		list.add(new FuncDecl(new IdDecl("read"), new List<IdParam>(), new Block(new List<Stmt>())));
  		List<IdParam> params = new List<IdParam>();
  		params.add(new IdParam(new IdDecl("data")));
  		list.add(new FuncDecl(new IdDecl("print"), params, new Block(new List<Stmt>())));
  		return list;
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
   * @attribute syn
   * @aspect NameAnalysis
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:37
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:37")
  public IdDecl lookup(String name) {
    Object _parameters = name;
    if (lookup_String_visited == null) lookup_String_visited = new java.util.HashSet(4);
    if (lookup_String_values == null) lookup_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (lookup_String_values.containsKey(_parameters)) {
      return (IdDecl) lookup_String_values.get(_parameters);
    }
    if (lookup_String_visited.contains(_parameters)) {
      throw new RuntimeException("Circular definition of attribute Program.lookup(String).");
    }
    lookup_String_visited.add(_parameters);
    state().enterLazyAttribute();
    IdDecl lookup_String_value = lookup_compute(name);
    lookup_String_values.put(_parameters, lookup_String_value);
    state().leaveLazyAttribute();
    lookup_String_visited.remove(_parameters);
    return lookup_String_value;
  }
  /** @apilevel internal */
  private IdDecl lookup_compute(String name) {
  		for (FuncDecl f : getFuncDecls()) {
  			if (f.getIdDecl().getID().equals(name))
  				return f.getIdDecl();
  		}
  		for (FuncDecl f : predefinedFunctions()) {
  			if (f.getIdDecl().getID().equals(name))
  				return f.getIdDecl();
  		}
  		return unknownDecl();
  	}
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:4
   * @apilevel internal
   */
  public UnknownDecl Define_unknownDecl(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unknownDecl();
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:4
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownDecl
   */
  protected boolean canDefine_unknownDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:18
   * @apilevel internal
   */
  public IntType Define_intType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return intType();
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:18
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute intType
   */
  protected boolean canDefine_intType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:20
   * @apilevel internal
   */
  public ListType Define_listType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return listType();
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:20
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute listType
   */
  protected boolean canDefine_listType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:19
   * @apilevel internal
   */
  public BoolType Define_boolType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return boolType();
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:19
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute boolType
   */
  protected boolean canDefine_boolType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:21
   * @apilevel internal
   */
  public UnknownType Define_unknownType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unknownType();
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/UnknownDecl.jrag:21
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownType
   */
  protected boolean canDefine_unknownType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/FunctionCalls.jrag:33
   * @apilevel internal
   */
  public FuncDecl Define_enclosingFunction(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getFuncDeclListNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/FunctionCalls.jrag:35
      int index = _callerNode.getIndexOfChild(_childNode);
      {
        return getFuncDecl(index);
      }
    }
    else {
      return getParent().Define_enclosingFunction(this, _callerNode);
    }
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/FunctionCalls.jrag:33
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingFunction
   */
  protected boolean canDefine_enclosingFunction(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/Errors.jrag:28
   * @apilevel internal
   */
  public Program Define_program(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return this;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/Errors.jrag:28
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute program
   */
  protected boolean canDefine_program(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:26
   * @apilevel internal
   */
  public String Define_getLabel(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getFuncDeclListNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:30
      int index = _callerNode.getIndexOfChild(_childNode);
      {
      		return getFuncDecl(index).getIdDecl().getID()+"_";
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
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:20
   * @apilevel internal
   */
  public String Define_getPrefix(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return "";
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
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:63
   * @apilevel internal
   */
  public IdDecl Define_lookup(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getFuncDeclListNoTransform()) {
      // @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:49
      int index = _callerNode.getIndexOfChild(_childNode);
      {
      		IdDecl decl = getFuncDecl(index).localLookup(name);
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
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:91
   * @apilevel internal
   */
  public boolean Define_isFunction(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
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
    int childIndex = this.getIndexOfChild(_callerNode);
    return null;
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
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:123
   * @apilevel internal
   */
  public Type Define_expectedType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return intType();
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:123
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute expectedType
   */
  protected boolean canDefine_expectedType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:138
   * @apilevel internal
   */
  public boolean Define_isParam(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:138
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isParam
   */
  protected boolean canDefine_isParam(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:146
   * @apilevel internal
   */
  public int Define_getParamIndex(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return -1;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:146
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute getParamIndex
   */
  protected boolean canDefine_getParamIndex(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:157
   * @apilevel internal
   */
  public boolean Define_inExprOf(ASTNode _callerNode, ASTNode _childNode, IdDecl decl) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/NameAnalysis.jrag:157
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inExprOf
   */
  protected boolean canDefine_inExprOf(ASTNode _callerNode, ASTNode _childNode, IdDecl decl) {
    return true;
  }
/** @apilevel internal */
protected boolean Program_errors_visited = false;
  /**
   * @attribute coll
   * @aspect Errors
   * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/Errors.jrag:26
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="Errors", declaredAt="/home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/Errors.jrag:26")
  public Set<ErrorMessage> errors() {
    ASTState state = state();
    if (Program_errors_computed) {
      return Program_errors_value;
    }
    if (Program_errors_visited) {
      throw new RuntimeException("Circular definition of attribute Program.errors().");
    }
    Program_errors_visited = true;
    state().enterLazyAttribute();
    Program_errors_value = errors_compute();
    Program_errors_computed = true;
    state().leaveLazyAttribute();
    Program_errors_visited = false;
    return Program_errors_value;
  }
  /** @apilevel internal */
  private Set<ErrorMessage> errors_compute() {
    ASTNode node = this;
    while (node != null && !(node instanceof Program)) {
      node = node.getParent();
    }
    Program root = (Program) node;
    root.survey_Program_errors();
    Set<ErrorMessage> _computedValue = new TreeSet<ErrorMessage>();
    if (root.contributorMap_Program_errors.containsKey(this)) {
      for (ASTNode contributor : root.contributorMap_Program_errors.get(this)) {
        contributor.contributeTo_Program_errors(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected boolean Program_errors_computed = false;

  /** @apilevel internal */
  protected Set<ErrorMessage> Program_errors_value;

}
