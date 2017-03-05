package winterbe.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Benjamin Winterberg
 */
public class Streams1_Basic {

    public static void main(String[] args) {

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        // filtering
        System.out.println("\n_____________ filtering_1 _____________ ");
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);
        // "aaa2", "aaa1"

        // sorting
        System.out.println("\n_____________ sorting_1 _____________ ");
        List<String> result = new ArrayList<>(stringCollection.size());
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("b"))
                .forEach(result::add);
        System.out.println("res = " + result);
        // "aaa1", "aaa2"
        System.out.println("src = " + stringCollection);

        // mapping
        result.clear();
        System.out.println("\n_____________ mapping_1 _____________ ");
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(result::add);
        System.out.println("res2 = " + result);

        // "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"


        // matching
        System.out.println("\n_____________ matching _____________ ");
        boolean anyStartsWithA = stringCollection
                .stream()
                .anyMatch((s) -> s.startsWith("a"));

        System.out.println("anyStartsWithA="+anyStartsWithA);      // true

        boolean allStartsWithA = stringCollection
                .stream()
                .allMatch((s) -> s.startsWith("a"));

        System.out.println("allStartsWithA="+allStartsWithA);      // false

        boolean noneStartsWithZ = stringCollection
                .stream()
                .noneMatch((s) -> s.startsWith("z"));

        System.out.println("noneStartsWithZ="+noneStartsWithZ);      // true


        // counting
        System.out.println("\n_____________ counting _____________ ");
        long startsWithB = stringCollection
                .stream()
                .filter((s) -> s.startsWith("b"))
                .count();

        System.out.println("counting __ startsWithB="+startsWithB);    // 3


        // reducing
        System.out.println("\n_____________ reducing _____________ ");
        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
        // "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"


    }

}
