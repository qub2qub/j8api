package lambdasinaction.chap6;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static lambdasinaction.chap6.Dish.menu;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class Summarizing {
  
  public static void main(String... args) {
    System.out.println("Nr. of dishes: " + menu.stream().collect(counting()));
    
    System.out.println("1 The most caloric dish is: " +
      menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get());
    
    Comparator<Dish> dishCaloriesComparator = comparingInt(Dish::getCalories);
    BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
    System.out.println("2 The most caloric dish is: " + menu.stream().collect(reducing(moreCaloricOf)).get());
  
    Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(comparingInt(Dish::getCalories)));
    System.out.println("3 The most caloric dish is: " + mostCalorieDish.orElseGet(null));
    
    System.out.println("1 Total calories in menu: " + menu.stream().collect(summingInt(Dish::getCalories)));
    System.out.println("2 Total calories in menu: " + menu.stream().mapToInt(Dish::getCalories).sum());
    
    
    System.out.println("Average calories in menu: " + menu.stream().collect(averagingInt(Dish::getCalories)));
    System.out.println("Menu statistics: " + menu.stream().collect(summarizingInt(Dish::getCalories)));
    
    System.out.println("1 Short menu: " + menu.stream().map(Dish::getName).collect(joining()));
//    System.out.println("2 Short menu: " + menu.stream().collect(joining()) ); // if Dish had toString()
    System.out.println("3 Short menu comma separated: " + menu.stream().map(Dish::getName).collect(joining(", ")));
    
    System.out.println("1 Count menu: " + menu.stream().collect(counting()));
    System.out.println("2 Count menu: " + menu.stream().count());
  }
  
  
}
