package com.sufiyan.socialscape.dao;

import com.sufiyan.socialscape.model.User;
import com.sufiyan.socialscape.utils.HashUtil;

import java.sql.*;

public class UserDAO {

    public static boolean register(User user) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, HashUtil.sha256(user.getPassword()));
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Register failed: " + e.getMessage());
            return false;
        }
    }

    public static User login(String email, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, HashUtil.sha256(password));
            System.out.println("Trying login with hash: " + HashUtil.sha256(password));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
        return null;
    }
}
