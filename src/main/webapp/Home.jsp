<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="SignUpPackage.User" %> 
    <%
    // Check if user is logged in
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="Header.jsp" />

 <div class="header">
            <h1>Welcome, <%= user.getUsername() %>!</h1>
            <a href="logout" class="btn-logout">Logout</a>
        </div>

<jsp:include page="Footer.jsp" />



</body>
</html>