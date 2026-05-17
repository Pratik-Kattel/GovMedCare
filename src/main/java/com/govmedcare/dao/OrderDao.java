package com.govmedcare.dao;
import com.govmedcare.model.CartItem;
import com.govmedcare.model.Order;
import com.govmedcare.model.OrderItem;
import com.govmedcare.repository.OrderRepository;
import com.govmedcare.types.OrderStatus;
import com.govmedcare.utils.DBConnection;
import com.govmedcare.utils.QueryUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
            ps.setString(3, "Completed");
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Unable to create order",e);
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
            logger.log(Level.SEVERE, "Unable to save order item",e);
        }
        return false;
    }

    @Override
    public List<Order> getPurchaseHistory(Long patient_id) {
        final List<Order> list=new ArrayList<>();
        try(Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(QueryUtil.getPurchaseHistory)
        ){
            ps.setLong(1,patient_id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Order order=new Order();
                order.setOrderId(rs.getLong("order_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                OrderStatus.valueOf(rs.getString("status"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(order);

            }
        } catch (SQLException e) {
           logger.log(Level.SEVERE,"Unable to get purchase history",e);
        }
        return list;

    }

    @Override
    public List<OrderItem> getSoldHistory() {
        List<OrderItem> list=new ArrayList<>();
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement(QueryUtil.getSoldHistory)
        ){
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                OrderItem orderItem=new OrderItem();
                orderItem.setOrderId(rs.getLong("order_id"));
                orderItem.setMedicineId(rs.getLong("medicine_id"));
                orderItem.setMedicineName(rs.getString("medicine_name"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItem.setPatientName(rs.getString("patient_name"));
                orderItem.setPrice(rs.getDouble("price"));
                list.add(orderItem);
            }
        }
        catch (SQLException e){
            logger.log(Level.SEVERE,"Unable to get sales history",e);
        }
        return list;
    }

}

