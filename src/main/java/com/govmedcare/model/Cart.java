package com.govmedcare.model;
import java.sql.Timestamp;

public class Cart {
    private Timestamp created_at;
    private Long cart_id;
    private Long patient_ID;


    public Cart(Timestamp created_at, Long patient_ID) {
        this.created_at = created_at;
        this.patient_ID = patient_ID;
    }

    public Cart(Long cart_id, Long patient_ID, Timestamp created_at) {
        this.cart_id = cart_id;
        this.patient_ID = patient_ID;
        this.created_at = created_at;
    }

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public Long getPatient_ID() {
        return patient_ID;
    }

    public void setPatient_ID(Long patient_ID) {
        this.patient_ID = patient_ID;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }



}
