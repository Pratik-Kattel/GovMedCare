package com.govmedcare.utils;

public class QueryUtil {
   public static final String saveUser="Insert into users(name,email,password,role,phone,address) VALUES(?,?,?,?,?,?)";
   public static final String checkByEmail="SELECT * FROM users where email=?";
   public static final String saveMedicine="Insert into medicines(name,description,price,quantity,category_id,imageURL) VALUES (?,?,?,?,?,?)";
   public static final String checkByName="SELECT * FROM medicines where name=?";

}
