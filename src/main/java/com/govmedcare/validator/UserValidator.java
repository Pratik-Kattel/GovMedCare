package com.govmedcare.validator;

import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.User;

public class UserValidator {
    private static final String Email_Regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static void validateEmailAndPassword(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!user.getEmail().matches(Email_Regex)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (user.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be greater than 6 digits");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
