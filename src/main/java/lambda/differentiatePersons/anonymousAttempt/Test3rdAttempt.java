package lambda.differentiatePersons.anonymousAttempt;

import lambda.common.GenderEnum;
import lambda.common.Person;

import java.util.List;

/**
 * "-": ugly with instantiation, hard to read.
 *
 * Created by dkorolev on 7/24/2016.
 */
public class Test3rdAttempt {

    public static void main(String[] args) {

        List<Person> list = Person.createShortList();
        Search3rdAttempt test = new Search3rdAttempt();

        test.callDrivers(list, new MyTest<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getAge()>15;
            }
        });


        test.emailDraftees(list, new MyTest<Person>() {
            @Override
            public boolean test(Person p) {
                return p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == GenderEnum.MALE;
            }
        });

    }
}
