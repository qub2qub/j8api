package lambda.additions.chainingAndFiltering;

import lambda.additions.searchCriteria.SearchCriteria;
import lambda.common.GenderEnum;
import lambda.common.Person;

import java.util.List;

/**
 * Using filtering to reduce number of elements for iteration.
 *
 * Created by dkorolev on 7/24/2016.
 */
public class TestFiltering {
    public static void main(String[] args) {

        List<Person> list = Person.createShortList();
        SearchCriteria criteria = new SearchCriteria();

        list.stream().filter(p -> p.getAge()>15 && p.getGender().equals(GenderEnum.MALE))
                .forEach(Person::printPersonDetails);
        System.out.println();
        list.stream().filter(criteria.getCriteria("allDraftees"))
                .forEach(System.out::println);


    }
}
