<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="SignUpPackage.Artist" %> 
    <%
    // Check if artist is logged in
    Artist artist = null;
    try {
        artist = (Artist) session.getAttribute("user");
        if (artist == null || !"artist".equals(artist.getUserType())) {
            response.sendRedirect("login.jsp");
            return;
        }
    } catch (ClassCastException e) {
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

</body>
</html>