package com.govmedcare.repository;

import com.govmedcare.model.CartItem;

public interface OrderRepository {
    Long createOrder(Long patient_id, double amount);
    boolean saveOrderItem(Long order_id, CartItem item);
}
