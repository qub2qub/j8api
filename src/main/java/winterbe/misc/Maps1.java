package winterbe.misc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Benjamin Winterberg
 */
public class Maps1 {

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(5, "Old_5");

        for (int i = 0; i < 10; i++) {
            String ifAbsent = map.putIfAbsent(i, "value_" + i);
            System.out.println(i+"  =  " + ifAbsent);
        }

        System.out.println("__ map.forEach( (id, val) -> DO ) __");
        map.forEach((id, val) -> System.out.println(id+"  =  " + val));


        map.computeIfPresent(3, (num, val) -> "["+val + num+"]"); // [value_33]
        System.out.println("map.get(3)  =  " + map.get(3));             // val33

        map.computeIfPresent(9, (num, val) -> null);
        System.out.println("map.containsKey(9)  =  " + map.containsKey(9));     // false

        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println("map.containsKey(23)  =  " + map.containsKey(23));    // true

        map.computeIfAbsent(3, num -> "bam");
        System.out.println("map.get(3)_v2  =  " + map.get(3));             // val33

        System.out.println("map.getOrDefault(42)  =  " + map.getOrDefault(42, "not found"));      // not found

        System.out.println("map.remove(3, \"val3\") = " + map.remove(3, "val3"));
        System.out.println("map.get(3)_v3  =  " + map.get(3));             // val33

        System.out.println("map.remove(3, \"[value_33]\")  =  " + map.remove(3, "[value_33]"));
        System.out.println("map.get(3)_v4  =  " + map.get(3));             // null

        /*
        Merge either put the key/value into the map if no entry for the key exists,
        or the merging function will be called to change the existing value.
         */
        // засетит новое зн-е new19value
        map.merge(19, "new19value", String::concat);
        System.out.println("map.get(19)  =  " + map.get(19));

        // ничего не будет делать, оставить старое val9
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println("map.get(9)  =  " + map.get(9));

        // смержит новое к старому = val9concat
        map.merge(9, "newConcat", String::concat);
        System.out.println("map.get(9)  =  " + map.get(9));
    }

}