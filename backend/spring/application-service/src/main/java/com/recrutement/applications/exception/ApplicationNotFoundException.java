package com.recrutement.applications.exception;

public class ApplicationNotFoundException extends RuntimeException{

    public ApplicationNotFoundException(String message){
        super(message);
    }
}
