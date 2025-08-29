package com.practice.controller;

import com.practice.entity.Employee;
import com.practice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

@Autowired
private EmployeeService employeeService;

@PostMapping("/add")
public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
    System.err.println(employee);
    employeeService.saveEmployee(employee);
return new ResponseEntity<>("Employee Data Saved Successfully",HttpStatus.CREATED);
}

@GetMapping("/getAllData")
public ResponseEntity<List<Employee>> getAllData(){
    List<Employee> employeeList=employeeService.getAllEmployee();
    return new ResponseEntity<>(employeeList,HttpStatus.OK);
}

@GetMapping("/getDataById/{id}")
public ResponseEntity<Employee> getDataById(@PathVariable("id") int id){
    Employee employee = employeeService.getEmployeeById(id);
    return  new ResponseEntity<>(employee,HttpStatus.OK);
}

@DeleteMapping("/deleteDataById/{id}")
public ResponseEntity<String> deleteEmployeeById(@PathVariable("id")int id){
    employeeService.deleteEmployeeById(id);
    return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
}

@PutMapping("/updateDataById/{id}")
public ResponseEntity<Employee> updateEmployeeById(@PathVariable("id")int id,@RequestBody Employee employee){
    Employee updateEmployee = employeeService.updateEmployeeById(id,employee);
    return new ResponseEntity<>(updateEmployee,HttpStatus.OK);
}

@PostMapping("/addListOfData")
public ResponseEntity<List<Employee>> saveListOfEmployee(@RequestBody List<Employee> employeeList){
    employeeService.saveListOfEmployee(employeeList);
    return new ResponseEntity<>(employeeList,HttpStatus.CREATED);
}

    @GetMapping("/filter")
    public ResponseEntity<List<Employee>> filterEmployeeByDepartment(@RequestParam(required = false) String department, @RequestParam(required = false) String designation){
        List<Employee> sortedList = new ArrayList<>();

        if(department!= null){
            sortedList = employeeService.filterByDepartment(department);
        }else {
            sortedList =employeeService.filterBydesingnation(designation);
        }

        return new ResponseEntity<>(sortedList,HttpStatus.OK);
    }
}
