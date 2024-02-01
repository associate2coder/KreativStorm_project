package com.springbootproject.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EnrollmentStatusException extends RuntimeException {

    public EnrollmentStatusException(String message) {
        super(message);
    }
}
