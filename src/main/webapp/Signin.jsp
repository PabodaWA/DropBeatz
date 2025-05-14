<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="./CSS/SignIn2.css">
 <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<title>Sign In</title>
</head>
<body>

<c:if test="${not empty errorMessage}">
    <div style="color: red; text-align: center;">${errorMessage}</div>
</c:if>

 <div class="container">
        <div class="login-card">
            <div class="login-content">
                <div class="logo">
                    <span class="logo-icon"></span>
                    <div class="logo-text">
                        <span class="brand">DropBeatz</span>
                        <span class="slogan">Online Music Store</span>
                    </div>
                </div>
                
                <h1>Log in</h1>
                
                <form id="loginForm" action="SignInServelet" method="post">
    <div class="input-group">
        <input type="text" name="username" id="username" class="username" placeholder="User Name" required>
    </div>
    
    <div class="input-group">
        <input type="password" name="password" id="password" class="password" placeholder="Password" required>
    </div>

                    
                    <div class="form-options">
                        <div class="remember-me">
                            <input type="checkbox" id="keep-logged" checked>
                            <label for="keep-logged">Keep me logged in</label>
                        </div>
                        <a href="#" class="forgot-link">Forgot password?</a>
                    </div>
                    
                    <button type="submit" class="login-btn">Log in</button>
                    
                    <div class="register-link">
                        Don't have an account? <a href="#">Register</a>
                    </div>
                </form>
                
                <div class="footer-links">
                    <a href="#">Terms of Use</a> | <a href="#">Privacy Policy</a>
                </div>
            </div>
            
            <div class="banner-image">
                <!-- Image will be added via CSS -->
            </div>
        </div>
    </div>
   



</body>
</html>