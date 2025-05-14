package com.dropbetzadmin.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dropbeatzadmin.model.AdminModel;
import com.dropbetzadmin.util.DBConnection;
import com.dropbetzadmin.control.AdminService;

@SuppressWarnings("unused")
public class AdminController implements AdminService{

	 private static boolean isSuccess;
	    private static Connection con = null;
	    private static Statement stmt = null;
	    private static ResultSet rs = null;
//abstraction
	    @Override
	    public boolean insertData(String fullname, String username, String country, String contactnumber, String email, String password, String role) {
	        boolean isSuccess = false;
	        
	        try {
	            con = DBConnection.getConnection();
	            if (con == null) {
	                System.out.println("Database connection failed");
	                return false;
	            }
	            
	            // Use consistent table name - change "admins" to "user" to match other methods
	            String sql = "INSERT INTO user (fullname, username, country, contactnumber, email, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement stmt = con.prepareStatement(sql);
	            
	            stmt.setString(1, fullname);
	            stmt.setString(2, username);
	            stmt.setString(3, country);
	            stmt.setString(4, contactnumber);
	            stmt.setString(5, email);
	            stmt.setString(6, password);
	            stmt.setString(7, role);
	            
	            int result = stmt.executeUpdate();
	            if (result > 0) {
	                isSuccess = true;
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL Error: " + e.getMessage());
	            e.printStackTrace();
	        } catch (Exception e) {
	            System.out.println("Error in insertData: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        
	        return isSuccess;
	    }
	
	  //abstraction
	    @Override
	    public List<AdminModel> getById(String id) {
	        ArrayList<AdminModel> admin = new ArrayList<>();
	        try {
	            con = DBConnection.getConnection();
	            stmt = con.createStatement();
	            String sql = "select * from admin where id = '" + id + "'";
	            rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	                AdminModel ap = new AdminModel(
	                    rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
	                    rs.getString(5), rs.getString(7), rs.getString(6), rs.getString(8)
	                );
	                admin.add(ap);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return admin;
	    }
	    @Override
	    public List<AdminModel> getAllAdmin() {
	        ArrayList<AdminModel> admin = new ArrayList<>();
	        try {
	            con = DBConnection.getConnection();
	            stmt = con.createStatement();
	            String sql = "select * from user where role = 'admin'";
	            rs = stmt.executeQuery(sql);
	            while (rs.next()) {
	                AdminModel ap = new AdminModel(
	                    rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
	                    rs.getString(5), rs.getString(7), rs.getString(6), rs.getString(8)
	                );
	                admin.add(ap);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return admin;
	    }
	    @Override
	    public boolean updateData(String id, String fullname, String username, String country, String contactnumber, String password, String email, String role) {
	        try {
	            con = DBConnection.getConnection();
	            stmt = con.createStatement();
	            String sql = "update user set fullname= '" + fullname + "',username ='" + username + "',country = '" + country +
	                         "',contactnumber ='" + contactnumber + "',email ='" + email + "',password = '" + password +
	                         "' where id = '" + id + "' ";
	            int rs = stmt.executeUpdate(sql);
	            isSuccess = rs > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return isSuccess;
	    }
	    @Override
	    public boolean deleteData(String id) {
	        int convID = Integer.parseInt(id);
	        try {
	            con = DBConnection.getConnection();
	            stmt = con.createStatement();
	            String sql = "delete from user where id = '" + convID + "'";
	            int rs = stmt.executeUpdate(sql);
	            isSuccess = rs > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return isSuccess;
	    }
	    @Override
	    public boolean isUsernameTaken(String username) {
	        boolean exists = false;
	        try {
	            con = DBConnection.getConnection();
	            stmt = con.createStatement();
	            String sql = "SELECT * FROM user WHERE username = '" + username + "'";
	            rs = stmt.executeQuery(sql);
	            exists = rs.next();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return exists;
	    }
	}


