package com.example.rqdesign.employees.exception;

public class DownstreamNotServingException extends RuntimeException{
    public DownstreamNotServingException(String message) {
        super(message);
    }
}
