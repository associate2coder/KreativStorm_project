package com.springbootproject.exception.student;

public class StudentNullException extends RuntimeException{
    public StudentNullException(String errorMessage) {
        super(errorMessage);
    }
}
