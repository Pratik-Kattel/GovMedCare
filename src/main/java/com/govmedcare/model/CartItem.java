package com.govmedcare.model;

public class CartItem {
    private Long cartItemId;
    private Long cartId;
    private Long medicineId;
    private int quantity;

    public CartItem(Long cartId, Long medicineId, int quantity) {
        this.cartId = cartId;
        this.medicineId = medicineId;
        this.quantity = quantity;
    }


    public CartItem(Long cartItemId, Long cartId, Long medicineId, int quantity) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.medicineId = medicineId;
        this.quantity = quantity;
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
