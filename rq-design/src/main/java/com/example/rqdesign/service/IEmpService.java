package com.example.rqdesign.service;

import com.example.rqdesign.dto.request.EmployeeReqDto;
import com.example.rqdesign.dto.response.*;

public interface IEmpService {

    public EmployeesRespDto getAllEmployees();

    public EmployeesRespDto getEmployeesByNameSearch(String matchString);

    public CreateEmpRespDto getEmployeeById(String id);

    public HighestQueryDataRespDto getHighestSalaryOfEmployees();

    public HighestQueryDataRespDto getTopKHighestEarningEmployeeNames(Integer topKCount);

    public CreateEmpRespDto createEmployee(EmployeeReqDto empReqDto);

    public DeleteEmpRespDto removeEmployee(String id);
}
