package com.govmedcare.repository;
import com.govmedcare.model.CartItem;
import com.govmedcare.model.Order;
import com.govmedcare.model.OrderItem;

import java.util.List;

public interface OrderRepository {
    Long createOrder(Long patient_id, double amount);
    boolean saveOrderItem(Long order_id, CartItem item);
    List<Order> getPurchaseHistory(Long patient_id);
    List<OrderItem> getSoldHistory(Long supplier_id);
}
