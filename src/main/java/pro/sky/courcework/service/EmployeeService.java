package pro.sky.courcework.service;
import pro.sky.courcework.model.Employee;
import pro.sky.courcework.exeption.EmployeeAlreadyAddedException;
import pro.sky.courcework.exeption.EmployeeNotFoundException;
import pro.sky.courcework.exeption.EmployeeStorageIsFullException;

import java.util.List;

public interface EmployeeService  {

    Employee addEmployee(String name, String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException;

    Employee removeEmployee(String name, String lastName) throws EmployeeNotFoundException;


    Employee findEmployee(String name, String lastName) throws EmployeeNotFoundException;


    List<Employee> getAllEmployees();

}

