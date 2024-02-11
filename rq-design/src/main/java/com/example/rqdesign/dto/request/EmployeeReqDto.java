package com.example.rqdesign.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeReqDto {

    @NotBlank
    private String id;

    @NotBlank
    @NotNull
    @JsonProperty("name")
    private String employeeName;

    @NotBlank
    @JsonProperty("salary")
    private String employeeSalary;

    @NotBlank
    @JsonProperty("age")
    private String employeeAge;

    @JsonProperty("profile_image")
    private Object profileImage;
}
