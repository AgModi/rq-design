package com.example.rqdesign.employees.exception;

public class DownstreamResponseParsingException extends RuntimeException{
    public DownstreamResponseParsingException(String message) {
        super(message);
    }
}
