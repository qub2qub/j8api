package lambda.additions.looping;

import lambda.common.Person;

import java.util.List;

/**
 * Looping through method of the collection.
 *
 * Created by dkorolev on 7/24/2016.
 */
public class TestForEachLamdaStyle {
    public static void main(String[] args) {
        List<Person> list = Person.createShortList();

        list.forEach(p -> System.out.println("Name:"+p.getName()+" Age:"+p.getAge()));
        System.out.println();
        list.forEach(System.out::println);
        System.out.println();
        list.forEach(Person::printPersonDetails);
    }
}
