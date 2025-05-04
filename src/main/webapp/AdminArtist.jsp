<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="./CSS/Admin.css">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="Header.jsp"/>
 <!-- WRAP everything inside .admin-container -->
<div class="admin-container">
  
  <!-- Sidebar -->
  <nav class="sidebar">
    <a href="AdminDashBoard.jsp"><h2>Admin Dashboard</h2></a>
<ul>
    <li><a href="AdminOrders.jsp">Orders</a></li>
    <li><a href="AdminProducts.jsp">Products</a></li>
    <li><a href="AdminMessages.jsp">Messages</a></li>
    <li><a href="AdminArtist.jsp">Artists</a></li>
    <li><a href="AdminUser.jsp">Customer Control</a></li>
  </ul>
  </nav>

  <!-- Main Content -->
  <main class="content">
    <h1>Artists</h1>
    <table>
      <thead>
        <tr>
          <th>Artist ID</th>
          <th>Name</th>
          <th>Email</th>
          <th>Password</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>101</td>
          <td>CUST001</td>
          <td>Track A</td>
          <td>Shipped</td>
         
          <td>
            <button class="update-btn">Update</button>
            <button class="delete-btn">Delete</button>
          </td>
        </tr>
      </tbody>
    </table>
  </main>

</div>

  <!-- JAVASCRIPT -->
  <script>

  </script>

<jsp:include page="Footer.jsp"/>
</body>
</html>

