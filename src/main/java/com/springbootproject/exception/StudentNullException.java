package com.springbootproject.exception;

public class StudentNullException extends RuntimeException{
    public StudentNullException(String errorMessage) {
        super(errorMessage);
    }
}
