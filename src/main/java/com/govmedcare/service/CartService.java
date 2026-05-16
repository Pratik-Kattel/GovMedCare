package com.govmedcare.service;

import com.govmedcare.dao.CartDao;
import com.govmedcare.exception.InvalidQuantityException;
import com.govmedcare.exception.UserDoesNotExistsException;

public class CartService {
    CartDao cartDao=new CartDao();

    public boolean addToCartService(Long patient_id, Long medicine_id,int quantity){
        if(patient_id<=0){
            throw new UserDoesNotExistsException("Unknown user, please try again");
        }
        if(medicine_id<=0){
            throw new RuntimeException("Invalid medicine, please try again");
        }
        if(quantity<=0){
            throw new InvalidQuantityException("Invalid quantity, quantity must be greater than one");
        }
        Long cart_id= cartDao.createOrUpdateCart(patient_id);
        boolean exists=cartDao.cartMedicineExists(cart_id,medicine_id);

        if(exists){
            return cartDao.updateExistingQuantity(cart_id,medicine_id,quantity);
        }
        return cartDao.addCartItem(cart_id,medicine_id,quantity);

    }
}
