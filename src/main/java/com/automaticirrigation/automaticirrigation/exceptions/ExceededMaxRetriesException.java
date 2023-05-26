package com.automaticirrigation.automaticirrigation.exceptions;

public class ExceededMaxRetriesException extends RuntimeException {
    public ExceededMaxRetriesException(String message) {
        super(message);
    }
}