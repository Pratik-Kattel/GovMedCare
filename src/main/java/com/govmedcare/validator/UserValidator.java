package com.govmedcare.validator;

import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.User;

public class UserValidator {
    private static final String Email_Regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String Phone_Regex="\\d{10}";

    public static void validateRegistrationCredentials(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(user.getAddress()==null || user.getAddress().isEmpty()){
            throw new IllegalArgumentException("Address cannot be empty");
        }
        if(user.getPhone() == null || user.getPhone().isEmpty() || !user.getPhone().matches(Phone_Regex)){
            throw new IllegalArgumentException("Please enter valid phone number");
        }
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());

    }
    public static void validateEmail(String email){
        if(email==null || !email.matches(Email_Regex)){
            throw new IllegalArgumentException("Please enter valid email address");
        }

    }
    public static void validatePassword(String password){
        if(password==null || password.length()<5){
            throw new IllegalArgumentException("Password cannot be empty and must me 6 character long");
        }
    }
    public static void validateLoginCredentials(String email,String password){
        validateEmail(email);
        validatePassword(password);
    }
}
