package lambdasinaction.chap10;

import java.util.Optional;
import lambdasinaction.chap10.NewOptionalApproach.Car;
import lambdasinaction.chap10.NewOptionalApproach.Insurance;
import lambdasinaction.chap10.NewOptionalApproach.Person;

/**
 * Created on 2018-03-11
 */
public class CombiningOptionals {
    public Optional<Insurance> nullSafeFindCheapestInsurance1(Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }
    public Optional<Insurance> nullSafeFindCheapestInsurance2(Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }
    public Insurance findCheapestInsurance(Person person, Car car) {
        // queries services provided by the different insurance companies
        // compare all those data
        return new Insurance();//cheapestCompany;
    }
}
