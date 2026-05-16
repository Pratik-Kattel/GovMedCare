package com.govmedcare.dao;

import com.govmedcare.repository.PaymentRepository;
import com.govmedcare.types.PaymentStatus;
import com.govmedcare.utils.DBConnection;
import com.govmedcare.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentDao implements PaymentRepository {
    Logger logger=Logger.getLogger(PaymentDao.class.getName());
    @Override
    public boolean savePayment(Long orderID, double amount, PaymentStatus paymentStatus, String PaymentOption) {
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(QueryUtil.savePayment)
        ){
            ps.setLong(1,orderID);
            ps.setDouble(2,amount);
            ps.setString(3,PaymentOption);
            ps.setString(4,paymentStatus.name());
            int rowsAffected=ps.executeUpdate();
            return rowsAffected>0;
        }
        catch (SQLException e){
            logger.log(Level.SEVERE,"Unable to save payment");

        }
        return false;
    }
}
