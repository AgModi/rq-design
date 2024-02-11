package com.example.rqdesign.service.impl;

import com.example.rqdesign.client.IEmpClient;
import com.example.rqdesign.dto.request.EmployeeReqDto;
import com.example.rqdesign.dto.response.*;
import com.example.rqdesign.service.IEmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService implements IEmpService {

    @Autowired
    @Qualifier("empHttpClient")
    //TODO make it profile based
    //@Qualifier("mockHttpClient")
    private IEmpClient client;

    @Override
    public EmployeesRespDto getAllEmployees() {
        return client.getAllEmployees();
    }

    @Override
    public EmployeesRespDto getEmployeesByNameSearch(String matchString) {
        log.trace("Searching employee with name :{}", matchString);
        EmployeesRespDto employeesRespDto = client.getAllEmployees();
        List<EmpRespDto> empRespDtoList = employeesRespDto.getData();
        empRespDtoList = empRespDtoList.stream().filter(emp -> emp.getEmployeeName().contains(matchString)).collect(Collectors.toList());
        employeesRespDto.setData(empRespDtoList);
        log.trace("Employees with name :{}, are {}", matchString, employeesRespDto.getData());
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

        HighestQueryDataRespDto respDto = HighestQueryDataRespDto.convertFromEmpRestDto(maxSalaryPerson);
        log.trace("Highest salary is :{}", respDto.getHighestSalary());
        return respDto;
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

        HighestQueryDataRespDto respDto = HighestQueryDataRespDto.convertFromEmpRestDto(topKEmpNameList);
        log.trace("Highest earning employees is :{}", respDto.getHighestEarningEmployees());
        return respDto;
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
