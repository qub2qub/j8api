package lambdasinaction.chap2;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2018-01-13
 */
public class PrettyPrintApple {
  
  public interface AppleFormatter {
    String accept(Apple a);
  }
  
  public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
    for (Apple apple : inventory) {
      System.out.println(formatter.accept(apple));
    }
  }
  
  public static void main(String[] args) {
    List<Apple> inventory = Arrays.asList(
      new Apple(80, "green"),
      new Apple(155, "green"),
      new Apple(120, "red"));
  
    prettyPrintApple(inventory, new AppleFancyFormatter());
    prettyPrintApple(inventory, new AppleSimpleFormatter());
    prettyPrintApple(inventory, a -> String.format("Apple: color=%s, weight=%s", a.getColor(), a.getWeight()));
    
  }
  
  public static class AppleFancyFormatter implements AppleFormatter {
    
    public String accept(Apple apple) {
      String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
      return "A " + characteristic + " " + apple.getColor() + " apple";
    }
  }
  
  public static class AppleSimpleFormatter implements AppleFormatter {
    
    public String accept(Apple apple) {
      return "An apple of " + apple.getWeight() + "g";
    }
  }
  
}
