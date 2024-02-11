package com.springbootproject.exception.student;

public class StudentDtoWrongEmailException extends RuntimeException{
    public StudentDtoWrongEmailException(String errorMessage) {
        super(errorMessage);
    }
}
