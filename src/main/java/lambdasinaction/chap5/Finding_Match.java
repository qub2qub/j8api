package lambdasinaction.chap5;

import static lambdasinaction.chap4.Dish.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lambdasinaction.chap4.Dish;

public class Finding_Match {
  
  public static void main(String... args) {
    
    if (menu.stream().anyMatch(Dish::isVegetarian)) {
      System.out.println("Vegetarian friendly");
    }
    
    System.out.println("Healthy1 = " + menu.stream().allMatch(dish -> dish.getCalories() < 1000));
    System.out.println("Healthy2 = " + menu.stream().noneMatch(dish -> dish.getCalories() >= 1000));
    
    Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();
    dish.ifPresent(d -> System.out.println("Vegetarian = "+d.getName()));
    menu.stream().filter(d -> !d.isVegetarian()).findAny()
      .ifPresent(d -> System.out.println("Meat = " + d.getName()));
  
    List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
    //firstSquareDivisibleByThree
    Optional<Integer> first = someNumbers.stream()
      .map(x -> x * x)
      .filter(x -> x % 3 == 0)
      .findFirst();
//      .ifPresent( n -> System.out.println(n.toString()))
    System.out.println(first.orElse(-1));
    
  }
  
}
