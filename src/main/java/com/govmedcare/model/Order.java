package com.govmedcare.model;
import com.govmedcare.types.OrderStatus;

import java.util.Date;
import java.util.List;

public class Order {
    private Long orderId;
    private Long patientId;
    private double totalAmount;
    private OrderStatus status;
    private Date createdAt;
    private List<OrderItem> items;

    public Order(Long patientId, double totalAmount, OrderStatus status, Date createdAt, List<OrderItem> items) {
        this.patientId = patientId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
    }
    public Order(Long orderId, Long patientId, double totalAmount, OrderStatus status, Date createdAt, List<OrderItem> items) {
        this.orderId = orderId;
        this.patientId = patientId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}