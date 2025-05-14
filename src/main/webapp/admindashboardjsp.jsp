<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@page import="java.util.*, com.dropbeatzadmin.model.AdminModel" %>
<%@page import="com.dropbetzadmin.control.AdminController" %>

<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("Signin.jsp");
        return;
    }
%>
    
<!DOCTYPE html>
<html>
<head>
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
                <a href="admindashboardjsp.jsp" class="nav-item active">
                    <i class="fas fa-home"></i>
                    <span>Dashboard</span>
                </a>
            </div>
            
            <div class="nav-section">
                <h3>Management</h3>
                <a href="Adminproduct.jsp" class="nav-item">
                    <i class="fas fa-music"></i>
                    <span>Products</span>
                </a>
                
                <a href="AdminOrders.jsp" class="nav-item">
                    <i class="fas fa-eye"></i>
                    <span>Orders</span>
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
                <a href="adminprofile.jsp" class="nav-item">
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
                        <span><%= session.getAttribute("username") %></span>
                    </div>
                </div>
            </div>
            
            <!-- Admin List Table -->
            <div class="table-container">
                <div class="table-header">
                    <h3>Admin List</h3>
                    <a href="AdminAddadminForm.jsp" >
                    <button class="add-button">
                        <i class="fas fa-plus"></i> Add Admin
                    </button>
                    </a>
                </div>
                
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>fullname</th>
                            <th>Username</th>
                            <th>country</th>
                            <th>contactnumber</th>
                            <th>Password </th>
                            <th>email</th>
                            <th>role </th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                        AdminController adminController = new AdminController();
                        List<AdminModel> adminList = adminController.getAllAdmin();

                        for(AdminModel admin : adminList) {
                    %>
                        <tr>
                            <td><%= admin.getId()%></td>
                            <td><%= admin.getFullname()%></td>
                            <td><%= admin.getUsername()%></td>
                            <td><%= admin.getCountry()%></td>
                            <td><%= admin.getContactnumber()%></td>
                            <td><%= admin.getPassword()%></td>
                            <td><%= admin.getEmail()%></td>
                            <td><%= admin.getRole()%></td>
                            
                            <td>
                                <a href="AdminUpdateAdmin.jsp?id=<%= admin.getId() %>&fullname=<%= admin.getFullname() %>&username=<%= admin.getUsername()%>&country=<%= admin.getCountry() %>&contactnumber=<%=admin.getContactnumber() %>&password=<%=admin.getPassword() %>&email=<%=admin.getEmail() %>&role=<%=admin.getRole() %>" >
                                    <button class="action-button edit-button">Update</button>
                                </a> 
                                
                                <form action="Admindeleteservelet" method="post">
                                    <input type="hidden" name="id" value="<%= admin.getId() %>">
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
            
            <!-- Stats Cards -->
            <div style="display: grid; grid-template-columns: repeat(4, 1fr); gap: 20px; margin-bottom: 30px;">
                <div style="background-color: #222; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
                    <div style="display: flex; justify-content: space-between; align-items: center;">
                        <div>
                            <p style="color: #999; font-size: 14px;">Total Products</p>
                            <h3 style="font-size: 24px; margin-top: 5px;">254</h3>
                        </div>
                        <div style="background-color: rgba(233, 30, 99, 0.2); padding: 10px; border-radius: 50%;">
                            <i class="fas fa-music" style="color: #e91e63; font-size: 20px;"></i>
                        </div>
                    </div>
                    <p style="color: #2ecc71; font-size: 12px; margin-top: 10px;">
                        <i class="fas fa-arrow-up"></i> 12.5% from last month
                    </p>
                </div>
                
                <div style="background-color: #222; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
                    <div style="display: flex; justify-content: space-between; align-items: center;">
                        <div>
                            <p style="color: #999; font-size: 14px;">Total Artists</p>
                            <h3 style="font-size: 24px; margin-top: 5px;">75</h3>
                        </div>
                        <div style="background-color: rgba(33, 150, 243, 0.2); padding: 10px; border-radius: 50%;">
                            <i class="fas fa-user-friends" style="color: #2196f3; font-size: 20px;"></i>
                        </div>
                    </div>
                    <p style="color: #2ecc71; font-size: 12px; margin-top: 10px;">
                        <i class="fas fa-arrow-up"></i> 5.3% from last month
                    </p>
                </div>
                
                <div style="background-color: #222; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
                    <div style="display: flex; justify-content: space-between; align-items: center;">
                        <div>
                            <p style="color: #999; font-size: 14px;">Total Users</p>
                            <h3 style="font-size: 24px; margin-top: 5px;">3,521</h3>
                        </div>
                        <div style="background-color: rgba(46, 204, 113, 0.2); padding: 10px; border-radius: 50%;">
                            <i class="fas fa-users" style="color: #2ecc71; font-size: 20px;"></i>
                        </div>
                    </div>
                    <p style="color: #2ecc71; font-size: 12px; margin-top: 10px;">
                        <i class="fas fa-arrow-up"></i> 8.7% from last month
                    </p>
                </div>
                
                <div style="background-color: #222; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
                    <div style="display: flex; justify-content: space-between; align-items: center;">
                        <div>
                            <p style="color: #999; font-size: 14px;">New Messages</p>
                            <h3 style="font-size: 24px; margin-top: 5px;">28</h3>
                        </div>
                        <div style="background-color: rgba(233, 30, 99, 0.2); padding: 10px; border-radius: 50%;">
                            <i class="fas fa-envelope" style="color: #e91e63; font-size: 20px;"></i>
                        </div>
                    </div>
                    <p style="color: #e74c3c; font-size: 12px; margin-top: 10px;">
                        <i class="fas fa-arrow-down"></i> 2.1% from last month
                    </p>
                </div>
            </div>
            
            <footer>
                <p>Copyright © DropBeatz 2025. All rights reserved.</p>
            </footer>
        </div>
    </div>

</body>
</html>