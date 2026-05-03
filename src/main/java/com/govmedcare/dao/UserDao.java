package com.govmedcare.dao;

import com.govmedcare.model.User;
import com.govmedcare.repository.UserRepository;
import com.govmedcare.types.UserRole;
import com.govmedcare.types.UserStatus;
import com.govmedcare.utils.DBConnection;
import com.govmedcare.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements UserRepository {
    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    @Override
    public boolean saveUser(User user) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.saveUser)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole().name());
            ps.setString(5, user.getPhone());
            ps.setString(6, user.getAddress());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "User not saved", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean checkByEmail(String email) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.checkByEmail)
        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Query not executed", e.getMessage());
        }
        return false;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.checkByEmail)
        ) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getLong("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        UserRole.valueOf(rs.getString("role")),
                        rs.getString("phone"),
                        rs.getString("address"),
                        UserStatus.valueOf(rs.getString("status")),
                        rs.getTimestamp("created_at")
                );
                return Optional.of(user);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Query not executed", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.getUsers)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("Address"));
                user.setPhone(rs.getString("phone"));
                list.add(user);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to get users", e.getMessage());
        }
        return list;
    }

    @Override
    public boolean blockUser(Long id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(QueryUtil.blockUser)
        ) {
            int rows = ps.executeUpdate();
            if (rows > 0) return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to block User", e.getMessage());

        }
        return false;
    }
}

