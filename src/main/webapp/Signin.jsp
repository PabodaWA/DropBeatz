<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="./CSS/Signin.css">
 <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
<title>Sign In</title>
</head>
<body>
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
                
                <form id="loginForm">
                    <div class="input-group">
                        <input type="email" id="email" placeholder="Email" required>
                    </div>
                    
                    <div class="input-group">
                        <input type="password" id="password" placeholder="Password" required>
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
            
            <div class="banner-image">
                <!-- Image will be added via CSS -->
            </div>
        </div>
    </div>
    
    <script>
        function togglePassword() {
            const passwordInput = document.getElementById('password');
            const passwordIcon = document.getElementById('password-icon');
            
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                passwordIcon.classList.remove('fa-eye');
                passwordIcon.classList.add('fa-eye-slash');
            } else {
                passwordInput.type = 'password';
                passwordIcon.classList.remove('fa-eye-slash');
                passwordIcon.classList.add('fa-eye');
            }
        }
        
        document.getElementById('loginForm').addEventListener('submit', function(event) {
            event.preventDefault();
            
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
            
            // Here you would typically send the login data to your server
            console.log("Login attempted with:", email);
            
            // For demonstration purposes only:
            alert("Login functionality would connect to your backend here.");
        });
    </script>



</body>
</html>