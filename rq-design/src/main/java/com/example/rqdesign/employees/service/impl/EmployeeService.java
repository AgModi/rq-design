package com.example.rqdesign.employees.service.impl;

import com.example.rqdesign.employees.client.IEmpClient;
import com.example.rqdesign.employees.dto.request.EmployeeReqDto;
import com.example.rqdesign.employees.dto.response.*;
import com.example.rqdesign.employees.service.IEmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService implements IEmpService {

    @Autowired
    @Qualifier("empHttpClient")
    private IEmpClient client;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public EmployeesRespDto getAllEmployees() {
        return client.getAllEmployees();
    }

    @Override
    public EmployeesRespDto getEmployeesByNameSearch(String matchString) {
        EmployeesRespDto employeesRespDto = client.getAllEmployees();
        List<EmpRespDto> empRespDtoList = employeesRespDto.getData();
        empRespDtoList = empRespDtoList.stream().filter(emp -> emp.getEmployeeName().contains(matchString)).collect(Collectors.toList());
        employeesRespDto.setData(empRespDtoList);
        return employeesRespDto;
    }

    @Override
    public CreateEmpRespDto getEmployeeById(String id) {
        return client.getEmployeeById(id);
    }

    @Override
    public HighestQueryDataRespDto getHighestSalaryOfEmployees() {
        EmployeesRespDto employeesRespDto = client.getAllEmployees();
        List<EmpRespDto> empRespDtoList = employeesRespDto.getData();
        EmpRespDto maxSalaryPerson = empRespDtoList.stream().max((o1,o2) -> {
            Integer salaryO1 = Integer.parseInt(o1.getEmployeeSalary());
            Integer salaryO2 = Integer.parseInt(o2.getEmployeeSalary());
            return (salaryO1 > salaryO2)? 1 : -1;
        }).orElseThrow(NoSuchElementException::new);

        return HighestQueryDataRespDto.convertFromEmpRestDto(maxSalaryPerson);
    }

    @Override
    public HighestQueryDataRespDto getTopKHighestEarningEmployeeNames(Integer topKCount) {
        EmployeesRespDto employeesRespDto = client.getAllEmployees();
        List<EmpRespDto> empRespDtoList = employeesRespDto.getData();

        List<String> topKEmpNameList = empRespDtoList.stream().sorted((o1,o2) -> {
            Integer salaryO1 = Integer.parseInt(o1.getEmployeeSalary());
            Integer salaryO2 = Integer.parseInt(o2.getEmployeeSalary());
            return (salaryO1 > salaryO2)? -1 : 1;
        }).limit(topKCount).map(emp -> emp.getEmployeeName()).collect(Collectors.toList());

        return HighestQueryDataRespDto.convertFromEmpRestDto(topKEmpNameList);
    }

    @Override
    public CreateEmpRespDto createEmployee(EmployeeReqDto empReqDto) {
        return client.createEmployee(empReqDto);
    }

    @Override
    public DeleteEmpRespDto removeEmployee(String id) {
        return client.deleteEmployee(id);
    }
}
