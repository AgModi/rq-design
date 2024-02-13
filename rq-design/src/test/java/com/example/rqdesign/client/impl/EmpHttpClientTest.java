package com.example.rqdesign.client.impl;

import com.example.rqdesign.client.cofig.EmployeesConfig;
import com.example.rqdesign.dto.response.EmployeesRespDto;
import com.example.rqdesign.exception.DownstreamConnectException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class EmpHttpClientTest {

    @Mock
    private EmployeesConfig config;

    @Mock
    private BaseHttpClient baseHttpClient;

    @InjectMocks
    private EmpHttpClient empHttpClient;

    @Test
    public void testGetEmployees() {
        //Mockito.when(empHttpClient.callGetHttpClient(Mockito.anyString(), Mockito.any(), Mockito.anyMap(), Mockito.anyMap())).thenReturn(ResponseEntity.ok(new EmployeesRespDto()));
        //EmployeesRespDto employeesRespDto = empHttpClient.getAllEmployees();
        Assertions.assertThrows(DownstreamConnectException.class, () -> empHttpClient.getAllEmployees());
    }

    @Test
    public void testGetEmployeeById() {
        //Mockito.when(baseHttpClient.callGetHttpClient(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.ok(new EmployeesRespDto()));
        //EmployeesRespDto employeesRespDto = empHttpClient.getAllEmployees();
        Assertions.assertThrows(DownstreamConnectException.class, () -> empHttpClient.getEmployeeById("1"));
    }

    @Test
    public void testCreateEmployee() {
        //Mockito.when(baseHttpClient.callGetHttpClient(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.ok(new EmployeesRespDto()));
        //EmployeesRespDto employeesRespDto = empHttpClient.getAllEmployees();
        Assertions.assertThrows(DownstreamConnectException.class, () -> empHttpClient.createEmployee(null));
    }

    @Test
    public void testDeleteEmployee() {
        //Mockito.when(baseHttpClient.callGetHttpClient(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.ok(new EmployeesRespDto()));
        //EmployeesRespDto employeesRespDto = empHttpClient.getAllEmployees();
        Assertions.assertThrows(DownstreamConnectException.class, () -> empHttpClient.deleteEmployee("1"));
    }
}
