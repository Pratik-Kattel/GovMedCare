package com.govmedcare.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashPasswordUtility {

    // Hash password
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Verify password
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}