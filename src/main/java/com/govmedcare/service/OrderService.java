package com.govmedcare.service;

import com.govmedcare.dao.OrderDao;
import com.govmedcare.exception.InvalidAmountException;
import com.govmedcare.exception.InvalidOrderException;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.CartItem;
import com.govmedcare.model.Order;
import com.govmedcare.model.OrderItem;

import java.util.List;

public class OrderService {
    OrderDao orderDao = new OrderDao();

    public Long createOrderService(Long patient_id, double amount) {
        if (patient_id <= 0) {
            throw new UserDoesNotExistsException("Invalid user, please try again!!");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }
        return orderDao.createOrder(patient_id, amount);
    }

    public boolean saveOrderItemService(Long order_id, CartItem item) {
        return orderDao.saveOrderItem(order_id, item);
    }
    public List<Order> getPurchaseHistoryService(Long patient_id) {
        if(patient_id<=0){
            throw new UserDoesNotExistsException("Invalid user, please try again !!");
        }
        return orderDao.getPurchaseHistory(patient_id);
    }
    public List<OrderItem> getSoldHistoryService() {
        return orderDao.getSoldHistory();
    }
}
