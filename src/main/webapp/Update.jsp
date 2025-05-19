<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("username");
		String country = request.getParameter("country");
		String contactnumber = request.getParameter("contactnumber");
		String email = request.getParameter("email");
		String password= request.getParameter("password");
		
	%>
	
	<form action="UpdateServlet" method="post">
	
				<div class="field input">
                    <label for="id">ID</label>
                    <input type="text" name="id" id="id" autocapitalize="off" value="<%=id%>" readonly>
                </div>
                <div class="field input">
                    <label for="fullname">Full Name</label>
                    <input type="text" name="fullname" id="fullname" autocapitalize="off" value="<%=fullname%>" required>
                </div>
                <div class="field input">
                    <label for="username">User name</label>
                    <input type="text" name="username" id="username" autocapitalize="off" value="<%=username%>" required>
                </div>
                <div class="field input">
                    <label for="country">Country</label>
                    <input type="text" name="country" id="country" autocapitalize="off" value="<%=country%>" required>
                </div>
                <div class="field input">
                    <label for="contactnumber">Contact Number</label>
                    <input type="text" name="contactnumber" id="contactnumber" autocapitalize="off" value="<%=contactnumber%>"  required>
                </div>
                <div class="field input">
                    <label for="email">Email</label>
                    <input type="text" name="email" id="email" autocapitalize="off" value="<%=email%>" required>
                </div>
                <div class="field input">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password" autocapitalize="off" value="<%=password%>"  required>
                </div>
                <div class="field">
                    <input type="submit" class="btn" name="submit" value="Update" required>
                </div>
                
            </form>
</body>
</html>