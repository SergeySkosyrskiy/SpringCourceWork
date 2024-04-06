package pro.sky.courcework;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import pro.sky.courcework.exeption.EmployeeAlreadyAddedException;
import pro.sky.courcework.exeption.EmployeeNotFoundException;
import pro.sky.courcework.exeption.EmployeeStorageIsFullException;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees = new ArrayList<>(List.of(


            new Employee("Ivan", "Ivanov"),

            new Employee("Semyon", "Gorbunkov"),

            new Employee("Peter", " Petrov"),

            new Employee("Nikolay", "Nikolaev"),

            new Employee("Fedor", "Fedorov")));
    private static final int MAX_EMPLOYEES = 5;

    @Override
    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Хранилище для сотрудников заполнено");
        }
        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }
        employees.add(newEmployee);
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.contains(employeeToRemove)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(employeeToRemove);
        return employeeToRemove;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employeeToFind = new Employee(firstName, lastName);
        if (!employees.contains(employeeToFind)) throw new EmployeeNotFoundException("Сотрудник не найден");

        return employees.get(employees.indexOf(employeeToFind));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

}

