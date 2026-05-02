package com.govmedcare.dao;
import com.govmedcare.model.Medicine;
import com.govmedcare.repository.AdminMedicineRepository;
import com.govmedcare.utils.DBConnection;
import com.govmedcare.utils.QueryUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminMedicineDao implements AdminMedicineRepository {
    Logger logger=Logger.getLogger(AdminMedicineDao.class.getName());

    @Override
    public List<Medicine> getPendingMedicines() {
        List<Medicine> list=new ArrayList<>();
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(QueryUtil.getPendingMedicines)
        ){
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Medicine medicine=new Medicine();
                medicine.setMedicineID(rs.getLong("medicine_id"));
                medicine.setName(rs.getString("name"));
                medicine.setDescription(rs.getString("description"));
                medicine.setPrice(rs.getDouble("price"));
                medicine.setCreated_at(rs.getTimestamp("created_at"));
                medicine.setImageURL(rs.getString("image_url"));
                medicine.setQuantity(rs.getInt("quantity"));
                list.add(medicine);
            }
        }
         catch (SQLException e) {
            logger.log(Level.SEVERE,"Failed to get pending medicines",e.getMessage());
        }
        return list;
    }

    @Override
    public boolean approveMedicine(int id) {
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement(QueryUtil.validateMedicines)
        ){
            ps.setInt(1,id);
            int rows =ps.executeUpdate();
            if(rows>0) return true;
        }
        catch (SQLException e){
            logger.log(Level.SEVERE,"Failed to approve medicine",e.getMessage());
        }
        return false;

    }

    @Override
    public boolean deleteMedicine(int id) {
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement(QueryUtil.deleteMedicine)
        ){
            ps.setInt(1,id);
            int rows=ps.executeUpdate();
            if(rows>0) return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Failed to delete medicine",e.getMessage());
        }
        return false;
    }
}