package product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
/**
 * Servlet implementation class UpdateProduct
 */

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
	    maxFileSize = 1024 * 1024 * 50,     // 50 MB
	    maxRequestSize = 1024 * 1024 * 60   // 60 MB
	)
@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Extract form fields
            String productIdStr = request.getParameter("productId");
            String productName = request.getParameter("productName");
            String priceStr = request.getParameter("price");
            String description = request.getParameter("description");
            String currentLink = request.getParameter("currentLink");
            Part audioFilePart = request.getPart("audioFile");
            
            // Validate product ID
            if (productIdStr == null || productIdStr.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Product ID is missing.");
                request.getRequestDispatcher("albums.jsp").forward(request, response);
                return;
            }
            
            int productId;
            try {
                productId = Integer.parseInt(productIdStr);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid product ID format.");
                request.getRequestDispatcher("artist.jsp").forward(request, response);
                return;
            }

            // Validate inputs
            if (productName == null || productName.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Product name is required.");
                request.getRequestDispatcher("editproduct.jsp").forward(request, response);
                return;
            }

            // Validate price
            if (priceStr == null || priceStr.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Price is required.");
                request.getRequestDispatcher("editproduct.jsp").forward(request, response);
                return;
            }
            
            try {
                double price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    request.setAttribute("errorMessage", "Price must be greater than zero.");
                    request.getRequestDispatcher("editproduct.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid price format.");
                request.getRequestDispatcher("editproduct.jsp").forward(request, response);
                return;
            }

            // Check if new file is uploaded
            String filePath = currentLink;
            if (audioFilePart != null && audioFilePart.getSize() > 0) {
                String fileName = getFileName(audioFilePart);
                if (fileName == null) {
                    request.setAttribute("errorMessage", "Invalid file uploaded.");
                    request.getRequestDispatcher("editproduct.jsp").forward(request, response);
                    return;
                }

                // Validate file extension
                String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
                String[] validExtensions = {"wav", "flac", "alac", "mqa"};
                boolean isValidExtension = false;
                for (String ext : validExtensions) {
                    if (ext.equals(fileExtension)) {
                        isValidExtension = true;
                        break;
                    }
                }
                if (!isValidExtension) {
                    request.setAttribute("errorMessage", "Invalid file type. Only WAV, FLAC, ALAC, or MQA files are allowed.");
                    request.getRequestDispatcher("editproduct.jsp").forward(request, response);
                    return;
                }

                // Validate file size
                long maxFileSize = 50 * 1024 * 1024; // 50 MB
                if (audioFilePart.getSize() > maxFileSize) {
                    request.setAttribute("errorMessage", "File size exceeds 50MB limit.");
                    request.getRequestDispatcher("editproduct.jsp").forward(request, response);
                    return;
                }

                // Generate unique filename
                String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;
                String uploadPath = getServletContext().getRealPath("") + "uploads";
                Files.createDirectories(Paths.get(uploadPath));
                String newFilePath = uploadPath + java.io.File.separator + uniqueFileName;

                // Save file to disk
                try (InputStream fileContent = audioFilePart.getInputStream()) {
                    Files.copy(fileContent, Paths.get(newFilePath), StandardCopyOption.REPLACE_EXISTING);
                }
                
                // Update file path for database
                filePath = "uploads/" + uniqueFileName;
                
                // Delete old file if exists (optional)
                if (currentLink != null && !currentLink.isEmpty()) {
                    try {
                        String oldFilePath = getServletContext().getRealPath("") + currentLink;
                        Files.deleteIfExists(Paths.get(oldFilePath));
                    } catch (IOException e) {
                        // Log error but continue with update
                        System.err.println("Error deleting old file: " + e.getMessage());
                    }
                }
            }

            // Update data in database
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                
                String sql;
                if (audioFilePart != null && audioFilePart.getSize() > 0) {
                    // Update with new file
                    sql = "UPDATE product SET product_name = ?, price = ?, description = ?, link = ? WHERE product_id = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, productName);
                    stmt.setString(2, priceStr);
                    stmt.setString(3, description != null ? description : "");
                    stmt.setString(4, filePath);
                    stmt.setInt(5, productId);
                } else {
                    // Update without changing file
                    sql = "UPDATE product SET product_name = ?, price = ?, description = ? WHERE product_id = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, productName);
                    stmt.setString(2, priceStr);
                    stmt.setString(3, description != null ? description : "");
                    stmt.setInt(4, productId);
                }
                
                int rowsAffected = stmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    request.getSession().setAttribute("successMessage", "Product updated successfully!");
                } else {
                    request.getSession().setAttribute("errorMessage", "No product found with ID: " + productId);
                }
                
            } catch (SQLException e) {
                if (e.getMessage().contains("Duplicate entry")) {
                    request.getSession().setAttribute("errorMessage", "Product name already exists. Please choose a different name.");
                } else {
                    request.getSession().setAttribute("errorMessage", "Database error: " + e.getMessage());
                }
            } finally {
                try { if (stmt != null) stmt.close(); } catch (Exception e) { }
                try { if (conn != null) conn.close(); } catch (Exception e) { }
            }

            // Redirect to albums page
            response.sendRedirect("artist.jsp");

        } catch (ClassNotFoundException e) {
            request.getSession().setAttribute("errorMessage", "MySQL JDBC Driver not found: " + e.getMessage());
            response.sendRedirect("artist.jsp");
        } catch (IllegalStateException e) {
            if (e.getCause() instanceof org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException) {
                request.getSession().setAttribute("errorMessage", "Request size exceeds 60MB limit. Please upload a smaller file.");
            } else {
                request.getSession().setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            }
            response.sendRedirect("artist.jsp");
        } catch (Exception e) {
            request.getSession().setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            response.sendRedirect("artist.jsp");
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        if (contentDisp != null) {
            for (String cd : contentDisp.split(";")) {
                if (cd.trim().startsWith("filename")) {
                    return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
        }
        return null;
    }

}
