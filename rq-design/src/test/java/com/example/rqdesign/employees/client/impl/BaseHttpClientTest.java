package com.example.rqdesign.employees.client.impl;

import com.example.rqdesign.employees.dto.response.EmployeesRespDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class BaseHttpClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BaseHttpClient client;

    private String host = "dummy.restapiexample.com";

    private String port = "8080";

    private String scheme = "https";

    private String apiUrl = "/api/v1/employees";

    @Test
    public void testGetEmployees() {
        String url = scheme + "://" + host + ":" + port + apiUrl;
        client.callGetHttpClient(url, EmployeesRespDto.class, null, null);
    }
}
