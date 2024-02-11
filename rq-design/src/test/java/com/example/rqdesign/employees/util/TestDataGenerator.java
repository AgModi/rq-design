package com.example.rqdesign.employees.util;

import com.example.rqdesign.employees.dto.response.EmployeesRespDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestDataGenerator {

    private static ObjectMapper mapper = new ObjectMapper();
    public static EmployeesRespDto getEmployeesRespDto(){
        File file = null;
        EmployeesRespDto someClassObject = null;
        try {
            file = ResourceUtils.getFile("classpath:templates/employees_20.json");
            someClassObject = mapper.readValue(file, EmployeesRespDto.class);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IOException");
            throw new RuntimeException(e);
        }

        return someClassObject;
    }
}
