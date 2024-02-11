package com.example.rqdesign.employees.exception;

public class DownstreamConnectException extends RuntimeException{
    public DownstreamConnectException(String message) {
        super(message);
    }
}
