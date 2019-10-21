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
 * @declaredat /home/jens/school/Compilers/Compilers/FunCompiler/src/jastadd/ActivationRecord.jrag:163
 */
public class ActivationRecord extends java.lang.Object {
  
    Map<String,Integer> map;

  
    String stamp;

  
    static Scanner scan = new Scanner(System.in);

  

    public ActivationRecord(){
      map = new HashMap<String,Integer>();
    }

  

    public int get(String id){
      return map.get(id);
    }

  

    public void put (String id, int val){
      map.put(id,val);
    }

  

    public void print(){
      for(String Key : map.keySet() ){
        System.out.println(Key + ", " + map.get(Key));
      }
    }


}
