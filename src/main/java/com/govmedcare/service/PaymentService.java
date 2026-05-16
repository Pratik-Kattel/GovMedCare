package com.govmedcare.service;

import com.govmedcare.dao.PaymentDao;
import com.govmedcare.exception.InvalidAmountException;
import com.govmedcare.exception.InvalidOrderException;
import com.govmedcare.types.PaymentStatus;

public class PaymentService {
    PaymentDao paymentDao=new PaymentDao();
    public boolean savePayment(Long orderID, double amount, PaymentStatus paymentStatus, String PaymentOption) {
        if(orderID<=0){
            throw new InvalidOrderException("Invalid orderID, please try again later !!");
        }
        if(amount<=0){
            throw new InvalidAmountException("Amount must be greater than 0");
        }
        return paymentDao.savePayment(orderID,amount,PaymentStatus.paid,PaymentOption);
    }
}
