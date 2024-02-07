package com.springbootproject.exception.student;

public class StudentDtoNullException extends RuntimeException{
    public StudentDtoNullException(String errorMessage) {
        super(errorMessage);
    }
}
