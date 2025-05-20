<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel ="stylesheet" href="CSS/login8.css"><link>
</head>
<body>
<jsp:include page="Header.jsp" />

	<div class="container">
        <div class="box form-box">
            <header>Login</header>
            <form action="LoginServlet" method="post">
                <div class="field input">
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username" required>
                </div>
                <div class="field input">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password" required>
                </div>
                <div class="field">
                    <input type="submit" class="btn" name="submit" value="Login" required>
                </div>
                 <div class="link">
                    Don't have an account? <a href="Register.jsp">Sing Up</a>
                </div> 
                <div class="link">
                    Are you an Admin Or an Artist ? <a href="Signin.jsp">Sing In</a>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="Footer.jsp" />
</body>
</html>