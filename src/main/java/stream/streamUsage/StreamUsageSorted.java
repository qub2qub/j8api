package stream.streamUsage;

import stream.streamUsage.examplePeople.People;
import stream.streamUsage.examplePeople.Sex;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Usage of Sorted.
 *
 * Created by dkorolev on 1/5/2017.
 */
public class StreamUsageSorted {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("a1", "a4", "a3", "a2", "a1", "a4");

        People alex = new People("Alex", 16, Sex.MAN);
        People tom = new People("Tom", 23, Sex.MAN);
        People helen = new People("Helen", 42, Sex.WOMAN);
        People greg = new People("Greg", 69, Sex.MAN);
        Collection<People> peoples = Arrays.asList(alex, tom, helen, greg);


        //sort collection according to alphabet
        System.out.println(stringList.stream().sorted().collect(Collectors.toList()));

        //sort in reverse order
        System.out.println(stringList.stream().sorted((s,s1)->s1.compareTo(s))
                .collect(Collectors.toList()));

        //sort alphabetically without duplicates
        System.out.println(stringList.stream().sorted().distinct()
                .collect(Collectors.toList()));

        //sort reverse without duplicates
        System.out.println(stringList.stream().sorted((s,s1)->s1.compareTo(s))
                .distinct().collect(Collectors.toList()));


        //sort people collection in reverse alphabet order by Name
        System.out.println(peoples.stream().sorted((p1,p2)->p2.getName().compareTo(p1.getName()))
                .collect(Collectors.toList()));

        //sort people according to Gender, then by Age
        System.out.println(peoples.stream()
                .sorted((o1,o2)-> o1.getSex() != o2.getSex()? o1.getSex().
                        compareTo(o2.getSex()): o1.getAge().compareTo(o2.getAge()))
                .collect(Collectors.toList()));


    }
}
