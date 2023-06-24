package com.example.medicalservice.exception;


public class AuthenticationFailedException extends RuntimeException {

    public AuthenticationFailedException(String message){
        super(message);
    }
 }
