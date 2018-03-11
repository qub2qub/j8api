package lambdasinaction.chap9.multi.inherit;

public class DiamondSubInterfaces {
    public static void main(String... args) {
        new D1().hello();
        new D2().hello();
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
    interface C extends A {
        default void hello() {
            System.out.println("CCC");
        }
    }
    static class D1 implements A, B {
    }
    static class D2 implements  C {
    }
}
