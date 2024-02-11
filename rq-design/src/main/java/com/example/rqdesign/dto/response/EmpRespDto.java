package com.example.rqdesign.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class EmpRespDto {

    private String id;

    @JsonAlias({"employee_name", "name"})
    private String employeeName;

    @JsonAlias({"employee_salary", "salary"})
    private String employeeSalary;

    @JsonAlias({"employee_age" ,"age"})
    private String employeeAge;

    @JsonProperty("profile_image")
    private Object profileImage;
}