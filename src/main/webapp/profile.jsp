<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/up15.css">
</head>
<body>
<jsp:include page="Header.jsp" />
<div class="user-profile">
<h1>User Profile</h1>
	<!--<p><strong>ID:</strong> ${user.id}</p>
	<p><strong>Full Name:</strong> ${user.fullname}</p>
	<p><strong>Username:</strong> ${user.username}</p>
	<p><strong>Country:</strong> ${user.country}</p>
	<p><strong>Contact Number:</strong> ${user.contactnumber}</p>
	<p><strong>Email:</strong> ${user.email}</p>
	<p><strong>Password:</strong> ${user.password}</p>-->
<table class="profile-table">
        <tr>
            <th>ID</th>
            <td>${user.id}</td>
        </tr>
        <tr>
            <th>Full Name</th>
            <td>${user.fullname}</td>
        </tr>
        <tr>
            <th>Username</th>
            <td>${user.username}</td>
        </tr>
        <tr>
            <th>Country</th>
            <td>${user.country}</td>
        </tr>
        <tr>
            <th>Contact Number</th>
            <td>${user.contactnumber}</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>${user.email}</td>
        </tr>
        <tr>
            <th>Password</th>
            <td>${user.password}</td>
        </tr>
    </table>

	<div class="user-buttons">
	<a href="Updateprofile.jsp?id=${user.id}&fullname=${user.fullname}&username=${user.username}
 				&country=${user.country}&contactnumber=${user.contactnumber}&email=${user.email}
 				&password=${user.password}">
 				<button>Update</button>
 				</a>
 				
 	<form action="AccountDeleteServlet" method="post">
 		<input type="hidden" name="id" value="${user.id}">
 		<button>Delete</button>
 	</form>
 	</div>
 	</div>
<jsp:include page="Footer.jsp" />
</body>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/up15.css">
</head>
<body>
<jsp:include page="Header.jsp" />
<div class="user-profile">
<h1>User Profile</h1>
	<!--<p><strong>ID:</strong> ${user.id}</p>
	<p><strong>Full Name:</strong> ${user.fullname}</p>
	<p><strong>Username:</strong> ${user.username}</p>
	<p><strong>Country:</strong> ${user.country}</p>
	<p><strong>Contact Number:</strong> ${user.contactnumber}</p>
	<p><strong>Email:</strong> ${user.email}</p>
	<p><strong>Password:</strong> ${user.password}</p>-->
<table class="profile-table">
        <tr>
            <th>ID</th>
            <td>${user.id}</td>
        </tr>
        <tr>
            <th>Full Name</th>
            <td>${user.fullname}</td>
        </tr>
        <tr>
            <th>Username</th>
            <td>${user.username}</td>
        </tr>
        <tr>
            <th>Country</th>
            <td>${user.country}</td>
        </tr>
        <tr>
            <th>Contact Number</th>
            <td>${user.contactnumber}</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>${user.email}</td>
        </tr>
        <tr>
            <th>Password</th>
            <td>${user.password}</td>
        </tr>
    </table>

	<div class="user-buttons">
	<a href="Updateprofile.jsp?id=${user.id}&fullname=${user.fullname}&username=${user.username}
 				&country=${user.country}&contactnumber=${user.contactnumber}&email=${user.email}
 				&password=${user.password}">
 				<button>Update</button>
 				</a>
 				
 	<form action="AccountDeleteServlet" method="post">
 		<input type="hidden" name="id" value="${user.id}">
 		<button>Delete</button>
 	</form>
 	</div>
 	</div>
<jsp:include page="Footer.jsp" />
</body>
>>>>>>> f74bc47d24e4171d27841acc3732393c3af03d84
</html>