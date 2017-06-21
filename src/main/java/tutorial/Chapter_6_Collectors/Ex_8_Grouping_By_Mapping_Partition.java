package tutorial.Chapter_6_Collectors;

import tutorial.Chapter_3_Migration_from_7_to_8.YoungEnterpriseCode;
import tutorial.Chapter_3_Migration_from_7_to_8.beans.Department;
import tutorial.Chapter_3_Migration_from_7_to_8.beans.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 *  29-May-17.
 */
public class Ex_8_Grouping_By_Mapping_Partition {
    public static void main(String[] args) {
        List<Employee> employees = YoungEnterpriseCode.hireFourEmployees();

        // Step 1: Group by Department
        Map<Department, List<Employee>> groupedByDepartment = employees.stream()
                .collect(groupingBy( // группировка по функции
                    Employee::getDepartment
                ));
        System.out.println("_____________groupedByDepartment:_____________");
        groupedByDepartment.forEach((k, v) -> {
            System.out.println(  k + " -> " + v.stream().map(Employee::getName).collect(Collectors.joining(","))   );
//            System.out.println(  k + " -> " + v.stream().collect(Collectors.mapping(Employee::getName, Collectors.joining("_")))   );
//            System.out.print(  k + " -> ");
//            v.forEach( e -> System.out.print(e.getName()+", "));
//            System.out.println();
        });

        // Step 2: Group and count employees by department
        Map<Department, Long> countByDepartment = employees.stream()
                .collect(groupingBy(// группировка по функции
                    Employee::getDepartment,
                    Collectors.counting()
                ));
        System.out.println("_____________countByDepartment:_____________");
        countByDepartment.forEach((k, v) -> System.out.println(k + " -> " + v));

        // Step 3: Join and print out skills of employees grouped by theirs departments
        Map<Department, String> skillsByDepartment = employees.stream()
                .collect(groupingBy(// группировка по функции
                    Employee::getDepartment,
                    Collectors.mapping(Employee::getSkill, Collectors.joining(" & "))
                ));
        System.out.println("_____________skillsByDepartment:_____________");
        skillsByDepartment.forEach((k, v) -> System.out.println(k + " -> " + v));

        // Step 4: Partition developer names by binary criteria: level
        Map<Boolean, String> groupByLevel = employees.stream()
                .collect(partitioningBy( // партишн по предикату
                    e -> e.getLevel() > 2,
                    Collectors.mapping(Employee::getName, Collectors.joining("_"))
                ));
        System.out.println("_____________groupByLevel:_____________");
        groupByLevel.forEach((k, v) -> System.out.println("Level>2=" + k + " -> " + v));

    }
}
