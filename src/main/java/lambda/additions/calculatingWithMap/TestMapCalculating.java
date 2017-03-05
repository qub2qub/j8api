package lambda.additions.calculatingWithMap;

import lambda.additions.searchCriteria.SearchCriteria;
import lambda.common.Person;

import java.util.List;
import java.util.OptionalDouble;

/**
 * Use mapToInt and mapToDouble to compute sum more easily.
 *
 * Created by dkorolev on 7/24/2016.
 */
public class TestMapCalculating {
    public static void main(String[] args) {

        List<Person> list = Person.createShortList();
        SearchCriteria criteria = new SearchCriteria();

        //calculate average age of pilots old way
        int sum = 0;
        int counter = 0;
        for (Person person : list) {
            if (person.getAge() >= 23 && person.getAge() <= 65) {
                sum += person.getAge();
                counter++;
            }
        }
        System.out.println("Sume of pilots ages: " + sum);
        System.out.println("Average sum of pilots: " + sum/counter);


        //calculate new way
        int sumNew = list.stream()
                .filter(p -> p.getAge() >= 23 && p.getAge() <= 65)
                .mapToInt(Person::getAge)
                .sum();

        OptionalDouble averageNew = list.parallelStream()
                .filter(criteria.getCriteria("allPilots"))
                .mapToDouble(Person::getAge)
                .average();
        System.out.println("SumNew of pilots ages: " + sumNew);
        System.out.println("AverageNew sum of pilots: " + averageNew);
    }
}
