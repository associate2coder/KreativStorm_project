package com.springbootproject.exception;

public class TeacherWithSuchAnIdDoesNotExistException extends RuntimeException{
    public TeacherWithSuchAnIdDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
}
