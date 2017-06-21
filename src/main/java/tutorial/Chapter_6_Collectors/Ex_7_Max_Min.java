package tutorial.Chapter_6_Collectors;

import tutorial.Chapter_3_Migration_from_7_to_8.YoungEnterpriseCode;
import tutorial.Chapter_3_Migration_from_7_to_8.beans.Department;
import tutorial.Chapter_3_Migration_from_7_to_8.beans.Employee;

import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;

/**
 *  29-May-17.
 */
public class Ex_7_Max_Min {
    public static void main(String[] args) {
        List<Employee> employees = YoungEnterpriseCode.hireFourEmployees();

        // Mix optional and static imports
        Optional<Department> result = employees.stream()
                .map(Employee::getDepartment)
                .collect(maxBy(comparing(Department::getName)));

        System.out.println("Max is " + result.get());


        result = employees.stream()
                .map(Employee::getDepartment)
                .collect(minBy(comparing(Department::getName)));

        System.out.println("Min is " + result.get());

    }
}
