package lambdasinaction.chap9.multi.inherit;

/**
 * Created on 2018-03-11
 */
public class DiamondClassNInterface {
    public static void main(String... args) {
        new D1().hello();
        new D2().hello();
        new D3().hello();
    }
    
    interface A {
        default void hello() {
            System.out.println("AAA");
        }
    }
    interface B extends A {
        default void hello() {
            System.out.println("BBB");
        }
    }
    static class C1 implements B {
        public void hello() {
            System.out.println("Class - CCC");
        }
    }
    static class C2 implements B { }
    static abstract class C3 implements A {
        public abstract void hello();
    }
    
    static class D1 extends C1 implements A { }
    static class D2 extends C2 implements A { }
    static class D3 extends C3 {
        @Override
        public void hello() {
            System.out.println("D3 D3 D3");
        }
    }
}
