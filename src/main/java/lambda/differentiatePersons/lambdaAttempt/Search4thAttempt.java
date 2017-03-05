package lambda.differentiatePersons.lambdaAttempt;

import lambda.common.Person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Good implementation with predicates
 *
 * Created by dkorolev on 7/24/2016.
 */
public class Search4thAttempt {

    public void callDrivers(List<Person> list,  Predicate<Person> pred) {
        for (Person p : list) {
            if (pred.test(p)) {
                roboCall(p);
            }
        }
    }

    public void emailDraftees(List<Person> pl, Predicate<Person> pred) {
        for (Person p : pl) {
            if (pred.test(p)) {
                roboCall(p);
            }
        }
    }


    public void mailPilots(List<Person> pl, Predicate<Person> pred) {
        for (Person p : pl) {
            if (pred.test(p)) {
                roboCall(p);
            }
        }
    }



    public void roboCall(Person p) {
        System.out.println("Calling " + p.getName() + " age " + p.getAge());
    }
}
