package lambdasinaction.chap3;

import java.util.Objects;
import java.util.function.Predicate;

public class Foo {
    static String str = "a";
//    public int  myMethod(int param) { return 1; }
    public char myMethod(int param) { return 'a';}
    int date = 6;
    
    public static void main(String[] args) {
    
        Object obj = new Object();
        
    
        int portNumber = 1337;
        Foo foo = new Foo();
        Runnable r = () -> System.out.println(portNumber);
        Runnable r2 = () -> System.out.println(foo.date);
        Runnable r3 = () -> System.out.println(Foo.str);
        Runnable r4 = () -> System.out.println(obj);
//        Runnable r5 = () -> System.out::println;
//        portNumber = 2222;
    
//        Predicate<Object> pred = isEqual(null);
        Predicate<Object> pred = isEqual("123");
        System.out.println(pred.test("123"));
        "a".compareToIgnoreCase("b");
    }
    
    static <T> Predicate<T> isEqual(Object targetRef) {
//        return (null == targetRef)
//            ? Objects::isNull
//            : targetRef::equals;
    
        if (targetRef == null) {
            return (t) -> Objects.isNull(t);
        } else {
            return (t) -> targetRef.equals(t);
        }
        
        /*return new Predicate<T>() {
            @Override
            public boolean test(T t) {
                if (targetRef == null) {
                    return Objects.isNull(t);
                } else {
                    return targetRef.equals(t);
                }
            }
        };*/
    }
}