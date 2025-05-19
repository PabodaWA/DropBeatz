package product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Servlet implementation class EditProduct
 */
@WebServlet("/editproduct")
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	  // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProduct() {
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
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Get connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Retrieve product details
            String query = "SELECT product_id, product_name, price, description, link FROM product WHERE product_id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, productId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Set product details as request attributes
                request.setAttribute("productId", rs.getInt("product_id"));
                request.setAttribute("productName", rs.getString("product_name"));
                request.setAttribute("price", rs.getString("price"));
                request.setAttribute("description", rs.getString("description"));
                request.setAttribute("link", rs.getString("link"));
                
                // Forward to edit page
                request.getRequestDispatcher("editproduct.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("errorMessage", "Product not found.");
                response.sendRedirect("artist.jsp");
            }
            
        } catch (ClassNotFoundException e) {
            request.getSession().setAttribute("errorMessage", "MySQL JDBC Driver not found: " + e.getMessage());
            response.sendRedirect("artist.jsp");
        } catch (SQLException e) {
            request.getSession().setAttribute("errorMessage", "Database error: " + e.getMessage());
            response.sendRedirect("artist.jsp");
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            response.sendRedirect("artist.jsp");
        } finally {
            // Close resources
            try { if (rs != null) rs.close(); } catch (Exception e) { }
            try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
            try { if (conn != null) conn.close(); } catch (Exception e) { }
        }
    }

}
