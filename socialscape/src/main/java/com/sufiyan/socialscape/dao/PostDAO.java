package com.sufiyan.socialscape.dao;

import com.sufiyan.socialscape.model.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {

    public static boolean insert(Post post) {
        String sql = "INSERT INTO posts (user_id, title, platform,caption, date, time, media_path, media_type, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, post.getUserId());
            stmt.setString(2, post.getTitle());
            stmt.setString(3, post.getPlatform());
            stmt.setString(4, post.getCaption());
            stmt.setString(5, post.getDate());
            stmt.setString(6, post.getTime());
            stmt.setString(7, post.getMediaPath());
            stmt.setString(8, post.getMediaType());
            stmt.setString(9, post.getStatus());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
            return false;
        }
    }

    public static List<Post> getPostsByUser(int userId) {
        List<Post> list = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE user_id = ?";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setUserId(rs.getInt("user_id"));
                post.setTitle(rs.getString("title"));
                post.setPlatform(rs.getString("platform"));
                post.setCaption(rs.getString("caption"));
                post.setDate(rs.getString("date"));
                post.setTime(rs.getString("time"));
                post.setMediaPath(rs.getString("media_path"));
                post.setMediaType(rs.getString("media_type"));
                post.setStatus(rs.getString("status"));
                list.add(post);
            }
        } catch (SQLException e) {
            System.out.println("Fetch error: " + e.getMessage());
        }
        return list;
    }

    public static boolean update(Post post) {
        String sql = "UPDATE posts SET title=?, platform=?, caption=?, date=?, time=?, media_path=?, media_type=?, status=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getPlatform());
            stmt.setString(3, post.getCaption());
            stmt.setString(4, post.getDate());
            stmt.setString(5, post.getTime());
            stmt.setString(6, post.getMediaPath());
            stmt.setString(7, post.getMediaType());
            stmt.setString(8, post.getStatus());
            stmt.setInt(9, post.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
            return false;
        }
    }

    public static boolean delete(int id) {
        String sql = "DELETE FROM posts WHERE id=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Delete Error: " + e.getMessage());
            return false;
        }
    }
}
