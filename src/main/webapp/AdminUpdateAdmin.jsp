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
	String id = request.getParameter("id");
			String fullname = request.getParameter("fullname");
			String username = request.getParameter("username");
			String country = request.getParameter("country");
			String contactnumber = request.getParameter("contactnumber");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String role = request.getParameter("role");
			%>

<div class="form-container">
    <h2>Uptade Admin Details</h2>
    <form id="productForm" action = "Adminupdateservlet" method ="post">
    
    <label for="Id">ID:</label>
    <input type="text"  name="id" value="<%= id %>" readonly>
    
      <label for="fullname">Full Name:</label>
      <input type="text"  name="fullname" value=<%= fullname %> required>
      
      <label for="username">Username:</label>
      <input type="text" name="username" value=<%= username %> required>
      
       <label for="country">Country:</label>
      <input type="text" name="country" value=<%= country %> required>
      
	 <label for="contactnumber">Contactnumber:</label>
      <input type="text" id="productDesc" name="contactnumber" value=<%= contactnumber%> required>
      
      <label for="email">Email:</label>
      <input type="text"  name="email" value=<%= email%> required>
      

      <label for="password">Password :</label>
     <input type="text" name="password" value="<%= password %>" required>

<label for="role">role:</label>
      <input type="text" id="productDesc" name="role" value=<%= role%> readonly>

      <button type="submit">Update</button>
    </form>

    
  </div>
</body>
</html>
</body>
</html>