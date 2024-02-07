package com.springbootproject.exception.student;

public class StudentWithSuchAnIdDoesNotExistException extends RuntimeException{
    public StudentWithSuchAnIdDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
