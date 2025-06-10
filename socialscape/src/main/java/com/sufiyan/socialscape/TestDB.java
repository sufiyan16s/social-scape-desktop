package com.sufiyan.socialscape;

import com.sufiyan.socialscape.dao.DBConnection;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if(conn != null) {
            System.out.println("Connected to MySQL successfully.");
        } else {
            System.out.println("Connection failed");
        }
    }
}
