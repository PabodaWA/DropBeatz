package product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get product id from request
        String productIdStr = request.getParameter("id");
        
        // Validate product id
        if (productIdStr == null || productIdStr.trim().isEmpty()) {
            request.getSession().setAttribute("errorMessage", "Invalid product ID.");
            response.sendRedirect("artist.jsp");
            return;
        }
        
        int productId;
        try {
            productId = Integer.parseInt(productIdStr);
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Invalid product ID format.");
            response.sendRedirect("artist.jsp");
            return;
        }
        
        Connection conn = null;
        PreparedStatement selectStmt = null;
        PreparedStatement deleteStmt = null;
        ResultSet rs = null;
        
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Get connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // First, get the file path to delete the file
            String selectQuery = "SELECT link FROM product WHERE product_id = ?";
            selectStmt = conn.prepareStatement(selectQuery);
            selectStmt.setInt(1, productId);
            rs = selectStmt.executeQuery();
            
            String filePath = null;
            if (rs.next()) {
                filePath = rs.getString("link");
            } else {
                request.getSession().setAttribute("errorMessage", "Product not found.");
                response.sendRedirect("artist.jsp");
                return;
            }
            
            // Delete record from database
            String deleteQuery = "DELETE FROM product WHERE product_id = ?";
            deleteStmt = conn.prepareStatement(deleteQuery);
            deleteStmt.setInt(1, productId);
            int rowsAffected = deleteStmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Delete the file if it exists
                if (filePath != null && !filePath.isEmpty()) {
                    String fullPath = getServletContext().getRealPath("") + filePath;
                    try {
                        Files.deleteIfExists(Paths.get(fullPath));
                    } catch (IOException e) {
                        // Log the error but continue with the process
                        System.err.println("Failed to delete file: " + fullPath + " - " + e.getMessage());
                    }
                }
                
                request.getSession().setAttribute("successMessage", "Product deleted successfully!");
            } else {
                request.getSession().setAttribute("errorMessage", "Failed to delete product.");
            }
            
        } catch (ClassNotFoundException e) {
            request.getSession().setAttribute("errorMessage", "MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            request.getSession().setAttribute("errorMessage", "Database error: " + e.getMessage());
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "An error occurred: " + e.getMessage());
        } finally {
            // Close resources
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (selectStmt != null) selectStmt.close(); } catch (Exception e) { }
            try { if (deleteStmt != null) deleteStmt.close(); } catch (Exception e) { }
            try { if (conn != null) conn.close(); } catch (Exception e) { }
        }
        
        // Redirect back to albums page
        response.sendRedirect("artist.jsp");
    }

}
