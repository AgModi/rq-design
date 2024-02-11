package com.example.rqdesign.exception;

public class DownstreamResponseParsingException extends RuntimeException{
    public DownstreamResponseParsingException(String message) {
        super(message);
    }
}
