package com.springbootproject.exception.student;

public class StudentDtoListDtoInputException extends RuntimeException{
    public StudentDtoListDtoInputException(String errorMessage) {
        super(errorMessage);
    }
}
