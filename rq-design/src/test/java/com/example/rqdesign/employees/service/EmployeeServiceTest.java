package com.example.rqdesign.employees.service;

import com.example.rqdesign.employees.Constants;
import com.example.rqdesign.employees.client.IEmpClient;
import com.example.rqdesign.employees.dto.response.EmpRespDto;
import com.example.rqdesign.employees.dto.response.EmployeesRespDto;
import com.example.rqdesign.employees.dto.response.HighestQueryDataRespDto;
import com.example.rqdesign.employees.service.impl.EmployeeService;
import com.example.rqdesign.employees.util.TestDataGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private IEmpClient client;

    @InjectMocks
    private EmployeeService employeeService;

    EmployeesRespDto employeesRespDto = TestDataGenerator.getEmployeesRespDto();

    @Test
    public void testEmployeesByNameSearch() throws IOException {
        String name = "Tom";
        Mockito.when(client.getAllEmployees()).thenReturn(employeesRespDto);

        EmployeesRespDto searchedEmployeesRespDto = employeeService.getEmployeesByNameSearch(name);
        List<EmpRespDto> empRespDtoList = searchedEmployeesRespDto.getData();

        Assert.assertNotNull(searchedEmployeesRespDto);
        Assert.assertEquals(Constants.SUCCESS, searchedEmployeesRespDto.getStatus());
        Assert.assertNotNull(empRespDtoList);
        empRespDtoList.forEach(emp -> {
            Assert.assertFalse(!emp.getEmployeeName().contains(name));
        });
    }

    @Test
    public void testHighestSalaryOfEmployee() throws IOException {
        Mockito.when(client.getAllEmployees()).thenReturn(employeesRespDto);

        HighestQueryDataRespDto highestQueryDataRespDto = employeeService.getHighestSalaryOfEmployees();
        Assert.assertNotNull(highestQueryDataRespDto);
        Assert.assertEquals(Constants.SUCCESS, highestQueryDataRespDto.getStatus());
        Assert.assertEquals(Long.valueOf(900800), Long.valueOf(highestQueryDataRespDto.getHighestSalary()));
    }

    @Test
    public void testTopKEmployeesWithHighEarning() throws IOException {
        Mockito.when(client.getAllEmployees()).thenReturn(employeesRespDto);
        List<String> expectedListOfTopKEmps  = Arrays.asList("Tom salaey Nixon", "Samsher Saxena", "R R", "Bahdur JK", "Sofi Don");

        HighestQueryDataRespDto highestQueryDataRespDto = employeeService.getTopKHighestEarningEmployeeNames(5);
        Assert.assertNotNull(highestQueryDataRespDto);
        Assert.assertEquals(Constants.SUCCESS, highestQueryDataRespDto.getStatus());

        List<String> actualListOfTopKEmps = highestQueryDataRespDto.getHighestEarningEmployees();

        expectedListOfTopKEmps.forEach(empName -> {
            Assert.assertFalse(!actualListOfTopKEmps.contains(empName));
        });
    }
}
