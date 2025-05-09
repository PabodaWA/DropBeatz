<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*, AdminOrdersPackage.AdminOrderModel" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./CSS/Admin1.css">
    <title>Admin Dashboard - Orders</title>
</head>
<body>
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
    <li><a href="AdminArtist.jsp">Artist Control</a></li>
    <li><a href="AdminUser.jsp">User Control</a></li>
    <li><a href="Admin.jsp">Admin Control</a></li>
  </ul>
</nav>
        <!-- Main Content -->
        <main class="content">
            <h1>Orders</h1>
            
            <% if(request.getAttribute("allOrders") != null) { %>
                <table>
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Customer ID</th>
                            <th>Product ID</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${allOrders}" var="order">
                            <tr>
                                <td>${order.getOrder_id()}</td>
                                <td>${order.getUser_id()}</td>
                                <td>${order.getProduct_id()}</td>
                              
                            </tr>
                        </c:forEach>
                        
                        <c:if test="${empty allOrders}">
                            <tr>
                                <td colspan="4">No orders found.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            <% } else { %>
                <div class="message">
                    <p>Loading orders...</p>
                    <script>
                        window.location.href = "AdminGetAllOrderServlet";
                    </script>
                </div>
            <% } %>
        </main>
    </div>
    <jsp:include page="Footer.jsp" />
</body>
</html>