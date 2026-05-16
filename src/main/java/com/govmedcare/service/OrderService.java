package com.govmedcare.service;

import com.govmedcare.dao.OrderDao;
import com.govmedcare.exception.InvalidAmountException;
import com.govmedcare.exception.InvalidOrderException;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.CartItem;

public class OrderService {
    OrderDao orderDao = new OrderDao();

    public Long createOrder(Long patient_id, double amount) {
        if (patient_id <= 0) {
            throw new UserDoesNotExistsException("Invalid user, please try again!!");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }
        return orderDao.createOrder(patient_id, amount);
    }

    public boolean saveOrderItem(Long order_id, CartItem item) {
        if (order_id <= 0) {
            throw new InvalidOrderException("Invalid order please try again !!");
        }
        return orderDao.saveOrderItem(order_id, item);
    }
}
