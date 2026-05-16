package com.govmedcare.repository;

public interface CartRepository {
    Long createOrUpdateCart(Long patient_id);
    boolean cartMedicineExists(Long cart_id, Long medicine_id);
}
