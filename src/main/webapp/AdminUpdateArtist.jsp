<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./CSS/AddProduct.css">
<title>Update Artist</title>
</head>
<body>

<div class="form-container">
    <h2>Update Artist</h2>
    <form id="updateForm" action="UpdateArtistServlet" method="post">
        <input type="hidden" name="id" value="${param.id}">
        
        <label for="fullname">Full Name:</label>
        <input type="text" name="fullname" value="${param.fullname}" required>
        
        <label for="username">Username:</label>
        <input type="text" name="username" value="${param.username}" required>
        
        <label for="country">Country:</label>
        <input type="text" name="country" value="${param.country}" required>
        
        <label for="contactnumber">Contact Number:</label>
        <input type="text" name="contactnumber" value="${param.contactnumber}" required>
        
        <label for="password">Password:</label>
        <input type="text" name="password" value="${param.password}" required>
        
        <label for="email">Email:</label>
        <input type="text" name="email" value="${param.email}" required>
        
        <input type="hidden" name="role" value="${param.role}">
        
        <button type="submit">Update Artist</button>
    </form>
</div>

</body>
</html>