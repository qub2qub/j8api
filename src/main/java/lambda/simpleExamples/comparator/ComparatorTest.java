package lambda.simpleExamples.comparator;

import lambda.common.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dkorolev on 7/24/2016.
 */
public class ComparatorTest {

    public static void main(String[] args) {

        List<Person> personList = Person.createShortList();

        //sort with inner class
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });

        System.out.println("Sorted old style:");
        for (Person person : personList) {
            System.out.println(person.getName());
        }


        //New lambda style
        System.out.println("Sorted ASC lamda style:");
        Collections.sort(personList,(Person p1, Person p2) -> p1.getName().compareTo(p2.getName()));
        for (Person person : personList) {
            System.out.println(person.getName());
        }


        System.out.println("Sorted DESC lamda style:");
        Collections.sort(personList, (p1,p2)-> p2.getName().compareTo(p1.getName()));
        for (Person person : personList) {
            System.out.println(person.getName() );
        }


        System.out.println("Sorted by age:");
        Collections.sort(personList, (p1, p2) -> p1.getAge() > p2.getAge() ? 1 : -1);
        for (Person person : personList) {
            System.out.println(person.getName()+":" +person.getAge());
        }
    }
}
