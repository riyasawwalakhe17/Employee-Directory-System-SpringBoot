package com.practice.service;

import com.practice.entity.Employee;

import java.util.List;

public interface EmployeeService {


    String saveEmployee(Employee employee);

    List<Employee> getAllEmployee();

    Employee getEmployeeById(int id);

    String deleteEmployeeById(int id);

    Employee updateEmployeeById(int id, Employee newEmployee);

    List<Employee> saveListOfEmployee(List<Employee> employeeList);
}
