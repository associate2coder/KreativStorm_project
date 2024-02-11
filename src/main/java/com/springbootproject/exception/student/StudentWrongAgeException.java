package com.springbootproject.exception.student;

public class StudentWrongAgeException extends RuntimeException{
    public StudentWrongAgeException(String errorMessage) {
        super(errorMessage);
    }
}
