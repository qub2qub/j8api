package tutorial.Chapter_6_Collectors;


import tutorial.Chapter_3_Migration_from_7_to_8.beans.Department;
import tutorial.Chapter_3_Migration_from_7_to_8.beans.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Ex_1_Collect_to_List {
    public static void main(String[] args) {
        List<Employee> employees = hireFourEmployees();

        List<Object> departmentNames = employees.stream()
                .filter(e -> e.getLevel() > 2)
                .map(e -> e.getDepartment())
                .distinct()
                .sorted(Comparator.comparing(e -> e.getName()))
                .collect(Collectors.toList());

        System.out.println(departmentNames.getClass());
        departmentNames.forEach(System.out::println);

            /*
            So, what concrete type (subclass) of List is being used here? Are there any guarantees?
If you look at the documentation of Collectors#toList(), it states that -
"There are no guarantees on the type, mutability, serializability, or thread-safety of the List returned".
If you want a particular implementation to be returned, you can use Collectors#toCollection(Supplier) instead.
             */


        List departmentNames2 = employees.stream()
                .filter(e -> e.getLevel() > 2)
                .map(e -> e.getDepartment())
                .distinct()
                .sorted(Comparator.comparing(e -> e.getName()))
                .collect(Collectors.toCollection(LinkedList::new));

        System.out.println(departmentNames2.getClass());
        departmentNames2.forEach(System.out::println);


    }

    private static List<Employee> hireFourEmployees() {

        Department financialDepartment = new Department("Financial");
        Department mobileDepartment = new Department("Mobile");

        return Arrays.asList(
                new Employee(18, "Petr", "Java", 1, financialDepartment),
                new Employee(25, "Olga", ".NET", 3, financialDepartment),
                new Employee(38, "John", "Delphi", 4, mobileDepartment),
                new Employee(43, "Sergey", "Java", 4, mobileDepartment));
    }

}
