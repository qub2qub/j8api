package tutorial.Chapter_6_Collectors;


import tutorial.Chapter_3_Migration_from_7_to_8.YoungEnterpriseCode;
import tutorial.Chapter_3_Migration_from_7_to_8.beans.Department;
import tutorial.Chapter_3_Migration_from_7_to_8.beans.Employee;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ex_2_Collect_to_Map {

    public static void main(String[] args) {

        List<Employee> employees = YoungEnterpriseCode.hireFourEmployees();

        // Get pairs: Department - Employee Name
        Map<String, Department> result = employees.stream()
                .filter(e -> e.getLevel() > 2)
                .sorted(Comparator.comparing(e -> e.getName()))
                .collect(Collectors.toMap(Employee::getName, Employee::getDepartment));

        System.out.println(result.getClass());
        result.forEach((k, v) -> System.out.println(k + " " + v));

        Optional<Department> collect = employees.stream()
            .map(Employee::getDepartment)
            .collect(Collectors.maxBy(Comparator.comparing(Department::getName)));
//        .max(Comparator.comparing(d -> d.getName().length()));
        System.out.println("collect = " + collect);

    }

}
