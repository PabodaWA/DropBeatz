package com.dropbeatzadmin.control;
import com.dropbeatzadmin.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dropbeatzadmin.model.AdminArtistModel;
import com.dropbeatzadmin.util.DBConnection;

public class AdminArtistController {
	
	private static boolean isSuccess;  
	private static Connection con = null;
	    private static Statement stmt = null;
	    private static ResultSet rs = null;
	    
	  //insert data
	    // Insert data
	    public static boolean insertdata(String fullname, String username, String country, String contactnumber, String password, String email, String role) {
	        boolean isSuccess = false;
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        
	        try {
	            // Get database connection
	            con = DBConnection.getConnection();
	            
	            // Check if connection was established
	            if (con == null) {
	                System.out.println("Database connection failed");
	                return false;
	            }
	            
	            // Create SQL query with parameterized statement to prevent SQL injection
	            String sql = "INSERT INTO user (fullname, username, country, contactnumber, email, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, fullname);
	            pstmt.setString(2, username);
	            pstmt.setString(3, country);
	            pstmt.setString(4, contactnumber);
	            pstmt.setString(5, email);
	            pstmt.setString(6, password);
	            pstmt.setString(7, role);
	            
	            // Execute the insert
	            int result = pstmt.executeUpdate();
	            
	            // Check if insert was successful
	            if (result > 0) {
	                isSuccess = true;
	                System.out.println("Insert successful: " + result + " row(s) affected");
	            } else {
	                System.out.println("Insert failed: No rows affected");
	            }
	            
	        } catch (Exception e) {
	            System.out.println("Error in insertdata method: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            // Close resources in finally block
	            try {
	                if (pstmt != null) pstmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                System.out.println("Error closing resources: " + e.getMessage());
	                e.printStackTrace();
	            }
	        }
	        
	        return isSuccess;
	    }
	    
	    // Other methods...
	
	  //GetByID
	    public static List<AdminArtistModel> getById(String id) {
	        ArrayList<AdminArtistModel> admin = new ArrayList<>();
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        try {
	            // DB connection
	            con = DBConnection.getConnection();
	            
	            // Query with PreparedStatement
	            String sql = "SELECT * FROM user WHERE id = ?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, id);
	            
	            rs = pstmt.executeQuery();
	            
	            while (rs.next()) {
	                String Id = rs.getString(1);
	                String fullname = rs.getString(2);
	                String Username = rs.getString(3);
	                String country = rs.getString(4);
	                String contactnumber = rs.getString(5);
	                String email = rs.getString(6);
	                String Password = rs.getString(7);
	                String role = rs.getString(8);
	                
	                AdminArtistModel ap = new AdminArtistModel(Id, fullname, Username, country, contactnumber, Password, email, role);
	                admin.add(ap);
	            }
	        } catch(Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pstmt != null) pstmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        
	        return admin;
	    }
	    
	    //getall

	    public static List<AdminArtistModel> getAllArtist() {
	        ArrayList<AdminArtistModel> admin = new ArrayList<>();
	        Connection con = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        
	        try {
	            // DB connection
	            con = DBConnection.getConnection();
	            
	            // Query with PreparedStatement
	            String sql = "SELECT * FROM user WHERE role = ?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, "artist");
	            
	            rs = pstmt.executeQuery();
	            
	            while (rs.next()) {
	                String id = rs.getString(1);
	                String fullname = rs.getString(2);
	                String Username = rs.getString(3);
	                String country = rs.getString(4);
	                String contactnumber = rs.getString(5);
	                String email = rs.getString(6);
	                String Password = rs.getString(7);
	                String role = rs.getString(8);
	                
	                AdminArtistModel ap = new AdminArtistModel(id, fullname, Username, country, contactnumber, Password, email, role);
	                admin.add(ap);
	            }
	        } catch(Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pstmt != null) pstmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        
	        return admin;
	    }
	  //update data

	  public static boolean updatedata(String id, String fullname, String username, String country, String contactnumber, String password, String email, String role) {
		    boolean isSuccess = false;
		    Connection con = null;
		    PreparedStatement pstmt = null;
		    
		    try {
		        // DB connection
		        con = DBConnection.getConnection();
		        
		        // SQL Query with PreparedStatement
		        String sql = "UPDATE user SET fullname=?, username=?, country=?, contactnumber=?, password=?, email=?, role=? WHERE id=?";
		        
		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, fullname);
		        pstmt.setString(2, username);
		        pstmt.setString(3, country);
		        pstmt.setString(4, contactnumber);
		        pstmt.setString(5, password);
		        pstmt.setString(6, email);
		        pstmt.setString(7, role);
		        pstmt.setString(8, id);
		        
		        int rs = pstmt.executeUpdate();
		        
		        if (rs > 0) {
		            isSuccess = true;
		        } else {
		            isSuccess = false;
		        }
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {
		        // Close resources
		        try {
		            if (pstmt != null) pstmt.close();
		            if (con != null) con.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    
		    return isSuccess;
		}

	  

	  public static boolean isUsernameTaken(String username) {
		    boolean exists = false;
		    Connection con = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;

		    try {
		        con = DBConnection.getConnection();
		        String sql = "SELECT * FROM user WHERE username = ?";
		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, username);
		        rs = pstmt.executeQuery();
		        
		        if (rs.next()) {
		            exists = true;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) rs.close();
		            if (pstmt != null) pstmt.close();
		            if (con != null) con.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }

		    return exists;
		}
	  
	//delete data

		public static boolean deletedata(String id) {
		    boolean isSuccess = false;
		    Connection con = null;
		    PreparedStatement pstmt = null;
		    
		    try {
		        // DBConnection
		        con = DBConnection.getConnection();
		        
		        String sql = "DELETE FROM user WHERE id = ?";
		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, id);
		        
		        int rs = pstmt.executeUpdate();
		        
		        if (rs > 0) {
		            isSuccess = true;
		        } else {
		            isSuccess = false;
		        }
		    } catch(Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (pstmt != null) pstmt.close();
		            if (con != null) con.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    
		    return isSuccess;
		}
		
		
}

