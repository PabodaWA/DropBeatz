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
    <h2>Add A New Admin</h2>
    <form id="productForm" action = "insertAdminServlet" method ="post">
      <label for="productName">Full Name:</label>
      <input type="text"  name="fullname" required>
      
      <label for="productName">Username:</label>
      <input type="text" id="productName" name="username" required>
      
      <label for="productName">Country:</label>
      <input type="text" id="productName" name="country" required>
      
      <label for="productName">Contact Number:</label>
      <input type="text" id="productName" name="contactnumber" required>
      
      <label for="productName">Password:</label>
      <input type="text" id="productName" name="password" required>
      
       <label for="productName">Email:</label>
      <input type="text" id="productName" name="email" required>
      
      <input type="hidden" name="role" value="admin">
      
      <button type="submit">Add Product</button>
    </form>

    
  </div>

 

</body>
</html>