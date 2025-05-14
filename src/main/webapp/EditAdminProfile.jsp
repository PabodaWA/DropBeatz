<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Admin Profile</title>
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
        .profile-form {
            padding: 20px;
        }
        .form-group {
            margin-bottom: 20px;
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
                        <h2>Edit Admin Profile</h2>
                        <p>Update your personal information</p>
                    </div>
                    
                    <div class="profile-form">
                        <%-- Display error message if any --%>
                        <% if(session.getAttribute("errorMessage") != null) { %>
                            <div class="alert alert-danger" role="alert">
                                <%= session.getAttribute("errorMessage") %>
                                <% session.removeAttribute("errorMessage"); %>
                            </div>
                        <% } %>
                        
                        <form action="EditAdminProfileServlet" method="post">
                            <div class="form-group">
                                <label for="fullname">Full Name</label>
                                <input type="text" class="form-control" id="fullname" name="fullname" value="${adminUser.fullname}" required>
                            </div>
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input type="text" class="form-control" id="username" value="${adminUser.username}" readonly>
                                <small class="form-text text-muted">Username cannot be changed</small>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" class="form-control" id="email" name="email" value="${adminUser.email}" required>
                            </div>
                            <div class="form-group">
                                <label for="country">Country</label>
                                <input type="text" class="form-control" id="country" name="country" value="${adminUser.country}" required>
                            </div>
                            <div class="form-group">
                                <label for="contactnumber">Contact Number</label>
                                <input type="text" class="form-control" id="contactnumber" name="contactnumber" value="${adminUser.contactnumber}" required>
                            </div>
                            <div class="form-group">
                                <label for="role">Role</label>
                                <input type="text" class="form-control" id="role" value="${adminUser.role}" readonly>
                                <small class="form-text text-muted">Role cannot be changed</small>
                            </div>
                            
                            <div class="form-group d-flex justify-content-between">
                                <button type="submit" class="btn btn-primary">Save Changes</button>
                                <a href="AdminProfileServlet" class="btn btn-secondary">Cancel</a>
                            </div>
                        </form>
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