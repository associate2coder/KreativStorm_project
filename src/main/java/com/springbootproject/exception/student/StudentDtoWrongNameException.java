package com.springbootproject.exception.student;

public class StudentDtoWrongNameException extends RuntimeException{
    public StudentDtoWrongNameException(String errorMessage) {
        super(errorMessage);
    }
}
