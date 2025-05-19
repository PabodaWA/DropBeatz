<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Your Profile Details</h2>
 	<table>
 		<tr>
 			<th>ID</th>
 			<th>Full Name</th>
 			<th>UserName</th>
 			<th>country</th>
 			<th>contact Number</th>
 			<th>email</th>
 			<th>password</th>
 			<th>Action</th>
 			
 		</tr>
 		<c:forEach var="register" items="${allregister}">
 		<tr>
 			<td>${register.id}</td>
 			<td>${register.fullname}</td>
 			<td>${register.username}</td>
 			<td>${register.country}</td>
 			<td>${register.contactnumber}</td>
 			<td>${register.email}</td>
 			<td>${register.password}</td>
 				
 			<td>
 				<a href="Update.jsp?id=${register.id}&fullname=${register.fullname}&username=${register.username}
 				&country=${register.country}&contactnumber=${register.contactnumber}&email=${register.email}
 				&password=${register.password}">
 				<button>Update</button>
 				</a>
 			</td>
 		</tr>
 		</c:forEach>
 		
 	</table>

</body>
</html>