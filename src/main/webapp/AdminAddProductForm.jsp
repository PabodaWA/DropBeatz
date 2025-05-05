<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="./CSS/AddProduct.css">
<title>Add Product</title>
</head>
<body>

<div class="form-container">
    <h2>Add New Product</h2>
    <form id="productForm" action = "AdminProductInsertServlet" method ="post">
      <label for="productName">Product Name:</label>
      <input type="text" id="productName" name="product_name" required>
      
      <label for="productPrice">Price ($):</label>
      <input type="number" id="productPrice" name="price" required>
      
       <label for="productName">Product link:</label>
      <input type="text" id="productName" name="link" required>
      

      <label for="productDesc">Description:</label>
      <textarea id="productDesc" rows="4" name="description" required></textarea>

      <button type="submit">Add Product</button>
    </form>

    
  </div>

 

</body>
</html>