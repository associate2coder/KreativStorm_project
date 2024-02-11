package com.springbootproject.exception.student;

public class StudentWrongEmailException extends RuntimeException{
    public StudentWrongEmailException(String errorMessage) {
        super(errorMessage);
    }
}
