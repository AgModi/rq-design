package com.example.rqdesign.employees.client;

import com.example.rqdesign.employees.dto.request.EmployeeReqDto;
import com.example.rqdesign.employees.dto.response.CreateEmpRespDto;
import com.example.rqdesign.employees.dto.response.DeleteEmpRespDto;
import com.example.rqdesign.employees.dto.response.EmployeesRespDto;

public interface IEmpClient {

    public static String EMPLOYEES_CLIENT = "emp.client";

    public EmployeesRespDto getAllEmployees();

    public CreateEmpRespDto getEmployeeById(String id);

    public CreateEmpRespDto createEmployee(EmployeeReqDto empReqDto);

    public DeleteEmpRespDto deleteEmployee(String id);
}
