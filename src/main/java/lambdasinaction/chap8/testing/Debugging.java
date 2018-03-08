package lambdasinaction.chap8.testing;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

public class Debugging {
  
  public static void main(String[] args) throws Exception {
  
    testMoveRightBy();
    
    List<XYPoint> points = Arrays.asList(new XYPoint(12, 2), null);
    points.stream().map(XYPoint::getX).forEach(System.out::println); // map(p -> p.getX())
  }
  
  public static void testMoveRightBy() throws Exception {
    XYPoint p1 = new XYPoint(5, 5);
    XYPoint p2 = p1.moveRightBy(10);
    
    assertEquals(15, p2.getX());
    assertEquals(5, p2.getY());
  }
  
}
