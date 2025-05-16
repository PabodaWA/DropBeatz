<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register - DropBeatz</title>
    <link rel="stylesheet" href="CSS/re31.css">
    <style>
        .error-message {
            color: #ff0000;
            margin-bottom: 15px;
            font-size: 14px;
            text-align: center;
        }
    </style>
</head>
<body>
    <jsp:include page="Header.jsp" />

    <div class="container">
        <div class="box form-box">
            <header>Sign Up</header>
            
            <!-- Display error message if present -->
            <% if(request.getAttribute("errorMessage") != null) { %>
                <div class="error-message">
                    <%= request.getAttribute("errorMessage") %>
                </div>
            <% } %>

            <form action="UserInsertServlet" method="post">
                <div class="field input">
                    <label for="fullname">Full Name</label>
                    <input type="text" name="fullname" id="fullname" autocomplete="off" required>
                </div>
                <div class="field input">
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username" autocomplete="off" required>
                </div>
                <div class="field input">
                    <label for="country">Country</label>
                    <input type="text" name="country" id="country" autocomplete="off" required>
                </div>
                <div class="field input">
                    <label for="contactnumber">Contact Number</label>
                    <input type="text" name="contactnumber" id="contactnumber" autocomplete="off" required>
                </div>
                <div class="field input">
                    <label for="email">Email</label>
                    <input type="email" name="email" id="email" autocomplete="off" required>
                </div>
                <div class="field input">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" autocomplete="off" required>
                </div>
                <!-- Hidden field for role with default value -->
                <input type="hidden" name="role" value="user">
                
                <div class="field">
                    <input type="submit" class="btn" style="background-color: #8B4513;" name="submit" value="Register">
                </div>
                
                <div class="links">
                    Already have an account? <a href="Signin.jsp">Sign In</a>
                </div>
            </form>
        </div>
    </div>
    
    <jsp:include page="Footer.jsp" />
</body>
</html>