package stream.streamUsage.examplePeople;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Usage of strems based on People collection
 *
 * Created by dkorolev on 1/4/2017.
 */
public class ExampleUsages {
    public static void main(String[] args) {

        People alex = new People("Alex", 16, Sex.MAN);
        People tom = new People("Tom", 23, Sex.MAN);
        People helen = new People("Helen", 42, Sex.WOMAN);
        People greg = new People("Greg", 69, Sex.MAN);
        Collection<People> peoples = Arrays.asList(alex, tom, helen, greg);

        //return all army guys (man and 18-27 years)
        System.out.println(peoples.stream()
                .filter(p->p.getSex().equals(Sex.MAN)
                        && 18<=p.getAge() && p.getAge()<=27).collect(Collectors.toList()));


        //average age of men
        System.out.println(peoples.stream()
                .filter(p->p.getSex().equals(Sex.MAN))
                .mapToInt(People::getAge).average().getAsDouble());

        //find all guys which can work (>18 and <60 for men, <55 for women)
        System.out.println(peoples.stream()
                .filter(p->p.getAge()>=18)
                .filter(p-> (p.getSex().equals(Sex.MAN) && p.getAge()<60)
                        || (p.getSex().equals(Sex.WOMAN) && p.getAge()<55))
                .collect(Collectors.toList()));



    }
}
