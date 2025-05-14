package com.dropbetzadmin.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dropbeatzadmin.model.AdminOrderModel;
import com.dropbetzadmin.util.DBConnection;

public class AdminOrderController {
	
	 private static Connection con = null;
	    private static Statement stmt = null;
	    private static ResultSet rs = null;
	    
	    // GetByID
	    public static List<AdminOrderModel> getById(String order_id) {
	        ArrayList<AdminOrderModel> a_order = new ArrayList<>();
	        
	        try {
	            // DB connection
	            con = DBConnection.getConnection();
	            stmt = con.createStatement();
	            
	            // Query - using backticks to escape reserved keyword "order"
	            String sql = "SELECT * FROM `order` WHERE order_id = '" + order_id + "'";
	            
	            rs = stmt.executeQuery(sql);
	            
	            while (rs.next()) {
	                String Order_id = rs.getString(1);
	                String Product_id = rs.getString(2);
	                String User_id = rs.getString(3);
	                
	                AdminOrderModel ap = new AdminOrderModel(Order_id, Product_id, User_id);
	                a_order.add(ap);
	            }
	        } catch(Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close database resources
	            closeResources();
	        }
	        
	        return a_order;
	    }
	    
	    // GetAll DATA
	    public static List<AdminOrderModel> getAllorder() {
	        ArrayList<AdminOrderModel> a_order = new ArrayList<>();
	        
	        try {
	            // DB connection
	            con = DBConnection.getConnection();
	            stmt = con.createStatement();
	            
	            // Query - using backticks to escape reserved keyword "order"
	            String sql = "SELECT order_id, Product_id, User_id FROM `order`";
	            
	            rs = stmt.executeQuery(sql);
	            
	            while (rs.next()) {
	                String order_id = rs.getString(1);
	                String Product_id = rs.getString(2);
	                String User_id = rs.getString(3);
	                
	                AdminOrderModel ap = new AdminOrderModel(order_id, Product_id, User_id);
	                a_order.add(ap);
	            }
	        } catch(Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close database resources
	            closeResources();
	        }
	        
	        return a_order;
	    }
	    
	    // Helper method to close database resources
	    private static void closeResources() {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (con != null) con.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


