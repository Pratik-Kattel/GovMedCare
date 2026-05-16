package com.govmedcare.repository;

import com.govmedcare.model.CartItem;

import java.util.List;

public interface CartRepository {
    Long createCart(Long patient_id);
    boolean cartMedicineExists(Long cart_id, Long medicine_id);
    boolean addCartItem(Long cart_id,Long medicine_id,int quantity);
    boolean updateExistingQuantity(Long cart_id,Long medicine_id,int quantity);
    List<CartItem> getCartItems(Long patient_id);
    boolean clearCart(Long patient_id);
}
