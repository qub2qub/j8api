package lambdasinaction.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * Created on 2018-01-14
 */
public class UtilFunctions {
  
  public static void main(String[] args) {
    Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
    System.out.println(filter(Arrays.asList("", "df", "tyh", "", "y", "oo"), nonEmptyStringPredicate));
    forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.print(i * 10 + " "));
    System.out.println();
    System.out.println( map(Arrays.asList("lambdas", "in", "action"), String::length));
  
    IntPredicate evenNumbers = (int i) -> i % 2 == 0; // true (no boxing)
    System.out.println(evenNumbers.test(1000));
    Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1; // false (boxing)
    System.out.println(oddNumbers.test(1000));
    
    
  }
  
  public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
    List<R> result = new ArrayList<>();
    for (T s : list) {
      result.add(f.apply(s));
    }
    return result;
  }
  
  public static <T> void forEach(List<T> list, Consumer<T> c) {
    for (T i : list) {
      c.accept(i);
    }
  }
  
  public static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> results = new ArrayList<>();
    for (T s : list) {
      if (p.test(s)) {
        results.add(s);
      }
    }
    return results;
  }
}
