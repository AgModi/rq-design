package com.example.rqdesign.employees.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateEmp {

    private String id;

    @JsonProperty("name")
    private String employeeName;

    @JsonProperty("salary")
    private String employeeSalary;

    @JsonProperty("age")
    private String employeeAge;

    @JsonProperty("profile_image")
    private Object profileImage;
}