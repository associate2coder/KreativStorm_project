package com.springbootproject.exception.student;

public class StudentWrongNameException extends RuntimeException{
    public StudentWrongNameException(String errorMessage) {
        super(errorMessage);
    }
}
