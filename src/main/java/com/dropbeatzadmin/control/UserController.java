package com.dropbeatzadmin.control;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.dropbeatzadmin.util.DBConnection;
import com.dropbeatzadmin.model.UserModel;

public class UserController {

    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    
    // Method to get all users (admin only)
    public static List<UserModel> getAllUsers() {
        ArrayList<UserModel> users = new ArrayList<>();
        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM user";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String username = rs.getString(3);
                String country = rs.getString(4);
                String contactnumber = rs.getString(5);
                String email = rs.getString(6);
                String password = rs.getString(7);
                String role = rs.getString(8);
                
                UserModel user = new UserModel(id, fullname, username, country, contactnumber, password, email, role);
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println("Error in getAllUsers: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return users;
    }
    
    // Login validate - Modified to work with or without password for profile retrieval
    public static List<UserModel> loginValidate(String username, String password) {
        ArrayList<UserModel> user = new ArrayList<>();
        try {
            // DB connection
            con = DBConnection.getConnection();
            
            String sql;
            if (password != null) {
                // For login validation with PreparedStatement (more secure)
                sql = "SELECT * FROM user WHERE username = ? AND password = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();
            } else {
                // For profile retrieval only (without password check)
                sql = "SELECT * FROM user WHERE username = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, username);
                rs = pstmt.executeQuery();
            }
            
            if (rs.next()) {
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String Username = rs.getString(3);
                String country = rs.getString(4);
                String contactnumber = rs.getString(5);
                String email = rs.getString(6);
                String Password = rs.getString(7);
                String role = rs.getString(8);

                UserModel u = new UserModel(id, fullname, Username, country, contactnumber, Password, email, role);
                user.add(u);
            }
        } catch (Exception e) {
            System.out.println("Error in loginValidate: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return user;
    }

    // Update user profile
    public static boolean updateUserProfile(String username, String fullname, String country,
            String contactnumber, String email) {
        boolean success = false;
        try {
            // DB connection
            con = DBConnection.getConnection();
            // Use PreparedStatement to prevent SQL injection
            String sql = "UPDATE user SET fullname = ?, country = ?, contactnumber = ?, email = ? WHERE username = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, fullname);
            pstmt.setString(2, country);
            pstmt.setString(3, contactnumber);
            pstmt.setString(4, email);
            pstmt.setString(5, username);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (Exception e) {
            System.out.println("Error in updateUserProfile: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return success;
    }
    
    // Helper method to close all resources
    private static void closeResources() {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}