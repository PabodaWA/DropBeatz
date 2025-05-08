<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <link rel="stylesheet" href="CSS/SignIn.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>

            <% if(request.getAttribute("message") != null) { %>
                <div class="alert error">
                    <%= request.getAttribute("message") %>
                </div>
            <% } %>
    <div class="container">
        <div class="login-card">
            <!-- Left Side: Login Form -->
            <div class="login-content">
                <div class="logo">
                    <span class="logo-icon"><i class="fas fa-music"></i></span>
                    <div class="logo-text">
                        <span class="brand">DropBeatz</span>
                        <span class="slogan">Online Music Store</span>
                    </div>
                </div>
                <h1>Log in</h1>
                <form id="loginForm" action="Signup" method="post">
                    <div class="input-group">
                    <label for="username">User name:</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div class="input-group">
                    <label for="password">Password:</label>
                        <input type="password" id="password" name="password" required>
                        <button type="button" class="toggle-password" onclick="togglePassword()">
                            <i id="password-icon" class="far fa-eye"></i>
                        </button>
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

            <!-- Right Side: Image -->
            <div class="banner-image"> <img src="Pictures/3.jpeg" alt="Logo">
            </div>
        </div>
    </div>

    <script>
    function togglePassword() {
        const passwordInput = document.getElementById("password");
        const passwordIcon = document.getElementById("password-icon");
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            passwordIcon.classList.remove("fa-eye");
            passwordIcon.classList.add("fa-eye-slash");
        } else {
            passwordInput.type = "password";
            passwordIcon.classList.remove("fa-eye-slash");
            passwordIcon.classList.add("fa-eye");
        }
    }
    </script>
</body>
</html>
