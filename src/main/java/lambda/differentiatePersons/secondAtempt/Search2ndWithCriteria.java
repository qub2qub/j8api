package lambda.differentiatePersons.secondAtempt;

import lambda.common.GenderEnum;
import lambda.common.Person;

import java.util.List;

/**
 * 2nd attempt of search.
 * "-" still a lot of repeated code.
 *
 * Created by dkorolev on 7/24/2016.
 */
public class Search2ndWithCriteria {

    public void callDrivers(List<Person> list) {
        for (Person p : list) {
            if (isDriver(p)) {
                roboCall(p);
            }
        }
    }

    public void emailDraftees(List<Person> pl) {
        for (Person p : pl) {
            if (isDraftee(p)) {
                roboCall(p);
            }
        }
    }


    public void mailPilots(List<Person> pl) {
        for (Person p : pl) {
            if (isPilot(p)) {
                roboCall(p);
            }
        }
    }


    public boolean isDriver(Person p) {
        return p.getAge() > 16;
    }

    public boolean isDraftee(Person p) {
        return p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == GenderEnum.MALE;
    }

    public boolean isPilot(Person p) {
        return p.getAge() >= 23 && p.getAge() <= 65;
    }


    public void roboCall(Person p) {
        System.out.println("Calling " + p.getName() + " age " + p.getAge());
    }
}
