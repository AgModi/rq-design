package com.example.rqdesign.client.impl;

import com.example.rqdesign.Constants;
import com.example.rqdesign.dto.request.EmployeeReqDto;
import com.example.rqdesign.dto.response.CreateEmpRespDto;
import com.example.rqdesign.dto.response.DeleteEmpRespDto;
import com.example.rqdesign.dto.response.EmployeesRespDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockHttpClientTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private MockHttpClient mockHttpClient;

    @Test
    public void testGetEmployees() {
        EmployeesRespDto employeesRespDto = mockHttpClient.getAllEmployees();
        Assertions.assertNotNull(employeesRespDto);
        Assertions.assertNotNull(employeesRespDto.getData());
        Assertions.assertEquals(20, employeesRespDto.getData().size());
    }

    @Test
    public void testGetEmployeeById() {
        CreateEmpRespDto createEmpRespDto = mockHttpClient.getEmployeeById("1");
        Assertions.assertNotNull(createEmpRespDto);
        Assertions.assertEquals("1", createEmpRespDto.getData().getId());
    }

    @Test
    public void testCreateEmployee() {
        CreateEmpRespDto createEmpRespDto = mockHttpClient.createEmployee(new EmployeeReqDto());
        Assertions.assertNotNull(createEmpRespDto);
        Assertions.assertEquals("1", createEmpRespDto.getData().getId());
    }

    @Test
    public void testDeleteEmployee() {
        DeleteEmpRespDto deleteEmpRespDto = mockHttpClient.deleteEmployee("1");
        Assertions.assertNotNull(deleteEmpRespDto);
        Assertions.assertEquals(Constants.SUCCESS, deleteEmpRespDto.getStatus());
    }
}
