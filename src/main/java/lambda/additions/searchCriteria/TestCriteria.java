package lambda.additions.searchCriteria;

import lambda.common.Person;
import lambda.differentiatePersons.lambdaAttempt.Search4thAttempt;

import java.util.List;

/**
 * Test usage of SearchCriteria class.
 *
 * Created by dkorolev on 7/24/2016.
 */
public class TestCriteria {

    public static void main(String[] args) {
        Search4thAttempt attempt = new Search4thAttempt();
        SearchCriteria criteria = new SearchCriteria();
        List<Person> personList = Person.createShortList();
        attempt.callDrivers(personList,criteria.getCriteria("allDrivers"));
    }
}
