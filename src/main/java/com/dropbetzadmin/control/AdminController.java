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

public class AdminController implements AdminService {

    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    @Override
    public boolean insertData(String fullname, String username, String country, String contactnumber, String email, String password, String role) {
        boolean isSuccess = false;
        PreparedStatement stmt = null;
        Connection con = null;
        
        try {
            // Get the connection
            con = DBConnection.getConnection();
            if (con == null) {
                System.out.println("Database connection failed");
                return false;
            }
            
            // Print debug information
            System.out.println("Inserting admin with username: " + username);
            
            // Begin transaction
            con.setAutoCommit(false);
            
            // Prepare the SQL statement with proper parameterization
            String sql = "INSERT INTO user (fullname, username, country, contactnumber, email, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql);
            
            // Set parameters
            stmt.setString(1, fullname);
            stmt.setString(2, username);
            stmt.setString(3, country);
            stmt.setString(4, contactnumber);
            stmt.setString(5, email);
            stmt.setString(6, password);
            stmt.setString(7, role);
            
            // Execute the statement
            int result = stmt.executeUpdate();
            
            // Check if insertion was successful
            if (result > 0) {
                System.out.println("Admin insertion successful: " + result + " row(s) affected");
                isSuccess = true;
                con.commit();
            } else {
                System.out.println("Admin insertion failed: No rows affected");
                con.rollback();
            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
            System.out.println("SQL Error in insertData: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other exceptions
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
            System.out.println("Error in insertData: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        return isSuccess;
    }

    @Override
    public List<AdminModel> getById(String id) {
        ArrayList<AdminModel> admin = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = DBConnection.getConnection();
            if (con == null) {
                System.out.println("Database connection failed in getById");
                return admin;
            }
            
            // Use PreparedStatement to prevent SQL injection
            String sql = "SELECT * FROM user WHERE id = ? AND role = 'admin'";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                AdminModel ap = new AdminModel(
                    rs.getString(1), // id
                    rs.getString(2), // fullname
                    rs.getString(3), // username
                    rs.getString(4), // country
                    rs.getString(5), // contactnumber
                    rs.getString(7), // email
                    rs.getString(6), // password
                    rs.getString(8)  // role
                );
                admin.add(ap);
                System.out.println("Found admin with ID: " + id + ", Name: " + rs.getString(2));
            }
            
            if (admin.isEmpty()) {
                System.out.println("No admin found with ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Error in getById: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(con, stmt, rs);
        }
        
        return admin;
    }

    @Override
    public List<AdminModel> getAllAdmin() {
        ArrayList<AdminModel> admin = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = DBConnection.getConnection();
            if (con == null) {
                System.out.println("Database connection failed in getAllAdmin");
                return admin;
            }
            
            // Use PreparedStatement for consistency
            String sql = "SELECT * FROM user WHERE role = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, "admin");
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                AdminModel ap = new AdminModel(
                    rs.getString(1), // id
                    rs.getString(2), // fullname
                    rs.getString(3), // username
                    rs.getString(4), // country
                    rs.getString(5), // contactnumber
                    rs.getString(7), // email
                    rs.getString(6), // password
                    rs.getString(8)  // role
                );
                admin.add(ap);
            }
            
            System.out.println("Retrieved " + admin.size() + " admin records");
        } catch (Exception e) {
            System.out.println("Error in getAllAdmin: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(con, stmt, rs);
        }
        
        return admin;
    }

    @Override
    public boolean updateData(String id, String fullname, String username, String country, String contactnumber, String password, String email, String role) {
        boolean isSuccess = false;
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = DBConnection.getConnection();
            if (con == null) {
                System.out.println("Database connection failed in updateData");
                return false;
            }
            
            System.out.println("Updating admin with ID: " + id);
            
            // Begin transaction
            con.setAutoCommit(false);
            
            // Use PreparedStatement to prevent SQL injection
            String sql = "UPDATE user SET fullname = ?, username = ?, country = ?, contactnumber = ?, email = ?, password = ? WHERE id = ?";
            stmt = con.prepareStatement(sql);
            
            stmt.setString(1, fullname);
            stmt.setString(2, username);
            stmt.setString(3, country);
            stmt.setString(4, contactnumber);
            stmt.setString(5, email);
            stmt.setString(6, password);
            stmt.setString(7, id);
            
            int result = stmt.executeUpdate();
            
            if (result > 0) {
                System.out.println("Admin update successful: " + result + " row(s) affected for ID: " + id);
                isSuccess = true;
                con.commit();
            } else {
                System.out.println("Admin update failed: No rows affected for ID: " + id);
                con.rollback();
            }
        } catch (SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
            System.out.println("SQL Error in updateData: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
            System.out.println("Error in updateData: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        return isSuccess;
    }

    @Override
    public boolean deleteData(String id) {
        boolean isSuccess = false;
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = DBConnection.getConnection();
            if (con == null) {
                System.out.println("Database connection failed in deleteData");
                return false;
            }
            
            System.out.println("Deleting admin with ID: " + id);
            
            // Begin transaction
            con.setAutoCommit(false);
            
            // Use PreparedStatement to prevent SQL injection
            String sql = "DELETE FROM user WHERE id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            
            int result = stmt.executeUpdate();
            
            if (result > 0) {
                System.out.println("Admin deletion successful: " + result + " row(s) affected for ID: " + id);
                isSuccess = true;
                con.commit();
            } else {
                System.out.println("Admin deletion failed: No rows affected for ID: " + id);
                con.rollback();
            }
        } catch (SQLException e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
            System.out.println("SQL Error in deleteData: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }
            System.out.println("Error in deleteData: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        return isSuccess;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        boolean exists = false;
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = DBConnection.getConnection();
            if (con == null) {
                System.out.println("Database connection failed in isUsernameTaken");
                return false;
            }
            
            // Use PreparedStatement to prevent SQL injection
            String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            
            rs = stmt.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
            
            System.out.println("Username '" + username + "' taken: " + exists);
        } catch (Exception e) {
            System.out.println("Error in isUsernameTaken: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources(con, stmt, rs);
        }
        
        return exists;
    }
    
    // Helper method to close resources
    private void closeResources(Connection con, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
            e.printStackTrace();
        }
    }
}