package com.example.rqdesign.employees.service;

import com.example.rqdesign.employees.dto.request.EmployeeReqDto;
import com.example.rqdesign.employees.dto.response.*;

public interface IEmpService {

    public EmployeesRespDto getAllEmployees();

    public EmployeesRespDto getEmployeesByNameSearch(String matchString);

    public CreateEmpRespDto getEmployeeById(String id);

    public HighestQueryDataRespDto getHighestSalaryOfEmployees();

    public HighestQueryDataRespDto getTopKHighestEarningEmployeeNames(Integer topKCount);

    public CreateEmpRespDto createEmployee(EmployeeReqDto empReqDto);

    public DeleteEmpRespDto removeEmployee(String id);
}
