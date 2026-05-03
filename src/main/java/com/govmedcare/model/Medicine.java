package com.govmedcare.model;

import java.sql.Timestamp;

public class Medicine {

    private long medicineID;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private long category_id;
    private boolean is_verified;
    private Timestamp created_at;
    private String imageURL;


    private String category_name;

    public Medicine(String name, String description, double price, int quantity, String imageURL, Long category_id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.category_id = category_id;
    }

    public Medicine() {
    }

    public Medicine(String name, String description, double price, int quantity, long category_id, boolean is_verified, String imageURL) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category_id = category_id;
        this.is_verified = is_verified;
        this.imageURL = imageURL;
    }

    public Medicine(long medicineID, String name, String description, double price, int quantity, long category_id, boolean is_verified, Timestamp created_at, String imageURL) {
        this.medicineID = medicineID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category_id = category_id;
        this.is_verified = is_verified;
        this.created_at = created_at;
        this.imageURL = imageURL;
    }

    public long getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(long medicineID) {
        this.medicineID = medicineID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
