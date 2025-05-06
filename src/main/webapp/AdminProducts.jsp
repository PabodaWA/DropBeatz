<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*, AdminProduct.AdminProductModel" %>
<%@ page import="AdminProduct.AdminProductController" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="./CSS/Admin.css">
<title>Insert title here</title>
</head>
<body>s

<jsp:include page="Header.jsp" />
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
    <h1>Products</h1>
    <a href="AdminAddProductForm.jsp"><h3>Click here to add a new product</h3></a>
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
       
            <button class="update-btn">Update</button>
            </a> 
<form action="AdminProductDeleteServlet" method="post">
    <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>">
    <button class="delete-btn" type="submit">Delete</button>
</form>          

</td>
    </tr>
    <%
        }
    %>
      
      </tbody>
      
      
    </table>
  </main>

</div>

  
  <script>

  </script>

<jsp:include page="Footer.jsp" />
</body>
</html>

