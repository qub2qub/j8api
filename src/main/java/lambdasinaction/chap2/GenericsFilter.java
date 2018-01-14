package lambdasinaction.chap2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 2018-01-13
 */
public class GenericsFilter {
  public interface GenericRule<T>{
    boolean test(T t);
  }
  public static <T> List<T> filter(List<T> list, GenericRule<T> rule){
    List<T> result = new ArrayList<>();
    for(T e: list){
      if(rule.test(e)){
        result.add(e);
      }
    }
    return result;
  }
  
  public static void main(String[] args) {
    List<Apple> inventory = Arrays.asList(
      new Apple(80, "green"),
      new Apple(155, "green"),
      new Apple(120, "red"));
    List<Integer> numbers = Arrays.asList(1, 9, 8, 5);
  
    List<Apple> redApples = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
    List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
  
    System.out.println("redApples = " + redApples);
    System.out.println("evenNumbers = " + evenNumbers);
  
    inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
    System.out.println("inventory = " + inventory);
//    inventory.add(new Apple(50, "blue"));
    
  }
}
