package com.govmedcare.dao;
import com.govmedcare.model.User;
import com.govmedcare.repository.UserRepository;
import com.govmedcare.utils.DBConnection;
import com.govmedcare.utils.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements UserRepository {
    private static final Logger logger=Logger.getLogger(UserDao.class.getName());

    @Override
    public boolean saveUser(User user) {
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement(QueryUtil.saveUser);
        ){
            ps.setString(1,user.getName());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getRole().name());
            ps.setString(5,user.getPhone());
            ps.setString(6,user.getAddress());

            int rowsAffected=ps.executeUpdate();
            if(rowsAffected>0){
                return true;
            }
            return false;

        } catch (SQLException e) {
            logger.log(Level.SEVERE,"User not saved",e);
        }
        return false;
    }

    @Override
    public boolean checkByEmail(String email) {
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement(QueryUtil.checkByEmail);
        ){
            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            return  rs.next();
        }
        catch (SQLException e){
            logger.log(Level.SEVERE,"Query not executed",e);
        }
        return false;
    }
}
