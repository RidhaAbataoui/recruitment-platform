package com.recrutement.applications.exception;

public class InvalidApplicationStatusTransitionException extends RuntimeException{
    public InvalidApplicationStatusTransitionException(String message){super(message);}
}
