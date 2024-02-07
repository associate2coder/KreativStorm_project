package com.springbootproject.exception.student;

public class StudentTableDoesNotExistException extends RuntimeException{
    public StudentTableDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
