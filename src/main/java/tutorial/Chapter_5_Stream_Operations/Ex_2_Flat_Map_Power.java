package tutorial.Chapter_5_Stream_Operations;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *  29-May-17.
 */
public class Ex_2_Flat_Map_Power {
    public static void main(String[] args) {

        Map<String,Integer> backendSkillMatrix = new HashMap<>();
        backendSkillMatrix.put("Java", 3);
        backendSkillMatrix.put("Scala", 2);
        backendSkillMatrix.put("Kotlin", 2);
        Developer backendDev = new Developer("Teodor", backendSkillMatrix);


        Map<String,Integer> frontendSkillMatrix = new HashMap<>();
        frontendSkillMatrix.put("React", 80);
        frontendSkillMatrix.put("Angular 10", 1);
        frontendSkillMatrix.put("Kotlin", 4);
        Developer frontendDev = new Developer("Dinesh", frontendSkillMatrix);


        List<Developer> projectTeam = new ArrayList<>();
        projectTeam.add(backendDev);
        projectTeam.add(frontendDev);

        // Step 1: Print all skills with level more than 1

        List<String> result = projectTeam.stream()
            // получаем сет из мапы скилов для каждого работника Set<Map.Entry<K, V>>
                .map(e -> e.getSkillMatrix().entrySet())
            // из списка Entry-в делаем стрим
                .flatMap(entrySet -> entrySet.stream())
            .peek(System.out::println) // отладочно - выводим то что проходит
            // на каждый Entry<K, V> добавляем логику Entry<String, int>
                .filter(item -> item.getValue() > 1) // значение скила
                .map(item -> item.getKey()) // название скила
                .distinct()
                .collect(Collectors.toList());

        System.out.println("skill > 1 list:\n"+result);


        int sum1 = Stream.of(1,2,3,4,5).mapToInt(i -> i).sum();
        int sum2 = Stream.of(1,2,3,4,5).mapToInt(Integer::intValue).sum();
        Integer sum3 = Stream.of(1,2,3,4,5).reduce(0, Integer::sum);
        Integer sum4 = Stream.of(1,2,3,4,5).collect(Collectors.summingInt(Integer::intValue));
        IntSummaryStatistics sum5 = Stream.of(1, 2, 3, 4, 5).collect(Collectors.summarizingInt(Integer::intValue));

        System.out.println("sum1 = " + sum1);
        System.out.println("sum2 = " + sum2);
        System.out.println("sum3 = " + sum3);
        System.out.println("sum4 = " + sum4);
        System.out.println("sum5 = " + sum5);


        // Step 2: Specialized FlatMap: sum of distinct values only
        String[][] arrayOfArrays = {{"1", "2"}, {"3", "4"}, {"1", "4", "1"}};

        LongStream longStream = Arrays.stream(arrayOfArrays)
                .flatMapToLong(innerArray -> Arrays.stream(innerArray)
                        .mapToLong(Long::new).distinct()); // <--- it doesn't solve the problem

/*        // Step 3: Possible solution
        longStream = Arrays.stream(arrayOfArrays)
                .flatMapToLong(innerArray -> Arrays.stream(innerArray)
                        .mapToLong(Long::new))
                .distinct();*/

        // Step 4: peek for debug purposes
        System.out.println(longStream.peek(System.out::println).sum());

        // Step 5: Print out all Strings by chars
        Stream.of("Scala", "Java",  "Kotlin")
                .flatMap(name ->  IntStream.range(0, name.length())
                        .mapToObj(name::charAt))
                .forEach(System.out::print);

    }
}
