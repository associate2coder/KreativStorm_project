package com.springbootproject.exception.student;

public class StudentWrongIdException extends RuntimeException{
    public StudentWrongIdException(String errorMessage) {
        super(errorMessage);
    }
}
