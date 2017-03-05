package lambda.additions.searchCriteria;

import lambda.common.GenderEnum;
import lambda.common.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Incapsulate all criterias in one class to get it from map.
 *
 * Created by dkorolev on 7/24/2016.
 */
public class SearchCriteria {

    private final Map<String, Predicate<Person>> searchMap = new HashMap<>();

    public SearchCriteria() {
        initSearchMap();
    }

    private void initSearchMap() {
        Predicate<Person> allDrivers = p -> p.getAge() >= 16;
        Predicate<Person> allDraftees = p -> p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == GenderEnum.MALE;
        Predicate<Person> allPilots = p -> p.getAge() >= 23 && p.getAge() <= 65;

        searchMap.put("allDrivers", allDrivers);
        searchMap.put("allDraftees", allDraftees);
        searchMap.put("allPilots", allPilots);
    }


    public Predicate<Person> getCriteria(String predicateName) {
        Predicate<Person> predicate = searchMap.get(predicateName);

        if (predicate == null) {
            System.err.println("Search criteria is not found");
            System.exit(1);
        }

        return predicate;
    }


}
