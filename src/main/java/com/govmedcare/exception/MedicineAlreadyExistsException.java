package com.govmedcare.exception;

public class MedicineAlreadyExistsException extends RuntimeException {
    public MedicineAlreadyExistsException(String message) {
        super(message);
    }
}
