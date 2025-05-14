<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*, com.dropbeatzadmin.model.AdminProductModel" %>
<%@ page import="com.dropbetzadmin.control.AdminProductController" %>

    <%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("Signin.jsp");
        return;
    }
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet" href="./CSS/NewAdmin.css">
<title>Admin Dashboard</title>
</head>
<body>

<div class="container">
        <!-- Sidebar -->
        <div class="sidebar">
            <div class="logo">
                <img src="Pictures/logo.png" alt="DropBeatz Logo">
                <h1>Drop<span>Beatz</span></h1>
            </div>
            
            <div class="nav-section">
                <h3>Main</h3>
                <a href="admindashboard.jsp" class="nav-item">
                    <i class="fas fa-home"></i>
                    <span>Dashboard</span>
                </a>
            </div>
            
            <div class="nav-section">
                <h3>Management</h3>
                <a href="Adminproduct.jsp" class="nav-item active">
                    <i class="fas fa-music"></i>
                    <span>Products</span>
                </a>
                <a href="AdminArtist.jsp" class="nav-item">
                    <i class="fas fa-user-friends"></i>
                    <span>Artists</span>
                </a>
                <a href="AdminUser.jsp" class="nav-item">
                    <i class="fas fa-users"></i>
                    <span>Users</span>
                </a>
                <a href="AdminMessages.jsp" class="nav-item">
                    <i class="fas fa-envelope"></i>
                    <span>Messages</span>
                </a>
            </div>
            
            <div class="nav-section">
                <h3>Settings</h3>
                <a href="profile.jsp" class="nav-item">
                    <i class="fas fa-user-cog"></i>
                    <span>Profile</span>
                </a>

                <a href="adminlogout.jsp" class="nav-item">
                    <i class="fas fa-sign-out-alt"></i>
                    <span>Logout</span>
                </a>
            </div>
        </div>
        
        <!-- Main Content -->
        <div class="main-content">
            <div class="header">
                <h2>Admin Dashboard</h2>
                
                <div class="user-info">
                    <div class="search-bar">
                        <i class="fas fa-search" style="color: #777;"></i>
                        <input type="text" placeholder="Search...">
                    </div>
                    
                    <div class="notification">
                        <i class="fas fa-bell"></i>
                        <span class="badge"></span>
                    </div>
                    
                    <div class="notification">
                        <i class="fas fa-envelope"></i>
                        <span class="badge"></span>
                    </div>
                    
                    <div class="user-profile">
                        <img src="/api/placeholder/35/35" alt="Admin Profile">
                        <span>Admin</span>
                    </div>
                </div>
            </div>
            
            <!-- Admin List Table -->
            <div class="table-container">
                <div class="table-header">
                    <h3>Messages</h3>
                    <a href="AdminAddProductForm.jsp" >
                    <button class="add-button">
                        <i class="fas fa-plus"></i> Add Product
                    </button>
                   </a>
                </div>
                
                <table>
                    <thead>
                       <tr>
          <th>Product ID</th>
          <th>Product Name</th>
           <th>Price</th>
           <th>Description</th>
           <th>Link</th>
          <th>Action</th>

        </tr>
                    </thead>
                    <tbody>
                        <%
    List<AdminProductModel> productList = AdminProductController.getAllproduct();
%>
      <%
        for(AdminProductModel product : productList){
    %>
    <tr>
        <td><%= product.getProduct_id() %></td>
        <td><%= product.getProduct_name() %></td>
        <td><%= product.getPrice() %></td>
        <td><%= product.getDescription() %></td>
        <td><%= product.getLink()%></td>
        
                           <td>
         
			<a href="AdminUpdateProduct.jsp?product_id=<%= product.getProduct_id() %>&product_name=<%= product.getProduct_name() %>&price=<%= product.getPrice() %>&description=<%= product.getDescription() %>&link=<%= product.getLink() %>" >
       
            <button class="action-button edit-button">Update</button>
            </a> 
           
<form action="AdminProductDeleteServlet" method="post">
    <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>">
    <button class="action-button delete-button" type="submit">Delete</button>
</form>          

</td>
                        </tr>
                        
                           <%
        }
    %>
      
                    </tbody>
                </table>
            </div>
            
            
            <footer>
                <p>Copyright © DropBeatz 2025. All rights reserved.</p>
            </footer>
        </div>
    </div>

</body>
</html>