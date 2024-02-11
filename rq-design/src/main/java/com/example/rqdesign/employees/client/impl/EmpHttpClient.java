package com.example.rqdesign.employees.client.impl;

import com.example.rqdesign.employees.client.IEmpClient;
import com.example.rqdesign.employees.client.cofig.EmployeesConfig;
import com.example.rqdesign.employees.dto.request.EmployeeReqDto;
import com.example.rqdesign.employees.dto.response.CreateEmpRespDto;
import com.example.rqdesign.employees.dto.response.DeleteEmpRespDto;
import com.example.rqdesign.employees.dto.response.EmployeesRespDto;
import com.example.rqdesign.employees.exception.DownstreamConnectException;
import com.example.rqdesign.employees.exception.DownstreamNotServingException;
import com.example.rqdesign.employees.exception.DownstreamResponseParsingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class EmpHttpClient extends BaseHttpClient implements IEmpClient {

    @Autowired
    private EmployeesConfig config;

    @Override
    public EmployeesRespDto getAllEmployees() {
        String url = config.getGetEmployees();
        log.info("Calling server with url {}", url);

        try {
            ResponseEntity<Object> response =  callGetHttpClient(url, EmployeesRespDto.class, null, null);
            HttpStatusCode httpStatusCode = response.getStatusCode();
            log.info("HTTP status code {}", httpStatusCode.value());
            if (httpStatusCode.is2xxSuccessful() || httpStatusCode.value() == 200){
                try {
                    return (EmployeesRespDto) response.getBody();
                } catch (Exception e) {
                    log.error("Error while parsing response {} of url {} with exception {}", response.getBody(), url, e.getMessage());
                    throw new DownstreamResponseParsingException("Unparseable response from Downstream server");
                }
            } else {
                log.error("Received httpcode {} for url {} {}", httpStatusCode.value(), url);
                throw new DownstreamNotServingException("Downstream server not serving data");
            }

        } catch (Exception ex) {
            log.error("Error while connecting to server with url {} with exception {}", url, ex.getMessage());
            throw new DownstreamConnectException("Failed to Connect to downstream server");
        }
    }

    @Override
    public CreateEmpRespDto getEmployeeById(String id) {
        String url = config.getGetEmpDetails(id);
        log.info("Calling server with url {}", url);
        try {
            ResponseEntity<Object> response =  callGetHttpClient(url, CreateEmpRespDto.class, null, null);
            HttpStatusCode httpStatusCode = response.getStatusCode();
            log.info("HTTP status code {}", httpStatusCode.value());
            if (httpStatusCode.is2xxSuccessful() || httpStatusCode.value() == 200){
                try {
                    return (CreateEmpRespDto) response.getBody();
                } catch (Exception e) {
                    log.error("Error while parsing response {} of url {} with exception {}", response.getBody(), url, e.getMessage());
                    throw new DownstreamResponseParsingException("Unparseable response from Downstream server");
                }
            } else {
                log.error("Received httpcode {} for url {} {}", httpStatusCode.value(), url);
                throw new DownstreamNotServingException("Downstream server not serving data");
            }

        } catch (Exception ex) {
            log.error("Error while connecting to server with url {} with exception {}", url, ex.getMessage());
            throw new DownstreamConnectException("Failed to Connect to downstream server");
        }
    }

    @Override
    public CreateEmpRespDto createEmployee(EmployeeReqDto empReqDto) {
        String url = config.getCreateEmp();
        log.info("Calling server with url {}", url);
        try {
            ResponseEntity<Object> response =  callPostHttpClient(url, empReqDto, CreateEmpRespDto.class, null);
            HttpStatusCode httpStatusCode = response.getStatusCode();
            log.info("HTTP status code {}", httpStatusCode.value());
            if (httpStatusCode.is2xxSuccessful() || httpStatusCode.value() == 200){
                try {
                    return (CreateEmpRespDto) response.getBody();
                } catch (Exception e) {
                    log.error("Error while parsing response {} of url {} with exception {}", response.getBody(), url, e.getMessage());
                    throw new DownstreamResponseParsingException("Unparseable response from Downstream server");
                }
            } else {
                log.error("Received httpcode {} for url {} {}", httpStatusCode.value(), url);
                throw new DownstreamNotServingException("Downstream server not serving data");
            }

        } catch (Exception ex) {
            log.error("Error while connecting to server with url {} with exception {}", url, ex.getMessage());
            throw new DownstreamConnectException("Failed to Connect to downstream server");
        }
    }

    @Override
    public DeleteEmpRespDto deleteEmployee(String id) {
        String url = config.getDeleteEmp(id);
        log.info("Calling server with url {}", url);
        try {
            ResponseEntity<Object> response =  callDeleteHttpClient(url, CreateEmpRespDto.class, null);
            HttpStatusCode httpStatusCode = response.getStatusCode();
            log.info("HTTP status code {}", httpStatusCode.value());
            if (httpStatusCode.is2xxSuccessful() || httpStatusCode.value() == 200){
                try {
                    return (DeleteEmpRespDto) response.getBody();
                } catch (Exception e) {
                    log.error("Error while parsing response {} of url {} with exception {}", response.getBody(), url, e.getMessage());
                    throw new DownstreamResponseParsingException("Unparseable response from Downstream server");
                }
            } else {
                log.error("Received httpcode {} for url {} {}", httpStatusCode.value(), url);
                throw new DownstreamNotServingException("Downstream server not serving data");
            }

        } catch (Exception ex) {
            log.error("Error while connecting to server with url {} with exception {}", url, ex.getMessage());
            throw new DownstreamConnectException("Failed to Connect to downstream server");
        }
    }
}
