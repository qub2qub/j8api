package lambdasinaction.chap3;

import java.util.Objects;
import java.util.function.Predicate;

public class Foo {
//    public int  myMethod(int param) { return 1; }
    public char myMethod(int param) { return 'a';}
    
    public static void main(String[] args) {
        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
//        portNumber = 2222;
    
//        Predicate<Object> pred = isEqual(null);
        Predicate<Object> pred = isEqual("123");
        System.out.println(pred.test("123"));
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