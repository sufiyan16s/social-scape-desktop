package com.sufiyan.socialscape.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/socialscape";
    private static final String USER = "root";
    private static final String PASSWORD = "Sufi$&8416@";

    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("DB Connection failed: " + e.getMessage());
            return null;
        }
    }
}
