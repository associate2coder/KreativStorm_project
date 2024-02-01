package com.springbootproject.exception;

public class StudentWithSuchAnIdDoesNotExistException extends RuntimeException{
    public StudentWithSuchAnIdDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
