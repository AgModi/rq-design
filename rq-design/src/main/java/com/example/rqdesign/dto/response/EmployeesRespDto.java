package com.example.rqdesign.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class EmployeesRespDto {

    public String status;

    public List<EmpRespDto> data;
}
