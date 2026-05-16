package com.govmedcare.dao;

import com.govmedcare.model.CartItem;
import com.govmedcare.repository.OrderRepository;
import com.govmedcare.utils.DBConnection;
import com.govmedcare.utils.QueryUtil;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDao implements OrderRepository {
    Logger logger = Logger.getLogger(OrderRepository.class.getName());

    @Override
    public Long createOrder(Long patient_id, double amount) {

        try (Connection conn = DBConnection.getConnection();
             // Creating order and returning the auto-incremented key from database
             PreparedStatement ps = conn.prepareStatement(QueryUtil.createOrder, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, patient_id);
            ps.setDouble(2, amount);
            ps.setString(3, "PAID");
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Unable to create order");
        }
        return 0L;
    }

    @Override
    public boolean saveOrderItem(Long order_id, CartItem item) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.saveOrderItem)
        ) {
            ps.setLong(1, order_id);
            ps.setLong(2, item.getMedicineId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPrice());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Unable to save order item");
        }
        return false;
    }

}

