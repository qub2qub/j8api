package lambda.additionalFunctInterfaces;

import lambda.common.Person;

import java.util.List;
import java.util.function.Function;

/**
 * Test with function interface.
 */
public class TestFunctionInt {

    public static void main(String[] args) {

        List<Person> list = Person.createShortList();

        for (Person person : list) {
            person.printPersonDetails();
        }


        Function<Person, String> function = p -> "Name:" + p.getName() + "Age:" + p.getAge();
        for (Person person : list) {
            System.out.println(
                    person.printCustom(function)
            );
        }

    }
}
