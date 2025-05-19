<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DropBeatZ - Artist Dashboard</title>
    <link rel="stylesheet" href="artist.css">
    <!-- Preload images to improve performance -->
    <link rel="preload" href="/img/hdp1.jpg" as="image">
    <link rel="preload" href="/img/mn2.jpg" as="image">
    <link rel="preload" href="/img/hifi.webp" as="image">
    <link rel="preload" href="/img/hifi2.png" as="image">
    <style>
    .tab-content {
        display: none;
    }
    .tab-content.active {
        display: block;
    }
    .content-grid {
        display: grid;
        grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
        gap: 20px;
        margin-top: 20px;
    }
    .content-card {
        border: 1px solid #ddd;
        border-radius: 8px;
        padding: 15px;
        background-color: #f9f9f9;
        color: #333; /* Darker font color for product details */
    }
    .content-card h3, .content-card p {
        color: #333; /* Ensure headers and paragraphs are dark */
    }
    .content-card p strong {
        color: #333; /* Explicitly set color for strong tags to ensure visibility */
        font-weight: bold; /* Ensure the strong tag appears bold */
    }
    .card-actions {
        display: flex;
        gap: 10px;
        margin-top: 10px;
    }
    .cta-button.small {
        padding: 5px 10px;
        font-size: 14px;
    }
    .cta-button.delete {
        background-color: #ff4d4d;
    }
    .bulk-actions {
        margin-top: 20px;
    }
    .success-message {
        background-color: #d4edda;
        color: #155724;
        padding: 10px;
        border-radius: 4px;
        margin-bottom: 15px;
    }
    .error-message {
        background-color: #f8d7da;
        color: #721c24;
        padding: 10px;
        border-radius: 4px;
        margin-bottom: 15px;
    }
</style>
</head>
<body>
<div class="container">
    <div class="berth"></div>
    <nav>
        <a href="/"><b>Drop</b>BeatZ</a>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/songs">Songs</a></li>
            <li><a href="/albums">Albums</a></li>
            <li><a href="/about">About</a></li>
        </ul>
        <a href="#" class="login"><i class="uil uil-user"></i>Logout</a>
    </nav>
    
    <div class="artist-dashboard">
        <div class="tabs">
            <a class="tab" href="addproduct.jsp">Upload Music</a>
            <button class="tab active" onclick="openTab('albums')">Albums</button>
            <button class="tab" onclick="openTab('profile')">Profile</button>
        </div>
        
        <div id="albums" class="tab-content active">
            <h2>My Albums</h2>
            
            <% 
            // Display success message if available
            String successMessage = (String) session.getAttribute("successMessage");
            if (successMessage != null && !successMessage.isEmpty()) {
                out.println("<div class='success-message'>" + successMessage + "</div>");
                session.removeAttribute("successMessage");
            }
            
            // Display error message if available
            String errorMessage = (String) session.getAttribute("errorMessage");
            if (errorMessage != null && !errorMessage.isEmpty()) {
                out.println("<div class='error-message'>" + errorMessage + "</div>");
                session.removeAttribute("errorMessage");
            }
            %>
            
            <div class="content-grid">
            <% 
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
                
                String query = "SELECT product_id, product_name, price, description, link FROM product";
                pstmt = conn.prepareStatement(query);
                rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    int productId = rs.getInt("product_id");
                    String productName = rs.getString("product_name");
                    String price = rs.getString("price");
                    String description = rs.getString("description");
                    
                    out.println("<div class='content-card'>");
                    out.println("<h3>" + productName + "</h3>");
                    out.println("<p><strong>Price:</strong> $" + price + "</p>");
                    out.println("<p><strong>Description:</strong> " + (description != null && !description.isEmpty() ? description : "No description available") + "</p>");
                    out.println("<div class='card-actions'>");
                    out.println("<a href='editproduct?id=" + productId + "' class='cta-button small'>Edit</a>");
                    out.println("<a href='#' onclick='confirmDelete(" + productId + ")' class='cta-button small delete'>Delete</a>");
                    out.println("</div>");
                    out.println("</div>");
                }
                
                
                
            } catch (Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
                e.printStackTrace();
            } finally {
                try { if (rs != null) rs.close(); } catch (Exception e) { }
                try { if (pstmt != null) pstmt.close(); } catch (Exception e) { }
                try { if (conn != null) conn.close(); } catch (Exception e) { }
            }
            %>
            </div>
        </div>
        
        <div id="profile" class="tab-content">
            <h2>Artist Profile</h2>
            <div class="artist-profile">
                <img src="/img/artist-avatar.jpg" alt="Artist Avatar" class="avatar">
                <p><strong>Name:</strong> Luna Vox</p>
                <p><strong>Email:</strong> lunavox@dropbeatz.com</p>
                <p><strong>Bio:</strong> Passionate vocalist bringing soulful hi-res audio to audiophiles worldwide.</p>
                <p><strong>Earnings:</strong> $1,245.50</p>
                <a href="#" class="cta-button">Edit Profile</a>
            </div>
        </div>
    </div>
</div>

    <script>
function openTab(tabName) {
    // Hide all tab content
    var tabContents = document.getElementsByClassName("tab-content");
    for (var i = 0; i < tabContents.length; i++) {
        tabContents[i].classList.remove("active");
    }
    
    // Remove active class from all tabs
    var tabs = document.getElementsByClassName("tab");
    for (var i = 0; i < tabs.length; i++) {
        tabs[i].classList.remove("active");
    }
    
    // Show the specific tab content
    document.getElementById(tabName).classList.add("active");
    
    // Add active class to the button that opened the tab
    event.currentTarget.classList.add("active");
}

function confirmDelete(productId) {
    if (confirm("Are you sure you want to delete this product?")) {
        window.location.href = "DeleteProduct?id=" + productId;
    }
}

// Automatically initialize the tab
document.addEventListener('DOMContentLoaded', function() {
    // Check if there's a hash in the URL
    if(window.location.hash) {
        var tab = window.location.hash.substring(1);
        if(document.getElementById(tab)) {
            openTab(tab);
        }
    }
});
</script>
</body>
</html>