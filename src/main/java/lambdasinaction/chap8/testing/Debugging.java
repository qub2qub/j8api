package lambdasinaction.chap8.testing;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 2018-03-10
 */
public class Debugging {
    public static void main(String[] args) {
    
        List<XYPoint> points = Arrays.asList(new XYPoint(4, 5), new XYPoint(11, 22), null);
//        points.stream().map(XYPoint::getX).forEach(System.out::println);
//        points.stream().map(p -> p.getX()).forEach(System.out::println);
    
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        numbers.stream().map(Debugging::divideByZero).forEach(System
            .out::println);
    }
    
    public static int divideByZero(int n){
        return n / 0;
    }
}
