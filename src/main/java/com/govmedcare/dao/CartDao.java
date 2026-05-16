package com.govmedcare.dao;

import com.govmedcare.repository.CartRepository;
import com.govmedcare.utils.DBConnection;
import com.govmedcare.utils.QueryUtil;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartDao implements CartRepository {
    Logger logger = Logger.getLogger(CartRepository.class.getName());

    @Override
    public Long createOrUpdateCart(Long patient_id) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(QueryUtil.getCartByUser);
            // Checking if cart for the user exists or not
            ps.setLong(1, patient_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.getLong("cart_id");
            }

            // creating cart if doesn't exists and returning the auto_incremented key from mySQL.
            PreparedStatement createCart = conn.prepareStatement(QueryUtil.createCart, Statement.RETURN_GENERATED_KEYS);
            createCart.setLong(1, patient_id);
            createCart.executeUpdate();

            ResultSet resultSet = createCart.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong("cart_id");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Unable to create or fetch cart");
        }
        return 0L;
    }

    @Override
    public boolean cartMedicineExists(Long cart_id, Long medicine_id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.checkCartMedicine)
        ) {
            ps.setLong(1, cart_id);
            ps.setLong(2, medicine_id);
            ResultSet rs=ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
         logger.log(Level.SEVERE,"Unable to fetch the cart medicines");
        }
        return false;
    }


}
