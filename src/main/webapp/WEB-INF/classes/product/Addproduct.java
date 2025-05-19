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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet("/Addproduct")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
    maxFileSize = 1024 * 1024 * 50,     // 50 MB
    maxRequestSize = 1024 * 1024 * 60   // 60 MB
)
public class Addproduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addproduct() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Load MySQL JDBC driver - THIS WAS MISSING
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Extract form fields
            String productName = request.getParameter("productName");
            String priceStr = request.getParameter("price");
            String description = request.getParameter("description");
            Part audioFilePart = request.getPart("audioFile");

            // Validate inputs
            if (productName == null || productName.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Product name is required.");
                request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                return;
            }

            // Validate price - Adding validation for price
            if (priceStr == null || priceStr.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Price is required.");
                request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                return;
            }
            
            try {
                double price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    request.setAttribute("errorMessage", "Price must be greater than zero.");
                    request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid price format.");
                request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                return;
            }

            // Validate audio file
            if (audioFilePart == null || audioFilePart.getSize() == 0) {
                request.setAttribute("errorMessage", "Please select an audio file.");
                request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                return;
            }

            String fileName = getFileName(audioFilePart);
            if (fileName == null) {
                request.setAttribute("errorMessage", "Invalid file uploaded.");
                request.getRequestDispatcher("addproduct.jsp").forward(request, response);
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
                request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                return;
            }

            // Validate file size
            long maxFileSize = 50 * 1024 * 1024; // 50 MB
            if (audioFilePart.getSize() > maxFileSize) {
                request.setAttribute("errorMessage", "File size exceeds 50MB limit.");
                request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                return;
            }

            // Generate unique filename
            String uniqueFileName = UUID.randomUUID().toString() + "." + fileExtension;
            String uploadPath = getServletContext().getRealPath("") + "uploads";
            Files.createDirectories(Paths.get(uploadPath));
            String filePath = uploadPath + java.io.File.separator + uniqueFileName;

            // Save file to disk
            try (InputStream fileContent = audioFilePart.getInputStream()) {
                Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            }

            // Store data in database
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO product (product_name, price, description, link) VALUES (?, ?, ?, ?)")) {
                stmt.setString(1, productName);
                stmt.setString(2, priceStr); // Price is stored as string in your schema
                stmt.setString(3, description != null ? description : "");
                stmt.setString(4, "uploads/" + uniqueFileName);
                stmt.executeUpdate();
            } catch (SQLException e) {
                if (e.getMessage().contains("Duplicate entry")) {
                    request.setAttribute("errorMessage", "Product name already exists. Please choose a different name.");
                } else {
                    request.setAttribute("errorMessage", "Database error: " + e.getMessage());
                }
                request.getRequestDispatcher("addproduct.jsp").forward(request, response);
                return;
            }

            // Set success message and redirect
            request.getSession().setAttribute("successMessage", "Product added successfully!");
            response.sendRedirect("artist.jsp");

        } catch (ClassNotFoundException e) {
            request.setAttribute("errorMessage", "MySQL JDBC Driver not found: " + e.getMessage());
            request.getRequestDispatcher("artist.jsp").forward(request, response);
        } catch (IllegalStateException e) {
            if (e.getCause() instanceof org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException) {
                request.setAttribute("errorMessage", "Request size exceeds 60MB limit. Please upload a smaller file.");
                request.getRequestDispatcher("artist.jsp").forward(request, response);
            } else {
                throw e;
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("artist.jsp").forward(request, response);
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