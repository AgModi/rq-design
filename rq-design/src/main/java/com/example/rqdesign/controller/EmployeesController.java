package com.example.rqdesign.controller;

import com.example.rqdesign.Constants;
import com.example.rqdesign.dto.request.EmployeeReqDto;
import com.example.rqdesign.dto.response.CreateEmpRespDto;
import com.example.rqdesign.dto.response.DeleteEmpRespDto;
import com.example.rqdesign.dto.response.EmployeesRespDto;
import com.example.rqdesign.dto.response.HighestQueryDataRespDto;
import com.example.rqdesign.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Constants.URL_EMPLOYEES)
public class EmployeesController {

    @Autowired
    @Qualifier("employeeService")
    private IEmpService service;
    
    @GetMapping
    public @ResponseBody EmployeesRespDto getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping(value = Constants.URL_EMP_SEARCH)
    public @ResponseBody EmployeesRespDto  getEmployeesByNameSearch(@Valid @RequestParam("name") String name) {
        return service.getEmployeesByNameSearch(name);
    }

    @GetMapping(value = Constants.URL_EMP_BY_ID)
    public @ResponseBody CreateEmpRespDto getEmployeeById(@PathVariable String id) {
        return service.getEmployeeById(id);
    }

    @GetMapping(value = Constants.URL_EMP_TOP_SALARY)
    public @ResponseBody HighestQueryDataRespDto getHighestSalaryOfEmployees() {
        return service.getHighestSalaryOfEmployees();
    }

    @GetMapping(value = Constants.URL_EMP_TOP_EARNING_EMPS)
    public @ResponseBody HighestQueryDataRespDto  getTopKHighestEarningEmployeeNames(@RequestParam("topKCount") Integer topKCount) {
        return service.getTopKHighestEarningEmployeeNames(topKCount);
    }

    @PostMapping
    public @ResponseBody CreateEmpRespDto  createEmployee(@Validated @RequestBody EmployeeReqDto empReqDto) {
        return service.createEmployee(empReqDto);
    }

    @DeleteMapping(value = Constants.URL_EMP_BY_ID)
    public @ResponseBody DeleteEmpRespDto removeEmployeeById(@PathVariable String id) {
        return service.removeEmployee(id);
    }
}
