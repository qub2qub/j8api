package lambda.differentiatePersons.firstAtempt;

import lambda.common.GenderEnum;
import lambda.common.Person;

import java.util.List;

/**
 * 1st solution.
 * "-": DRY is not followed (method repetition, selection criteria for each method
 *
 * Created by dkorolev on 7/24/2016.
 */
public class Search1stAttempt {


    public void callDrivers(List<Person> list) {
        for (Person p : list) {
            if (p.getAge() > 16) {
                roboCall(p);
            }
        }
    }

    public void emailDraftees(List<Person> pl) {
        for (Person p : pl) {
            if (p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == GenderEnum.MALE) {
                roboCall(p);
            }
        }
    }


    public void mailPilots(List<Person> pl) {
        for (Person p : pl) {
            if (p.getAge() >= 23 && p.getAge() <= 65) {
                roboCall(p);
            }
        }
    }




    public void roboCall(Person p) {
        System.out.println("Calling " + p.getName() + " age " + p.getAge());
    }
}
