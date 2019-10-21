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
 * @aspect ActivationRecord
 * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/ActivationRecord.jrag:149
 */
public class ReturnException extends Exception {
  
    int value;

  
    public ReturnException(int value) {
     this.value = value;
    }

  
    public int getValue() {
      return value;
    }


}
