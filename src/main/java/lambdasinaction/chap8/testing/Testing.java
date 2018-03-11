package lambdasinaction.chap8.testing;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import lambdasinaction.chap2.GenericsFilter;

public class Testing {
  
  public static void main(String[] args) throws Exception {
    testMoveRightBy();
    testComparingTwoPoints();
    testMoveAllPointsRightBy();
    testFilter();
  }
  
  public static void testMoveRightBy() throws Exception {
    XYPoint p1 = new XYPoint(5, 5);
    XYPoint p2 = p1.moveRightBy(10);
    
    assertEquals(15, p2.getX());
    assertEquals(5, p2.getY());
  }
  
  public static void testComparingTwoPoints() throws Exception {
    XYPoint p1 = new XYPoint(10, 15);
    XYPoint p2 = new XYPoint(10, 20);
    int result = XYPoint.compareByXAndThenY.compare(p1 , p2);
    assertEquals(-1, result);
  }
  
  public static void testMoveAllPointsRightBy() throws Exception{
    List<XYPoint> points =
        Arrays.asList(new XYPoint(5, 5), new XYPoint(10, 5));
    List<XYPoint> expectedPoints =
        Arrays.asList(new XYPoint(15, 5), new XYPoint(20, 5));
    
    List<XYPoint> newPoints = XYPoint.moveAllPointsRightBy(points, 10);
    assertEquals(expectedPoints, newPoints);
  }
  
  public static void testFilter() throws Exception{
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
    List<Integer> even = GenericsFilter.filter(numbers, i -> i % 2 == 0);
    List<Integer> smallerThanThree = GenericsFilter.filter(numbers, i -> i < 3);
    
    assertEquals(Arrays.asList(2, 4), even);
    assertEquals(Arrays.asList(1, 2), smallerThanThree);
  }
  
  
  
}
