package com.govmedcare.model;

public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long medicineId;
    private String medicineName;
    private int quantity;
    private double price;


    public OrderItem(Long orderId, Long medicineId, String medicineName, int quantity, double price) {
        this.orderId = orderId;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.price = price;
    }


    public OrderItem(Long orderItemId, Long orderId, Long medicineId, String medicineName, int quantity, double price) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}