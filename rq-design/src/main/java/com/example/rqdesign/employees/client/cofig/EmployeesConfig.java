package com.example.rqdesign.employees.client.cofig;


import com.example.rqdesign.employees.client.IEmpClient;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;

@Component
@ConfigurationProperties(IEmpClient.EMPLOYEES_CLIENT)
@Setter
public class EmployeesConfig extends BaseConfig{

    @NotBlank
    private String getEmployees;

    @NotBlank
    private String getEmpDetails;

    @NotBlank
    private String createEmp;

    @NotBlank
    private String deleteEmp;

    public String getGetEmployees() {
        return this.getScheme() + "://" + this.getHost()  + getEmployees;
    }

    public String getGetEmpDetails(String id) {
        return new String(this.getScheme() + "://" + this.getHost()  + getEmpDetails).replace("{id}", id);
    }

    public String getCreateEmp() {
        return this.getScheme() + "://" + this.getHost()  + createEmp;
    }

    public String getDeleteEmp(String id) {
        return new String(this.getScheme() + "://" + this.getHost()  + deleteEmp).replace("{id}", id);
    }
}
