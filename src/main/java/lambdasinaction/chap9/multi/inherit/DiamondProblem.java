package lambdasinaction.chap9.multi.inherit;

/**
 * Created on 2018-03-11
 */
public class DiamondProblem {
    public static void main(String... args) {
        new D().hello();
    }
    public interface A{
        default void hello(){
            System.out.println("Hello from A");
        }
    }
    public interface B extends A { }
    public interface C extends A {
//        void hello();
    }
    public static class D implements B, C {
//        public void hello() { } // implement abstract method from C
    }
}
