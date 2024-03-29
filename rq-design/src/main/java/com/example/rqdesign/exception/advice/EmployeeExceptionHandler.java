package com.example.rqdesign.exception.advice;

import com.example.rqdesign.controller.EmployeesController;
import com.example.rqdesign.dto.response.CreateEmpRespDto;
import com.example.rqdesign.exception.BaseHttpClientException;
import com.example.rqdesign.exception.DownstreamConnectException;
import com.example.rqdesign.exception.DownstreamNotServingException;
import com.example.rqdesign.exception.DownstreamResponseParsingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {EmployeesController.class})
@Slf4j
@Configuration
public class EmployeeExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DownstreamResponseParsingException.class)
    public CreateEmpRespDto handleException(DownstreamResponseParsingException exception) {
        return new CreateEmpRespDto(exception.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DownstreamConnectException.class)
    public CreateEmpRespDto handleException(DownstreamConnectException exception) {
        return new CreateEmpRespDto(exception.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DownstreamNotServingException.class)
    public CreateEmpRespDto handleException(DownstreamNotServingException exception) {
        return new CreateEmpRespDto(exception.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BaseHttpClientException.class)
    public CreateEmpRespDto handleException(BaseHttpClientException exception) {
        return new CreateEmpRespDto(exception.getMessage(), null);
    }
}
