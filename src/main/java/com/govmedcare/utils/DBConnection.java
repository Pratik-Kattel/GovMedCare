package com.govmedcare.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final Logger logger=Logger.getLogger(DBConnection.class.getName());
    private static final String URL="jdbc:mysql://localhost:3306/govmedcare";
    private static final String userName="root";
    private static final String password="1234";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("Driver loaded successfully");
        }
        catch (ClassNotFoundException e){
            logger.log(Level.SEVERE,e.getMessage());
        }
    }
    public static Connection getConnection() throws SQLException {
       return DriverManager.getConnection(URL, userName, password);

    }
}
