<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./CSS/AdminDashboard.css">
<title>Admin Dashboard</title>
</head>
<body>
<jsp:include page="Header.jsp" />
  <header>
  
  <div class="header">
            <h1>Admin Dashboard</h1>
</div>
    <h1><a href="AdminDashBoard.jsp">Admin Dashboard</a></h1>
  </header>

  <!-- MAIN CONTAINER -->
  <div class="container">

    <!-- LEFT SIDEBAR MENU -->
<nav class="sidebar">
  <ul>
    <li><a href="AdminOrders.jsp">Orders</a></li>
    <li><a href="AdminProducts.jsp">Products</a></li>
    <li><a href="AdminMessages.jsp">Messages</a></li>
    <li><a href="AdminArtist.jsp">Artists</a></li>
    <li><a href="AdminUser.jsp">Customer Control</a></li>
  </ul>
</nav>


    <!-- RIGHT CONTENT AREA -->
    <main class="dashboard-content">
      <section class="admin-details">
        <h2>Admin Account Details</h2>
        <p>Name: <span id="admin-name">Loading...</span></p>
        <p>Email: <span id="admin-email">Loading...</span></p>
        <!-- You can add more fields here -->
      </section>
    </main>

  </div>
<jsp:include page="Footer.jsp" />
</body>
</html>