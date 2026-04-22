package com.govmedcare.validator;

import com.govmedcare.model.Medicine;

public class MedicineValidator {
    public static void validateMedicine(Medicine medicine) {

        // Name Validation
        if (medicine.getName() == null || medicine.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Medicine name cannot be empty");
        }

        //  Description validation
        if (medicine.getDescription() == null || medicine.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Medicine description cannot be empty");
        }

        //  Price validation
        if (medicine.getPrice() <= 0) {
            throw new IllegalArgumentException("Medicine price must be greater than 0");
        }

        //  Quantity validation
        if (medicine.getQuantity() <= 0) {
            throw new IllegalArgumentException("Medicine quantity cannot be negative or zero");
        }

        // Category validation
        if (medicine.getCategory_id() <= 0) {
            throw new IllegalArgumentException("Invalid category selected");
        }

        // Image validation
        if (medicine.getImageURL() == null || medicine.getImageURL().trim().isEmpty()) {
            throw new IllegalArgumentException("Medicine image is required");
        }

    }
}
