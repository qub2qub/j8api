package lambdasinaction.chap6;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;
import static lambdasinaction.chap6.Dish.menu;

public class Reducing {
  
  public static void main(String... args) {
    System.out.println("Total calories in menu:");
    System.out.println("-1 mostCalorieDish="+  menu.stream().collect(reducing(
      (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).orElse(null));
    System.out.println("0 "+  menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j)));
    
    System.out.println("1 "+  menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j)));
    System.out.println("2 " + menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum)));
    System.out.println("3 int=" + menu.stream().map(Dish::getCalories).reduce(0, Integer::sum));
    System.out.println("4 optional=" + menu.stream().map(Dish::getCalories).reduce(Integer::sum).orElse(-1));
    System.out.println("5 mapToInt=" + menu.stream().mapToInt(Dish::getCalories).sum());
  
  
    String shortMenu1 = menu.stream().map(Dish::getName).collect(joining());
//    String shortMenu11 = menu.stream().collect(joining());
    String shortMenu2 = menu.stream().map(Dish::getName)
      .collect( reducing( (s1, s2) -> s1 + s2 ) ).get();
//    String shortMenu3 = menu.stream() // WRONG !!
//      .collect( reducing( (d1, d2) -> d1.getName() + d2.getName() ) ).get();
    String shortMenu4 = menu.stream()
      .collect( reducing( "", Dish::getName, (s1, s2) -> s1 + s2 ) );
    String shortMenu5 = menu.stream().map(Dish::getName)
      .reduce("", (s1, s2) -> s1 + s2);
    
  }
  
}