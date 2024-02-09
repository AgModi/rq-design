package com.example.rqdesign.employees.controller;

import com.example.rqdesign.employees.dto.response.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeesController {

    @GetMapping
    public List<Employee> getAllEmployees() {
        return Arrays.asList(new Employee());
    }

    @GetMapping
    public List<Employee> getEmployeesByNameSearch() {
        return Arrays.asList(new Employee());
    }

    @GetMapping
    public List<Employee> getEmployeeById() {
        return Arrays.asList(new Employee());
    }

    @GetMapping
    public List<Employee> getHighestSalaryOfEmployees() {
        return Arrays.asList(new Employee());
    }

    @GetMapping
    public List<Employee> getTop10HighestEarningEmployeeNames() {
        return Arrays.asList(new Employee());
    }

    @PostMapping
    public List<Employee> createEmployee() {
        return Arrays.asList(new Employee());
    }

    @PutMapping
    public List<Employee> updateEmployee() {
        return Arrays.asList(new Employee());
    }
}
