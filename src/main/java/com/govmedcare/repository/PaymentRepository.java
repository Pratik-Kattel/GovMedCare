package com.govmedcare.repository;

import com.govmedcare.types.PaymentStatus;

public interface PaymentRepository {
    boolean savePayment(Long orderID, double amount, PaymentStatus PaymentStatus, String paymentOption);
}
