package lambdasinaction.chap9.api2;

/**
 * Created on 2018-03-11
 */
public class RunGame {
    public static void main(String[] args) {
        Monster m = new Monster();
        m.setRelativeSize(120, 120);
        m.rotateBy(180);
        m.moveVertically(10);
    
        Sun sun = new Sun();
        sun.rotateBy(180);
    }
}
