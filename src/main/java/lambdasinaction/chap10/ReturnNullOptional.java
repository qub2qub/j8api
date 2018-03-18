package lambdasinaction.chap10;

import com.sun.istack.internal.Nullable;
import java.util.Optional;

/**
 * Created on 2018-03-17
 */
public class ReturnNullOptional {
    
    static Optional<String> get(@Nullable Object obj) {
        if (obj instanceof String) {
            return Optional.of((String)obj);
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(get(null));
        System.out.println(get("111"));
        ReturnNullOptional returnNullOptional = new ReturnNullOptional();
        Optional<Integer> integer = Optional.<String>empty().map(returnNullOptional::use2);
//        Optional<Integer> integer = Optional.<String>empty().map(ReturnNullOptional::use);
//        Optional<Integer> integer = Optional.<String>empty().map(String::length);
        System.out.println(integer);
    }
    
    public static int use(String str) {
        return str.length();
    }
    int use2(String str) {
        return str.length();
    }
}
