package com.example.rqdesign.employees.client.iClient;

import com.example.rqdesign.employees.dto.response.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Arrays;
import java.util.List;

public interface IEmpClient {

    public static String EMPLOYEES_CLIENT = "emp.client";

    public List<Employee> getAllEmployees();

    public List<Employee> getEmployeesByNameSearch();

    public List<Employee> getEmployeeById();

    public List<Employee> getHighestSalaryOfEmployees();

    public List<Employee> getTop10HighestEarningEmployeeNames();

    public List<Employee> createEmployee();

    public List<Employee> updateEmployee();
}
