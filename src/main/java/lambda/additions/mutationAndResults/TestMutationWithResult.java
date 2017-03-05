package lambda.additions.mutationAndResults;

import lambda.additions.searchCriteria.SearchCriteria;
import lambda.common.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Returns list after filtering with collect().
 *
 * Created by dkorolev on 7/24/2016.
 */
public class TestMutationWithResult {
    public static void main(String[] args) {

        List<Person> list = Person.createShortList();
        SearchCriteria criteria = new SearchCriteria();

        List<Person> allPilots = list.stream()
                .filter(criteria.getCriteria("allPilots"))
                .collect(Collectors.toList());

        allPilots.forEach(Person::printPersonDetails);
    }
}
