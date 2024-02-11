package com.example.rqdesign.exception;

public class DownstreamNotServingException extends RuntimeException{
    public DownstreamNotServingException(String message) {
        super(message);
    }
}
