package lambdasinaction.chap8.chainOfResponsibilit;

import java.util.function.Function;
import java.util.function.UnaryOperator;


public class RunChain {
  
  public static void main(String[] args) {
    ProcessingObject<String> p1 = new HeaderTextProcessing();
    ProcessingObject<String> p2 = new SpellCheckerProcessing();
    p1.setSuccessor(p2);
    String result1 = p1.handle("Aren't labdas really sexy?!!");
    System.out.println(result1);
    
    UnaryOperator<String> headerProcessing =
      (String text) -> "From Raoul, Mario and Alan: " + text;
    UnaryOperator<String> spellCheckerProcessing =
      (String text) -> text.replaceAll("labda", "lambda");
    Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
    String result2 = pipeline.apply("Aren't labdas really sexy?!!");
    System.out.println(result2);
  }
}


