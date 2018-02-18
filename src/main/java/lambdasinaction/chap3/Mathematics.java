package lambdasinaction.chap3;

import java.awt.AWTEventMulticaster;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Predicate;

/**
 * Created on 2018-01-15
 */
public class Mathematics
{
  private static DoubleFunction<Double> f = (x) -> x+10;
  
  private static double integrate(DoubleFunction<Double> f, double a, double b) {
    return ( f.apply( a ) + f.apply( b ) ) * ( b - a ) / 2.0;
  }
  
  public static void main(String[] args) {
    double res = integrate(f, 3, 7);
    System.out.println("res = " + res);
  
    // Predicate has a boolean return
    List<String> list = new ArrayList<>();
//    Predicate<String> p = s -> list.add(s);
// Consumer has a void return
    Consumer<String> b = s -> list.add(s);
    b =  s -> System.out.println("aa");
    b =  s -> "aa".trim();
  }
  
}
