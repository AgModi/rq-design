package com.example.rqdesign.employees.dto.response;

import com.example.rqdesign.employees.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class HighestQueryDataRespDto {

    private String status;

    private Integer highestSalary;

    private List<String> highestEarningEmployees;

    public static HighestQueryDataRespDto convertFromEmpRestDto(EmpRespDto empRespDto) {
        HighestQueryDataRespDto highestQueryDataRespDto = new HighestQueryDataRespDto(Constants.SUCCESS, Integer.parseInt(empRespDto.getEmployeeSalary()), null);
        return highestQueryDataRespDto;
    }

    public static HighestQueryDataRespDto convertFromEmpRestDto(List<String> empNames) {
        HighestQueryDataRespDto highestQueryDataRespDto = new HighestQueryDataRespDto(Constants.SUCCESS, null, empNames);
        return highestQueryDataRespDto;
    }
}
