package com.dropbeatzadmin.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dropbetzadmin.util.*;
import com.dropbeatzadmin.model.NewUserModel;

public class NewUserController {

    // connect DB
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;
    
    
     // Insert new user data into the database
    
    
    public static String insertdata(String fullname, String username, String country, String contactnumber, String email, String password, String role) {
        try {
            // Check if username or email already exists
            if (isUserExist(username, email)) {
                return "Username or email already exists!";
            }
            
            con = DBConnection.getConnection();
            // Make sure we're using the correct table name consistently
            String sql = "INSERT INTO user (fullname, username, country, contactnumber, email, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, fullname);
            pstmt.setString(2, username);
            pstmt.setString(3, country);
            pstmt.setString(4, contactnumber);
            pstmt.setString(5, email);
            pstmt.setString(6, password); // In production, this should be encrypted
            pstmt.setString(7, role);
            
            int result = pstmt.executeUpdate();
            if (result > 0) {
                return "success";
            } else {
                return "Failed to insert record";
            }
        } catch (SQLException e) {
            return "Database error: " + e.getMessage();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        } finally {
            closeConnection();
        }
    }

    /**
     * Check if username or email already exists in the database
     */
    private static boolean isUserExist(String username, String email) {
        boolean exists = false;
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT * FROM user WHERE username = ? OR email = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return exists;
    }

    /**
     * Login validation
     */
    public static List<NewUserModel> loginValidate(String username, String password) {
        ArrayList<NewUserModel> user = new ArrayList<>();
        
        try {
            // Use prepared statements to prevent SQL injection
            con = DBConnection.getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String uname = rs.getString(3);
                String country = rs.getString(4);
                String contactnumber = rs.getString(5);
                String email = rs.getString(6);
                String pass = rs.getString(7);
                String role = rs.getString(8);
                
                NewUserModel u = new NewUserModel(id, fullname, uname, country, contactnumber, pass, email, role);
                user.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return user;
    }
    
    /**
     * Get user profile information
     */
    public static List<NewUserModel> userProfile(String Id) {
        int convertedID = Integer.parseInt(Id);
        
        ArrayList<NewUserModel> user = new ArrayList<>();
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT * FROM user WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, convertedID);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String username = rs.getString(3);
                String country = rs.getString(4);
                String contactnumber = rs.getString(5);
                String email = rs.getString(6);
                String password = rs.getString(7);
                String role = rs.getString(8);
            
                NewUserModel u = new NewUserModel(id, fullname, username, country, contactnumber, email, password, role);
                user.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return user;
    }
    
    /**
     * Update user profile
     */
    public static boolean updateProfile(String id, String fullname, String username, String country, 
                                     String contactnumber, String email, String password, String role) {
        boolean isSuccess = false;
        try {
            con = DBConnection.getConnection();
            
            // Use prepared statements to prevent SQL injection
            String sql = "UPDATE user SET fullname = ?, username = ?, country = ?, contactnumber = ?, email = ?, password = ? WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, fullname);
            pstmt.setString(2, username);
            pstmt.setString(3, country);
            pstmt.setString(4, contactnumber);
            pstmt.setString(5, email);
            pstmt.setString(6, password);
            pstmt.setString(7, id);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return isSuccess;
    }
    
    /**
     * Get user by ID
     */
    public static List<NewUserModel> getById(String Id) {
        int convertedID = Integer.parseInt(Id);
        
        ArrayList<NewUserModel> user = new ArrayList<>();
        
        try {
            con = DBConnection.getConnection();
            String sql = "SELECT * FROM user WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, convertedID);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String username = rs.getString(3);
                String country = rs.getString(4);
                String contactnumber = rs.getString(5);
                String email = rs.getString(6);
                String password = rs.getString(7);
                String role = rs.getString(8);
            
                NewUserModel u = new NewUserModel(id, fullname, username, country, contactnumber, email, password, role);
                user.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return user;
    }
    
    /**
     * Delete user account
     */
    public static boolean deleteAccount(String id) {
        boolean isSuccess = false;
        
        try {
            int convID = Integer.parseInt(id);
            con = DBConnection.getConnection();
            
            // Use prepared statements to prevent SQL injection
            String sql = "DELETE FROM user WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, convID);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        
        return isSuccess;
    }
    
    /**
     * Close database connections
     */
    private static void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}