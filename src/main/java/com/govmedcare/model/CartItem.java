package com.govmedcare.model;

public class CartItem {
    private Long cartItemId;
    private Long cartId;
    private Long medicineId;
    private int quantity;
    private String medicineName;
    private double price;

    public CartItem() {

    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public CartItem(Long cartId, Long medicineId, int quantity) {
        this.cartId = cartId;
        this.medicineId = medicineId;
        this.quantity = quantity;
    }


    public CartItem(Long cartItemId, Long cartId, Long medicineId, int quantity, String medicineName, double price) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.medicineId = medicineId;
        this.quantity = quantity;
        this.medicineName = medicineName;
        this.price = price;
    }


    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
