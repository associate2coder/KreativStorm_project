package com.springbootproject.exception.student;

public class StudentDtoWrongIdException extends RuntimeException{
    public StudentDtoWrongIdException(String errorMessage) {
        super(errorMessage);
    }
}
