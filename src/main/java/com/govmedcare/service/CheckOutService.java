package com.govmedcare.service;
import com.govmedcare.exception.InvalidAmountException;
import com.govmedcare.exception.PaymentMethodException;
import com.govmedcare.exception.RequiredPaymentException;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.CartItem;
import com.govmedcare.types.PaymentStatus;

import java.util.List;

public class CheckOutService {
    MedicineService medicineService=new MedicineService();
    CartService cartService=new CartService();
    OrderService orderService=new OrderService();
    PaymentService paymentService=new PaymentService();

    public boolean checkOut(Long patient_id, double paidAmount, String paymentMethod) {
        if (patient_id <= 0) {
            throw new UserDoesNotExistsException("Invalid user, please try again !!");
        }
        if (paidAmount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0");
        }
        if (paymentMethod == null) {
            throw new PaymentMethodException("Please choose one payment option");
        }
        List<CartItem> cartItems = cartService.getCartItemsService(patient_id);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty, please add some items to cart");
        }
        double totalAmount=0;

        for (CartItem item:cartItems){
            totalAmount+=item.getQuantity()*item.getPrice();
        }
        if(paidAmount!=totalAmount){
            throw new RequiredPaymentException("Please pay the exact amount NPR."+totalAmount);
        }
        Long orderID=orderService.createOrder(patient_id,totalAmount);

        for (CartItem item:cartItems){
            orderService.saveOrderItem(orderID,item);
            medicineService.ReduceMedicineStockService(item.getMedicineId(), item.getQuantity());
        }
        paymentService.savePayment(orderID, paidAmount, PaymentStatus.paid,paymentMethod);
        cartService.clearCartService(patient_id);
        return true;
    }
}
