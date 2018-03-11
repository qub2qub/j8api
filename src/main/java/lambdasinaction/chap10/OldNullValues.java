package lambdasinaction.chap10;

/**
 * Created on 2018-03-11
 */
public class OldNullValues {
    
    public String getCarInsuranceName0(Person person) {
        return person.getCar().getInsurance().getName();
    }
    
    public String getCarInsuranceName1(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    // you’re not checking to see if the name of the insurance company is null because,
                    // like any other company, you know it must have a name.
                    return insurance.getName();
                }
            }
        }
        return "Unknown";
    }
    
    public String getCarInsuranceName2(Person person) {
        if (person == null) {
            return "Unknown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }
    
    public class Person {
        private Car car;
        public Car getCar() { return car; }
    }
    public class Car {
        private Insurance insurance;
        public Insurance getInsurance() { return insurance; }
    }
    public class Insurance {
        private String name;
        public String getName() { return name; }
    }
}
