package lambda.differentiatePersons.lambdaAttempt;

import lambda.common.GenderEnum;
import lambda.common.Person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by dkorolev on 7/24/2016.
 */
public class Test4thAttempt {

    public static void main(String[] args) {
        List<Person> persons = Person.createShortList();
        Search4thAttempt test = new Search4thAttempt();


        Predicate<Person> predicateDrivers = p->p.getAge()>15;

        System.out.println("===Call Drivers====");
        test.callDrivers(persons,predicateDrivers);
        System.out.println("==== Email Draftees =====");
        test.emailDraftees(persons, p -> p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == GenderEnum.MALE);
        System.out.println("==== mail Pilots ====");
        test.mailPilots(persons,p -> p.getAge() >= 23 && p.getAge() <= 65);
    }

}
