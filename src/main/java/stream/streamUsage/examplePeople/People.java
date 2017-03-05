package stream.streamUsage.examplePeople;

import java.util.Arrays;
import java.util.Collection;

/**
 * Helper class.
 *
 * Created by dkorolev on 1/4/2017.
 */
public class People {

    private String name;

    private Integer age;

    private Sex sex;


    public People(String name, Integer age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public static Collection<People> fillWith4People() {
        People alex = new People("Alex", 16, Sex.MAN);
        People tom = new People("Tom", 23, Sex.MAN);
        People helen = new People("Helen", 42, Sex.WOMAN);
        People greg = new People("Greg", 69, Sex.MAN);
        return Arrays.asList(alex, tom, helen, greg);
    }

    //getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
