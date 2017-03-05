package stream.streamUsage;

import stream.streamUsage.examplePeople.People;
import stream.streamUsage.examplePeople.Sex;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Usage of Min and Max.
 *
 * Created by dkorolev on 1/5/2017.
 */
public class StreamUsageMinMax {
    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("a1", "a2", "a3", "a1");

        People alex = new People("Alex", 16, Sex.MAN);
        People tom = new People("Tom", 23, Sex.MAN);
        People helen = new People("Helen", 42, Sex.WOMAN);
        People greg = new People("Greg", 69, Sex.MAN);
        Collection<People> peoples = Arrays.asList(alex, tom, helen, greg);


        //find max among strings
        System.out.println(stringList.stream().max(String::compareTo).get());

        //find min among strings
        System.out.println(stringList.stream().min(String::compareTo).get());

        //find a person with max age
        System.out.println(peoples.stream().max((p1,p2)->p1.getAge().compareTo(p2.getAge())).get());

        //find person with min age
        System.out.println(peoples.stream().min((p1,p2)->p1.getAge().compareTo(p2.getAge())).get());
    }
}
