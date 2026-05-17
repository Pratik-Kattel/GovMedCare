package com.govmedcare.dao;

import com.govmedcare.repository.ReportRepository;
import com.govmedcare.utils.DBConnection;
import com.govmedcare.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReportDao implements ReportRepository {
    Logger logger = Logger.getLogger(ReportRepository.class.getName());

    @Override
    public double generateReport() {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.calculateTotalRevenue)
        ) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_revenue");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to fetch the total revenue",e);

        }
        return 0;
    }
}
