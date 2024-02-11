package com.springbootproject.exception.student;

public class StudentDtoException extends RuntimeException{
    public StudentDtoException(String errorMessage) {
        super(errorMessage);
    }
}
