package tutorial.Chapter_6_Collectors;

import tutorial.Chapter_3_Migration_from_7_to_8.YoungEnterpriseCode;
import tutorial.Chapter_3_Migration_from_7_to_8.beans.Employee;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  29-May-17.
 */
public class Ex_5_Joining {
    public static void main(String[] args) {

        final List<Employee> employees = YoungEnterpriseCode.hireFourEmployees();
        String names = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining());

        String  delimitedNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining("# "));

        String  prefixedNames = employees.stream()
                .map(Employee::getName)
                .collect(Collectors.joining(" $ ", "Our interns: ",  ". Pay them!"));

        System.out.println("1 Joined names, defaults:\n\t\t"  + names);
        System.out.println("2 Joined, by delimiter:\n\t\t"  + delimitedNames);
        System.out.println("3 with prefix & suffix:\n\t\t"+prefixedNames);
    }
}
