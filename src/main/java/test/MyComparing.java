package test;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Denis on 08-03-2017
 */
public class MyComparing {

    public static void main(String[] args) {
        List<Pers> list = new ArrayList<>();
        list.add(new Pers("BBB",  66));
        list.add(new Pers("ABC",  123));
        list.add(new Pers("DDD",  99));
        list.add(new Pers("CCC",  77));
        list.add(new Pers("AAA",  77));
        list.add(new Pers("CCC",  66));

        runComparing(list);
    }

    @Immutable
    static class Pers {
        private final String name;
        private final int age;

        public Pers(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "{" +name +  "=" + age + '}';
        }
    }

    private static void runComparing(List<Pers> list) {
        System.out.println(list);
        list.sort(Comparator.comparing(Pers::getName));
        System.out.println(list);
        list.sort(Comparator.comparing(Pers::getName).thenComparing(Pers::getAge));
        System.out.println(list);
        // ***************************************************
        Collections.shuffle(list);
        System.out.println("*****************\n"+list);
        list.sort(Comparator.comparing(Pers::getAge));
        System.out.println(list);
        list.sort(Comparator.comparing(Pers::getAge).thenComparing(Pers::getName));
        System.out.println(list);
    /*
[{BBB=66}, {ABC=123}, {DDD=99}, {CCC=77}, {AAA=77}, {CCC=66}]
[{AAA=77}, {ABC=123}, {BBB=66}, {CCC=77}, {CCC=66}, {DDD=99}]
[{AAA=77}, {ABC=123}, {BBB=66}, {CCC=66}, {CCC=77}, {DDD=99}]
*****************
[{CCC=66}, {DDD=99}, {BBB=66}, {ABC=123}, {CCC=77}, {AAA=77}]
[{CCC=66}, {BBB=66}, {CCC=77}, {AAA=77}, {DDD=99}, {ABC=123}]
[{BBB=66}, {CCC=66}, {AAA=77}, {CCC=77}, {DDD=99}, {ABC=123}]
     */
    }

}
