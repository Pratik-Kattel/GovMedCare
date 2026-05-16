package com.govmedcare.utils;

public class QueryUtil {
   public static final String saveUser="Insert into users(name,email,password,role,phone,address) VALUES(?,?,?,?,?,?)";
   public static final String checkByEmail="SELECT * FROM users where email=?";
   public static final String saveMedicine="Insert into medicines(name,description,price,quantity,category_id,image_url) VALUES (?,?,?,?,?,?)";
   public static final String checkByName="SELECT * FROM medicines where name=?";
   public static final String getCategories="Select * from categories";
   public static final String getPendingMedicines ="SELECT m.*, c.name AS category_name FROM medicines m JOIN categories c ON m.category_id = c.category_id WHERE m.is_verified = false";;
   public static final String getApprovedMedicines ="SELECT m.*, c.name AS category_name FROM medicines m JOIN categories c ON m.category_id = c.category_id WHERE m.is_verified = true";
   public static final String validateMedicines="Update medicines SET is_verified=true where medicine_id=?";
   public static final String deleteMedicine="DELETE from medicines where medicine_id=?";
   public static final String getUsers="SELECT * FROM users where status='active'";
   public static final String blockUser="UPDATE users set status='blocked' WHERE user_id=?";
   public static final String getAllMedicineAndCategory="SELECT m.*, c.name AS category_name FROM medicines m JOIN categories c ON m.category_id = c.category_id;";
   public static final String getMedicinesByCategory = "SELECT m.*, c.name AS category_name FROM medicines m JOIN categories c ON m.category_id = c.category_id WHERE m.category_id = ?";
   public static final String countPending  = "SELECT COUNT(*) FROM medicines WHERE is_verified = false";
   public static final String countApproved = "SELECT COUNT(*) FROM medicines WHERE is_verified = true";
   public static final String updateStock="UPDATE medicines set quantity= quantity + ? where medicine_id=?";
   public static final String createCart = "INSERT INTO cart(patient_id) VALUES(?)";
   public static final String getCartByUser = "SELECT * FROM cart WHERE patient_id=?";
   public static final String checkCartMedicine = "SELECT * FROM cart_items WHERE cart_id=? AND medicine_id=?";
   public static final String addCartItem = "INSERT INTO cart_items(cart_id,medicine_id,quantity) VALUES(?,?,?)";
   public static final String updateCartQuantity = "UPDATE cart_items SET quantity=quantity+? WHERE cart_id=? AND medicine_id=?";
}
