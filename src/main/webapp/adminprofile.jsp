<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dropbeatzadmin.model.UserModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .profile-container {
            max-width: 800px;
            margin: 30px auto;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 5px;
        }
        .profile-header {
            background-color: #007bff;
            color: white;
            padding: 20px;
            border-radius: 5px 5px 0 0;
            margin-bottom: 20px;
        }
        .profile-info {
            padding: 20px;
        }
        .info-row {
            margin-bottom: 15px;
            display: flex;
        }
        .info-label {
            font-weight: bold;
            width: 150px;
        }
        .info-value {
            flex-grow: 1;
        }
        .nav-link.active {
            background-color: #007bff;
            color: white !important;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar Navigation -->
            <div class="col-md-3 bg-light d-flex flex-column p-3" style="min-height: 100vh;">
                <h3 class="text-center mb-4">Admin Dashboard</h3>
                <div class="nav flex-column nav-pills">
                    <a class="nav-link" href="admindashboardjsp.jsp">Dashboard</a>
                    <a class="nav-link active" href="AdminProfileServlet">My Profile</a>
                    <!-- Add your other navigation links here -->
                </div>
                <div class="mt-auto">
                    <a class="nav-link text-danger" href="LogoutServlet">Logout</a>
                </div>
            </div>
            
            <!-- Main Content -->
            <div class="col-md-9">
                <div class="profile-container">
                    <div class="profile-header">
                        <h2>Admin Profile</h2>
                        <p>Manage your personal information</p>
                    </div>
                    
                    <div class="profile-info">
                        <%-- Display success message if any --%>
                        <% if(session.getAttribute("successMessage") != null) { %>
                            <div class="alert alert-success" role="alert">
                                <%= session.getAttribute("successMessage") %>
                                <% session.removeAttribute("successMessage"); %>
                            </div>
                        <% } %>
                        
                        <div class="info-row">
                            <div class="info-label">Full Name:</div>
                            <div class="info-value"><%= ((UserModel)request.getAttribute("adminUser")).getFullname() %></div>
                        </div>
                        <div class="info-row">
                            <div class="info-label">Username:</div>
                            <div class="info-value"><%= ((UserModel)request.getAttribute("adminUser")).getUsername() %></div>
                        </div>
                        <div class="info-row">
                            <div class="info-label">Email:</div>
                            <div class="info-value"><%= ((UserModel)request.getAttribute("adminUser")).getEmail() %></div>
                        </div>
                        <div class="info-row">
                            <div class="info-label">Country:</div>
                            <div class="info-value"><%= ((UserModel)request.getAttribute("adminUser")).getCountry() %></div>
                        </div>
                        <div class="info-row">
                            <div class="info-label">Contact Number:</div>
                            <div class="info-value"><%= ((UserModel)request.getAttribute("adminUser")).getContactnumber() %></div>
                        </div>
                        <div class="info-row">
                            <div class="info-label">Role:</div>
                            <div class="info-value"><%= ((UserModel)request.getAttribute("adminUser")).getRole() %></div>
                        </div>
                        
                        <div class="mt-4">
                            <a href="EditAdminProfileServlet" class="btn btn-primary">Edit Profile</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>