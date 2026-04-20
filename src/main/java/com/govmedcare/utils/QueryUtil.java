package com.govmedcare.utils;

public class QueryUtil {
   public static final String saveUser="Insert into users(name,email,password,role,phone,address) VALUES(?,?,?,?,?,?)";
   public static final String checkByEmail="SELECT * FROM users where id=?";

}
