<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./CSS/AddProduct.css">
<title>Insert title here</title>
</head>
<body>
<%
String product_id = request.getParameter("product_id");
String product_name = request.getParameter("product_name");
String price = request.getParameter("price");
String description = request.getParameter("description");
String link = request.getParameter("link");

%>

<div class="form-container">
    <h2>Add New Product</h2>
    <form id="productForm" action = "AdminProductUpdateServelet" method ="post">
    
    <label for="productId">Product ID:</label>
    <input type="text" id="productID" name="product_id" value="<%= product_id %>" readonly>
    
      <label for="productName">Product Name:</label>
      <input type="text" id="productName" name="product_name" value=<%= product_name %> required>
      
      <label for="productPrice">Price ($):</label>
      <input type="number" id="productPrice" name="price" value=<%= price %> required>
      
       <label for="productName">Product link:</label>
      <input type="text" id="productLink" name="link" value=<%= link %> required>
      

      <label for="productDesc">Description:</label>
      <input type="text" id="productDesc" name="description" value=<%= description %> required>


      <button type="submit">Update Product</button>
    </form>

    
  </div>
</body>
</html>