package lambda.common;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Class Person.
 *
 * Created by dkorolev on 7/24/2016.
 */
public class Person {

    private String name;
    private int age;
    private GenderEnum gender;



//    public Person(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }

    private Person(Builder builder) {
        name = builder.name;
        age = builder.age;
        gender = builder.gender;
    }

    /**
     * Getters and Setters
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }


    //Builder
    public static class Builder {
        private String name;
        private int age;
        private GenderEnum gender;

        public Builder buildName(String setName) {
            this.name = setName;
            return this;
        }

        public Builder buildAge(int age) {
            this.age = age;
            return this;
        }

        public Builder buildGender(GenderEnum gender) {
            this.gender = gender;
            return this;
        }

        public Person build() {
            return new Person(this);
        }

    }

    /**
     * create 3 persons.
     * @return list of Person
     */
    public static List<Person> createShortList() {
        List<Person> people = new ArrayList<>();

        people.add(
                new Person.Builder()
                        .buildName("Tom")
                        .buildAge(10)
                        .buildGender(GenderEnum.MALE)
                        .build()
        );

        people.add(
                new Person.Builder()
                        .buildName("Betty")
                        .buildAge(25)
                        .buildGender(GenderEnum.FEMALE)
                        .build()
        );

        people.add(
                new Person.Builder()
                        .buildName("Fred")
                        .buildAge(30)
                        .buildGender(GenderEnum.MALE)
                        .build()
        );
        return people;
    }

    //old style
    public void printPersonDetails() {
        System.out.println(
                "Name: " +this.getName()
                        + " Age:" + this.getAge()
                + " Gender:" + this.getGender());
    }

    //use function interface (T->R)
    public String printCustom(Function<Person,String> fun) {
        return fun.apply(this);
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
