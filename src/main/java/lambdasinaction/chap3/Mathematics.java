package lambdasinaction.chap3;

import java.util.function.DoubleFunction;

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
  }
  
}
