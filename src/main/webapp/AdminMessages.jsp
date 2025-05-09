<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                <a href="admindashboard.jsp" class="nav-item active">
                    <i class="fas fa-home"></i>
                    <span>Dashboard</span>
                </a>
            </div>
            
            <div class="nav-section">
                <h3>Management</h3>
                <a href="AdminProducts.jsp" class="nav-item">
                    <i class="fas fa-music"></i>
                    <span>Products</span>
                </a>
                <a href="AdminArtists.jsp" class="nav-item">
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

                <a href="logout.jsp" class="nav-item">
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
                   
                </div>
                
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>admin</td>
                            <td>admin@dropbeatz.com</td>
                            <td>Super Admin</td>
                            <td><span class="status active">Active</span></td>
                            <td>
                                <button class="action-button edit-button">Edit</button>
                                <button class="action-button delete-button">Delete</button>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>manager</td>
                            <td>manager@dropbeatz.com</td>
                            <td>Content Manager</td>
                            <td><span class="status active">Active</span></td>
                            <td>
                                <button class="action-button edit-button">Edit</button>
                                <button class="action-button delete-button">Delete</button>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>editor</td>
                            <td>editor@dropbeatz.com</td>
                            <td>Editor</td>
                            <td><span class="status inactive">Inactive</span></td>
                            <td>
                                <button class="action-button edit-button">Edit</button>
                                <button class="action-button delete-button">Delete</button>
                            </td>
                        </tr>
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