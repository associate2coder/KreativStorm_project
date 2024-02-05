package com.springbootproject.exception;

public class StudentDtoNullException extends RuntimeException{
    public StudentDtoNullException(String errorMessage) {
        super(errorMessage);
    }
}
