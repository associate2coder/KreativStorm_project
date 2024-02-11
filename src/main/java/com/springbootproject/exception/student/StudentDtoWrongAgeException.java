package com.springbootproject.exception.student;

public class StudentDtoWrongAgeException extends RuntimeException{
    public StudentDtoWrongAgeException(String errorMessage) {
        super(errorMessage);
    }
}
