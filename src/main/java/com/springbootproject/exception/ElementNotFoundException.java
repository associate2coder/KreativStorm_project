package com.springbootproject.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String message) {
        super(message);
    }
}
