package com.govmedcare.service;

import com.govmedcare.dao.CartDao;
import com.govmedcare.exception.InvalidQuantityException;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.CartItem;

import java.util.List;

public class CartService {
    CartDao cartDao = new CartDao();

    public boolean addToCartService(Long patient_id, Long medicine_id, int quantity) {
        //  Validating the id
        if (patient_id <= 0) {
            throw new UserDoesNotExistsException("Unknown user, please try again");
        }
        if (medicine_id <= 0) {
            throw new RuntimeException("Invalid medicine, please try again");
        }
        if (quantity <= 0) {
            throw new InvalidQuantityException("Invalid quantity, quantity must be greater than one");
        }
        // Crate or update the cart for user
        Long cart_id = cartDao.createCart(patient_id);
        boolean exists = cartDao.cartMedicineExists(cart_id, medicine_id);

        // Update medicine in cart if it already exists there
        if (exists) {
            return cartDao.updateExistingQuantity(cart_id, medicine_id, quantity);
        }

        // Add to cart if not already exists
        return cartDao.addCartItem(cart_id, medicine_id, quantity);

    }

    public List<CartItem> getCartItemsService(Long patient_id){
        if(patient_id<=0){
            throw new UserDoesNotExistsException("Invalid user, please try again !!");
        }
        return cartDao.getCartItems(patient_id);
    }
    public boolean clearCartService(Long patient_id){
        if (patient_id<=0){
            throw new UserDoesNotExistsException("Invalid user, please try again later !");
        }
        return cartDao.clearCart(patient_id);
    }
    public boolean increaseQuantityService(Long patientId, Long medicineId) {
        if (patientId <= 0 || medicineId <= 0) {
            throw new RuntimeException("Invalid request");
        }
        return cartDao.increaseQuantity(patientId, medicineId);
    }
    public boolean decreaseQuantityService(Long patient_id, Long medicine_id) {
        if (patient_id <= 0 || medicine_id <= 0) {
            throw new RuntimeException("Invalid request");
        }
        return cartDao.decreaseQuantity(patient_id,medicine_id);
    }
    public boolean removeSingleItemService(Long patient_id, Long medicine_id) {
        if (patient_id <= 0 || medicine_id <= 0) {
            throw new RuntimeException("Invalid request");
        }
        return cartDao.removeSingleItem(patient_id,medicine_id);
    }


}
