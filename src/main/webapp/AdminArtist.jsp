<%@page import="com.dropbeatzadmin.control.AdminArtistController"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,com.dropbeatzadmin.model.AdminArtistModel" %>
<%@ page import="com.dropbeatzadmin.control.AdminOrderController" %>

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
                <a href="admindashboardjsp.jsp" class="nav-item ">
                    <i class="fas fa-home"></i>
                    <span>Dashboard</span>
                </a>
            </div>
            
            <div class="nav-section">
                <h3>Management</h3>
               
                
                
                
                <a href="AdminArtist.jsp" class="nav-item active">
                    <i class="fas fa-user-friends"></i>
                    <span>Artists</span>
                </a>
               
              
            </div>
            
            <div class="nav-section">
                
                

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
            
                    <div class="user-profile">
                        <img src="/api/placeholder/35/35" alt="Admin Profile">
                        <span><%= session.getAttribute("username") %></span>
                    </div>
                </div>
            </div>
            
       
            
            <!-- Admin List Table -->
            <div class="table-container">
                <div class="table-header">
                <a href="AdminAddArtistForm.jsp" >
                    <h3>Artist Control</h3>
                    <button class="add-button">
                        <i class="fas fa-plus"></i> Add Artist
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
                            <th>email</th>
                            <th>Password </th>
                            <th>role </th>
                        </tr>
                    </thead>
                    <tbody>
                    
                      <%
    List<AdminArtistModel> artistList = AdminArtistController.getAllArtist();
%>
      <%
        for(AdminArtistModel artist : artistList){
    %>
                         <tr>
                            <td><%= artist.getId()%></td>
                            <td><%= artist.getFullname()%></td>
                            <td><%= artist.getUsername()%></td>
                            <td><%= artist.getCountry()%></td>
                            <td><%= artist.getContactnumber()%></td>
                            <td><%= artist.getPassword()%></td>
                            <td><%= artist.getEmail()%></td>
                            <td><%= artist.getRole()%></td>
                           <td>
                                <a href="AdminUpdateArtist.jsp?id=<%= artist.getId() %>&fullname=<%= artist.getFullname() %>&username=<%= artist.getUsername()%>&country=<%= artist.getCountry() %>&contactnumber=<%=artist.getContactnumber() %>&password=<%=artist.getPassword() %>&email=<%=artist.getEmail() %>&role=<%=artist.getRole() %>" >
       
            <button class="action-button edit-button">Update</button>
            </a> 
                                
                               <form action="AdminDeletArtistServelet" method="post">
    <input type="hidden" name="id" value="<%= artist.getId() %>">
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