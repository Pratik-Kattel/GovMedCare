package com.govmedcare.exception;

public class RequiredPaymentException extends RuntimeException {
    public RequiredPaymentException(String message) {
        super(message);
    }
}
