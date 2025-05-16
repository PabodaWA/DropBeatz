package com.dropbeatzadmin.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class SaveProductServlet
 */
@WebServlet("/SaveProductServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 15    // 15 MB
)
public class SaveProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(SaveProductServlet.class.getName());
    
    // Database connection parameters - these should be configured in a properties file or environment variables
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    
    // Directory to store uploaded files
    private static final String UPLOAD_DIRECTORY = "audio_uploads";
    
    @Override
    public void init() throws ServletException {
        // Initialize database parameters from context params or use defaults
        dbUrl = getServletContext().getInitParameter("DB_URL");
        if (dbUrl == null) dbUrl = "jdbc:mysql://localhost:3306/dropbeatz";
        
        dbUser = getServletContext().getInitParameter("DB_USER");
        if (dbUser == null) dbUser = "root";
        
        dbPassword = getServletContext().getInitParameter("DB_PASSWORD");
        if (dbPassword == null) dbPassword = "root";
        
        // Register the JDBC driver at startup
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            LOGGER.info("MySQL JDBC Driver registered successfully");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "MySQL JDBC Driver not found", e);
            throw new ServletException("MySQL JDBC Driver not found", e);
        }
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveProductServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/addproduct.jsp");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String productName = request.getParameter("productName");
            String priceStr = request.getParameter("price");
            String description = request.getParameter("description");
            System.out.println("prinitng passinfg values");
            System.out.println(productName);
            System.out.println(priceStr);
            System.out.println(description);
            // Validate required fields
            if (productName == null || productName.trim().isEmpty() || 
                priceStr == null || priceStr.trim().isEmpty()) {
                handleError(request, response, "Product name and price are required fields");
                return;
            }
            
            // Validate price format
            double price;
            try {
                price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    handleError(request, response, "Price must be greater than zero");
                    return;
                }
            } catch (NumberFormatException e) {
                handleError(request, response, "Invalid price format");
                return;
            }
            
            // Get the audio file
            Part audioFilePart = request.getPart("audioFile");
            if (audioFilePart == null || audioFilePart.getSize() == 0) {
                handleError(request, response, "Audio file is required");
                return;
            }
            
            String fileName = getSubmittedFileName(audioFilePart);
            
            // Validate file extension
            if (!isValidAudioFile(fileName)) {
                handleError(request, response, "Invalid file format. Only WAV, FLAC, ALAC, and MQA files are allowed.");
                return;
            }
            
            // Generate unique filename to prevent overwriting
            String uniqueFileName = generateUniqueFileName(fileName);
            
            // Create directory if it doesn't exist
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadPath = applicationPath + File.separator + UPLOAD_DIRECTORY;
            
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                boolean created = uploadDir.mkdirs();
                if (!created) {
                    LOGGER.warning("Failed to create directory: " + uploadPath);
                    handleError(request, response, "Failed to create upload directory");
                    return;
                }
            }
            
            // Save the file
            String filePath = uploadPath + File.separator + uniqueFileName;
            Path path = Paths.get(filePath);
            
            try (InputStream inputStream = audioFilePart.getInputStream()) {
                Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to save file", e);
                handleError(request, response, "Failed to save file: " + e.getMessage());
                return;
            }
            
            // Relative URL path to access the file
            String fileUrl = request.getContextPath() + "/" + UPLOAD_DIRECTORY + "/" + uniqueFileName;
            
            // Store data in the database
            if (saveToDatabase(productName, price, description, fileUrl)) {
                // Success - set message and redirect
                request.getSession().setAttribute("successMessage", "Product has been successfully added to the database!");
                response.sendRedirect(request.getContextPath() + "/addproduct.jsp");
            } else {
                // Failed to save to database
                handleError(request, response, "Failed to add product to database");
            }
            
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error in doPost", e);
            handleError(request, response, "An unexpected error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Save product information to the database
     */
    private boolean saveToDatabase(String productName, double price, String description, String fileUrl) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Establish connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            
            // Create SQL statement
            String sql = "INSERT INTO product (product_name, price, description, link) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productName);
            pstmt.setDouble(2, price);
            pstmt.setString(3, description);
            pstmt.setString(4, fileUrl);
            
            // Execute SQL statement
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Database error", e);
            return false;
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Error closing database resources", e);
            }
        }
    }
    
    /**
     * Handle errors uniformly
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage) 
            throws ServletException, IOException {
        LOGGER.warning(errorMessage);
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("/addproduct.jsp").forward(request, response);
    }
    
    /**
     * Helper method to get the submitted filename
     */
    private String getSubmittedFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
    
    /**
     * Helper method to validate file extensions
     */
    private boolean isValidAudioFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }
        
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("wav") || extension.equals("flac") || 
               extension.equals("alac") || extension.equals("mqa");
    }
    
    /**
     * Helper method to generate unique filename
     */
    private String generateUniqueFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + extension;
    }
}