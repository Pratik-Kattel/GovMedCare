package com.govmedcare.service;

import com.govmedcare.exception.InvalidAmountException;
import com.govmedcare.exception.PaymentMethodException;
import com.govmedcare.exception.RequiredPaymentException;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.CartItem;
import com.govmedcare.model.Medicine;
import com.govmedcare.types.PaymentStatus;

import java.util.List;

public class CheckOutService {
    MedicineService medicineService = new MedicineService();
    CartService cartService = new CartService();
    OrderService orderService = new OrderService();
    PaymentService paymentService = new PaymentService();
    public boolean checkOut(Long patient_id, double paidAmount, String paymentMethod) {
        // validate user
        if (patient_id <= 0) {throw new UserDoesNotExistsException("Invalid user, please try again !!");
        }
        // validate amount
        if (paidAmount <= 0) {throw new InvalidAmountException("Amount must be greater than 0");
        }

        // validate payment method
        if (paymentMethod == null || paymentMethod.trim().isEmpty()) {throw new PaymentMethodException("Please choose one payment option");
        }

        // get cart items
        List<CartItem> cartItems = cartService.getCartItemsService(patient_id);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty, please add some items to cart");
        }

        // calculate total
        double totalAmount = 0;
        for (CartItem item : cartItems) {
            totalAmount += item.getQuantity() * item.getPrice();
        }

        // validate payment
        if (paidAmount != totalAmount) {
            throw new RequiredPaymentException("Please pay the exact amount NPR. " + totalAmount);
        }

        // validate stock availability (ONLY validation loop)
        for (CartItem item : cartItems) {
            Medicine medicine = medicineService.getMedicineByIdService(item.getMedicineId());
            if (medicine == null) {
                throw new RuntimeException("Medicine not found");
            }

            if (item.getQuantity() > medicine.getQuantity()) {throw new RuntimeException(medicine.getName() + " only has " + medicine.getQuantity() + " items available");
            }
        }
        // create order (AFTER validation)
        Long order_id = orderService.createOrderService(patient_id, totalAmount);

        // save order items + reduce stock
        for (CartItem item : cartItems) {
            orderService.saveOrderItemService(order_id, item);
            medicineService.ReduceMedicineStockService(item.getMedicineId(), item.getQuantity());
        }
        // save payment
        paymentService.savePayment(order_id, paidAmount, PaymentStatus.paid, paymentMethod
        );
        // clear cart
        cartService.clearCartService(patient_id);
        return true;
    }
}