package com.govmedcare.dao;
import com.govmedcare.model.Medicine;
import com.govmedcare.repository.MedicineRepository;
import com.govmedcare.utils.DBConnection;
import com.govmedcare.utils.QueryUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicineDao implements MedicineRepository {
    private static final Logger logger = Logger.getLogger(MedicineDao.class.getName());

    @Override
    public boolean SaveMedicine(Medicine medicine) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.saveMedicine)
        ) {
            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getDescription());
            ps.setDouble(3, medicine.getPrice());
            ps.setInt(4, medicine.getQuantity());
            ps.setLong(5, medicine.getCategory_id());
            ps.setString(6, medicine.getImageURL());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Medicine not saved", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean existByName(String name) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.checkByName);
        ) {
            ps.setString(1, name);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Query not executed", e.getMessage());
        }
        return false;
    }
}
