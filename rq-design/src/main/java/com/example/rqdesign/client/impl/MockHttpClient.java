package com.example.rqdesign.client.impl;

import com.example.rqdesign.Constants;
import com.example.rqdesign.client.IEmpClient;
import com.example.rqdesign.dto.request.EmployeeReqDto;
import com.example.rqdesign.dto.response.CreateEmpRespDto;
import com.example.rqdesign.dto.response.DeleteEmpRespDto;
import com.example.rqdesign.dto.response.EmpRespDto;
import com.example.rqdesign.dto.response.EmployeesRespDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
@Slf4j
public class MockHttpClient implements IEmpClient {

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();
    
    public EmployeesRespDto getEmployeesRespDto(){
        File file = null;
        EmployeesRespDto employeesRespDto = null;
        try {
            file = ResourceUtils.getFile("classpath:templates/employees_20.json");
            employeesRespDto = mapper.readValue(file, EmployeesRespDto.class);
        } catch (FileNotFoundException e) {
            log.error(" FileNotFoundException {}", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error(" IOException {}", e);
            throw new RuntimeException(e);
        }

        return employeesRespDto;
    }
    
    @Override
    public EmployeesRespDto getAllEmployees() {
        return getEmployeesRespDto();
    }

    @Override
    public CreateEmpRespDto getEmployeeById(String id) {
        for (EmpRespDto emp : getEmployeesRespDto().getData()) {
            if (emp.getId().equals(id)) {
                return new CreateEmpRespDto(Constants.SUCCESS, emp);
            }
        }
        return new CreateEmpRespDto(Constants.SUCCESS, null);
    }

    @Override
    public CreateEmpRespDto createEmployee(EmployeeReqDto empReqDto) {
        File file = null;
        CreateEmpRespDto createEmpRespDto = null;
        try {
            file = ResourceUtils.getFile("classpath:templates/empCreateSuccess.json");
            createEmpRespDto = mapper.readValue(file, CreateEmpRespDto.class);
        } catch (FileNotFoundException e) {
            log.error(" FileNotFoundException {}", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error(" IOException {}", e);
            throw new RuntimeException(e);
        }

        return createEmpRespDto;
    }

    @Override
    public DeleteEmpRespDto deleteEmployee(String id) {
        File file = null;
        DeleteEmpRespDto deleteEmpRespDto = null;
        try {
            file = ResourceUtils.getFile("classpath:templates/empDeleteSuccess.json");
            deleteEmpRespDto = mapper.readValue(file, DeleteEmpRespDto.class);
        } catch (FileNotFoundException e) {
            log.error(" FileNotFoundException {}", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error(" IOException {}", e);
            throw new RuntimeException(e);
        }

        return deleteEmpRespDto;
    }
}
