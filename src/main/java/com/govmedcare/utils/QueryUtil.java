package com.govmedcare.utils;

public class QueryUtil {
   public static final String saveUser="Insert into users(name,email,password,role,phone,address) VALUES(?,?,?,?,?,?)";
   public static final String checkByEmail="SELECT * FROM users where email=?";
   public static final String saveMedicine="Insert into medicines(name,description,price,quantity,category_id,image_url) VALUES (?,?,?,?,?,?)";
   public static final String checkByName="SELECT * FROM medicines where name=?";
   public static final String getCategories="Select * from categories";
   public static final String getPendingMedicines ="Select * from medicines where is_verified=false";
   public static final String validateMedicines="Update medicines SET is_verified=true where medicine_id=?";
   public static final String deleteMedicine="DELETE from medicines where medicine_id=?";
   public static final String getUsers="SELECT * FROM users where status='active'";
   public static final String blockUser="UPDATE users set status='blocked' WHERE user_id=?";


}
