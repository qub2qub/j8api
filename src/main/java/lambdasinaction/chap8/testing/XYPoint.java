package lambdasinaction.chap8.testing;

import static java.util.Comparator.comparing;

import java.util.Comparator;

public class XYPoint {
    
    public final static Comparator<XYPoint> compareByXAndThenY =
      comparing(XYPoint::getX).thenComparing(XYPoint::getY);
    
    private final int x;
    private final int y;

    public XYPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public XYPoint moveRightBy(int x){
        return new XYPoint(this.x + x, this.y);
    }
}