package pro.sky.courcework.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import pro.sky.courcework.model.Employee;
import pro.sky.courcework.exeption.EmployeeAlreadyAddedException;
import pro.sky.courcework.exeption.EmployeeNotFoundException;
import pro.sky.courcework.exeption.EmployeeStorageIsFullException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    private static final int MAX_EMPLOYEES = 5;


    @Override
    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {

        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Хранилище для сотрудников заполнено");
        }
        Employee newEmployee = new Employee(firstName, lastName);
        if (employees.containsKey(newEmployee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже существует");
        }
        employees.put(newEmployee.getFullName(),newEmployee);
        return newEmployee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);
        if (!employees.containsKey(employeeToRemove.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(employeeToRemove.getFullName());
        return employeeToRemove;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employeeToFind = new Employee(firstName, lastName);
        if (!employees.containsKey(employeeToFind.getFullName())) throw new EmployeeNotFoundException("Сотрудник не найден");
        return employees.get(employeeToFind.getFullName());
    }

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.size());
    }

}

