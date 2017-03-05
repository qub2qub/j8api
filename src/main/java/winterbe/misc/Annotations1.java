package winterbe.misc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;

/**
 * @author Benjamin Winterberg
 */
public class Annotations1 {

    @Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
    @interface MyAnnotation {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Hints {
        Hint[] value();
    }

    @Repeatable(Hints.class)
    @Retention(RetentionPolicy.RUNTIME)
    @interface Hint {
        String value();
    }

    @Hint("hint1")
    @Hint("hint2")
    class Person {}

    @Hints({@Hint("hint1"), @Hint("hint2")})
    class Person2 {}

    public static void main(String[] args) {
        Hint hint = Person.class.getAnnotation(Hint.class);
        System.out.println("hint = " + hint);   // null

        Hints hints1 = Person.class.getAnnotation(Hints.class);
        System.out.println(hints1.value().length);  // 2
        System.out.println("hints1 = " + hints1);

        Hint[] hints2 = Person.class.getAnnotationsByType(Hint.class);
        System.out.println(hints2.length);  // 2
        System.out.println("hints2 = " + Arrays.toString(hints2));

        Hint[] hints3 = Person2.class.getAnnotationsByType(Hint.class);
        System.out.println(hints3.length);  // 2
        System.out.println("hints3 = " + Arrays.toString(hints3));

    }
}