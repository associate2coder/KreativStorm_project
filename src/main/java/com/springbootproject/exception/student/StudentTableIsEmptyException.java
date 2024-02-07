package com.springbootproject.exception.student;

public class StudentTableIsEmptyException extends RuntimeException{
    public StudentTableIsEmptyException(String errorMessage) {
        super(errorMessage);
    }
}
