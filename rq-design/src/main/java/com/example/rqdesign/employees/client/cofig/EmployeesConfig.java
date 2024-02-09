package com.example.rqdesign.employees.client.cofig;


import com.example.rqdesign.employees.client.iClient.IEmpClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@ConfigurationProperties(IEmpClient.EMPLOYEES_CLIENT)
@Data
public class EmployeesConfig extends BaseConfig{

    @NotBlank
    public String url;
}
