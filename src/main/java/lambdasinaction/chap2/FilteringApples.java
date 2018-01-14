package lambdasinaction.chap2;

import java.util.*;

public class FilteringApples {
  
  interface ApplePredicate {
    boolean test(Apple a);
  }
  
  public static void main(String... args) {
    List<Apple> inventory = Arrays.asList(
      new Apple(80, "green"),
      new Apple(155, "green"),
      new Apple(120, "red"));
    System.out.println("greenApples = " +              filterApplesByColor(inventory, "green"));
    System.out.println("redApples = " +                  filterApplesByColor(inventory, "red"));
    // добавили предикат и теперь в метод "фильтр" можно передавать различные его реализации
    // In other words, you’ve parameterized the behavior of the filterApples method!
    System.out.println("greenApples2 = " +            filter(inventory, new AppleColorPredicate()));
    System.out.println("heavyApples = " +             filter(inventory, new AppleWeightPredicate()));
    System.out.println("redAndHeavyApples = " + filter(inventory, new AppleRedAndHeavyPredicate()));
    // можно анонимным классом, чуть короче
    List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
      public boolean test(Apple a) {
        return a.getColor().equals("red");
      }
    });
    System.out.println("redApples2=" + redApples2);
    // или то же самое лямбдой
    System.out.println("redApples3=" + filter(inventory, a -> a.getColor().equals("red")));
    TreeMap<Object, Object> m = new TreeMap<>();
    Comparator<? super Object> c = m.comparator();
    c.hashCode();
//    c.equals();
//    c.toString()
  }
  
  /**
   * ТОЖЕ ВАЖНЫЙ МЕТОД, Т.К. В НЁМ ЕСТЬ ЛОГИКА ПРОХОДА ПО КОЛЛЕКЦИИ
   * И ПРИМЕНЕНИЕ ПЕРЕДАННОГО ПОВЕДЕНИЯ К КАЖДОМУ ЕЁ ЭЛЕМЕНТУ
   */
  public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }
  
  public static List<Apple> filterGreenApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if ("green".equals(apple.getColor())) {
        result.add(apple);
      }
    }
    return result;
  }
  
  public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getColor().equals(color)) {
        result.add(apple);
      }
    }
    return result;
  }
  
  public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getWeight() > weight) {
        result.add(apple);
      }
    }
    return result;
  }
  
  static class AppleWeightPredicate implements ApplePredicate {
    
    public boolean test(Apple apple) {
      return apple.getWeight() > 150;
    }
  }
  
  static class AppleColorPredicate implements ApplePredicate {
    
    public boolean test(Apple apple) {
      return "green".equals(apple.getColor());
    }
  }
  
  static class AppleRedAndHeavyPredicate implements ApplePredicate {
    
    public boolean test(Apple apple) {
      return "red".equals(apple.getColor())
        && apple.getWeight() > 150;
    }
  }
}