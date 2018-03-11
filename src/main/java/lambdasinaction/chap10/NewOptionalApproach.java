package lambdasinaction.chap10;

import java.util.*;

import static java.util.stream.Collectors.toSet;

public class NewOptionalApproach {
    
/*    public Optional<String> getCarInsuranceName1(Optional<Person> person) {
        return person.map(Person::getCar)
            .map(Car::getInsurance)
            .map(Insurance::getName);
    }*/
    
    public String getCarInsuranceName2(Person person) {
        return Optional.ofNullable(person)
            //  optional’s flatMap method transforms the square contained
            // in the original optional into an optional containing a triangle.
            .flatMap(Person::getCar)
            .flatMap(Car::getInsurance)
            .map(Insurance::getName) // returns Optional<String>
            // Step 3 turns the Optional<Insurance> into an Optional<String>:
            // because the Insurance.getName() method returns a String, in this case a flatMap isn’t necessary.
            .orElse("Unknown");
    }
    
    public Set<String> getCarInsuranceNames3(List<Person> persons) {
        return persons.stream()
            .map(Person::getCar)
            .map(optCar -> optCar.flatMap(Car::getInsurance))
            .map(optInsurance -> optInsurance.map(Insurance::getName))
//            .flatMap(Optional::stream)
            .map(Optional::get)
            .collect(toSet());
    }
    
    public String getCarInsuranceName4(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
            .flatMap(Person::getCar)
            .flatMap(Car::getInsurance)
            .map(Insurance::getName)
            .orElse("Unknown");
    }
    
    public static class Person {
        private Optional<Car> car;
        private int age;
        public Optional<Car> getCar() { return car; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }
    public static class Car {
        private Optional<Insurance> insurance;
        public Optional<Insurance> getInsurance() { return insurance; }
    }
    public static class Insurance {
        private String name;
        public String getName() { return name; }
    }
}
