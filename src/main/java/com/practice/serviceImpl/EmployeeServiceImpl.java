package com.practice.serviceImpl;

import com.practice.entity.Employee;
import com.practice.repository.EmployeeRepository;
import com.practice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String saveEmployee(Employee employee) {
      Employee saveEmployee = employeeRepository.save(employee);
        return "Employee Data Saved Succeessfully";
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new NullPointerException("Employee with id does not found "+id));
        return employee;
    }

    @Override
    public String deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
        return "Employee deleted Successfully";
    }

    @Override
    public Employee updateEmployeeById(int id, Employee newEmployee) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new NullPointerException("Updated Developer in db does not found with id "+id));
        System.err.println("Old Employee from db"+employee);
        System.err.println("Employee object with value to be updated"+newEmployee);

        employee.setName(newEmployee.getName());
        employee.setDepartment(newEmployee.getDepartment());
        employee.setDesignation(newEmployee.getDesignation());
        employee.setSalary(newEmployee.getSalary());

        Employee updateEmployee = employeeRepository.save(employee);
        System.err.println("Employee with updated value"+updateEmployee);
        return updateEmployee;
    }

    @Override
    public List<Employee> saveListOfEmployee(List<Employee> employeeList) {
        employeeRepository.saveAll(employeeList);
        return employeeList;
    }


}
