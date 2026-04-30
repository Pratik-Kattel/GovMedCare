package com.govmedcare.dao;

import com.govmedcare.model.Medicine;
import com.govmedcare.repository.AdminMedicineRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AdminMedicineDao implements AdminMedicineRepository {

    private Connection conn;

    public AdminMedicineDao(Connection conn) {
        this.conn = conn;
    }

    //  Get all unverified medicines
    @Override
    public List<Medicine> getPendingMedicines() {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT * FROM medicine WHERE is_verified = false";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medicine med = new Medicine();
                med.setMedicineID(rs.getInt("id"));
                med.setName(rs.getString("name"));
                med.setIs_verified(rs.getBoolean("is_verified"));

                list.add(med);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Approve medicine (set is_verified = true)
    @Override
    public boolean approveMedicine(int id) {
        String sql = "UPDATE medicine SET is_verified = true WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //  Delete / Reject medicine
    @Override
    public boolean deleteMedicine(int id) {
        String sql = "DELETE FROM medicine WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}