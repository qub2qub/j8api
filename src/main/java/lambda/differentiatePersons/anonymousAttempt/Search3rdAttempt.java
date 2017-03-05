package lambda.differentiatePersons.anonymousAttempt;

import lambda.common.Person;

import java.util.List;

/**
 * Created by dkorolev on 7/24/2016.
 */
public class Search3rdAttempt {

    public void callDrivers(List<Person> list, MyTest<Person> aTest) {
        for (Person p : list) {
            if (aTest.test(p)) {
                roboCall(p);
            }
        }
    }

    public void emailDraftees(List<Person> pl, MyTest<Person> aTest) {
        for (Person p : pl) {
            if (aTest.test(p)) {
                roboCall(p);
            }
        }
    }


    public void mailPilots(List<Person> pl, MyTest<Person> aTest) {
        for (Person p : pl) {
            if (aTest.test(p)) {
                roboCall(p);
            }
        }
    }



    public void roboCall(Person p) {
        System.out.println("Calling " + p.getName() + " age " + p.getAge());
    }
}
