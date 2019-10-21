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
 * @ast class
 * @aspect FunctionCalls
 * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/FunctionCalls.jrag:3
 */
public class FuncDeclComp extends java.lang.Object implements Comparable<FuncDeclComp> {
  
  public FuncDeclComp(FuncDecl decl) {
  funcDecl = decl;
  }

  
  public FuncDecl funcDecl;

  
  public int compareTo(FuncDeclComp other) {
    return funcDecl.getIdDecl().getID().compareTo(other.funcDecl.getIdDecl().getID());
  }

  
  public String toString() {
    return funcDecl.getIdDecl().getID();
  }


}
