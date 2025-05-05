<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
          <th>Action</th>

        </tr>
      </thead>
      
      <tbody>
      
                
       <c:forEach var="a_product" items="${allProducts}">
    
      
        <tr>
          <td>${a_product.product_id}</td>
          <td>${a_product.product_name}</td>
          <td>${a_product.price}</td>
          <td>${a_product.description}</td>
          <td>
            <button class="update-btn">Update</button>
            <button class="delete-btn">Delete</button>
          </td>
        </tr>
        
       </c:forEach>
        
      </tbody>
      
     
      </tbody>
      
      
    </table>
  </main>

</div>

  <!-- JAVASCRIPT -->
  <script>

  </script>

<jsp:include page="Footer.jsp" />
</body>
</html>

