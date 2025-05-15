<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="">
    <title>Add Admin</title>
    <style>
        /* Basic styling if CSS file is missing */
        .form-container {
            width: 80%;
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        
        form {
            display: flex;
            flex-direction: column;
        }
        
        label {
            margin-top: 10px;
            margin-bottom: 5px;
            font-weight: bold;
        }
        
        input {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        
        button {
            padding: 12px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
        }
        
        button:hover {
            background-color: #45a049;
        }
        
        .error {
            color: red;
            font-size: 14px;
            margin-top: -10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Add A New Admin</h2>
        <form id="adminForm" action="AdminInsertServlet" method="post" onsubmit="return validateForm()">
            <label for="fullname">Full Name:</label>
            <input type="text" id="fullname" name="fullname" required>
            <span id="fullnameError" class="error"></span>
            
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <span id="usernameError" class="error"></span>
            
            <label for="country">Country:</label>
            <input type="text" id="country" name="country" required>
            <span id="countryError" class="error"></span>
            
            <label for="contactnumber">Contact Number:</label>
            <input type="text" id="contactnumber" name="contactnumber" required>
            <span id="contactError" class="error"></span>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
            <span id="emailError" class="error"></span>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <span id="passwordError" class="error"></span>
            
            <input type="hidden" name="role" value="admin">
            
            <button type="submit">Add Admin</button>
        </form>
    </div>

    <script>
        function validateForm() {
            let isValid = true;
            
            // Reset all error messages
            document.querySelectorAll('.error').forEach(elem => {
                elem.textContent = '';
            });
            
            // Validate fullname
            const fullname = document.getElementById('fullname').value.trim();
            if (fullname === '') {
                document.getElementById('fullnameError').textContent = 'Full name is required';
                isValid = false;
            }
            
            // Validate username
            const username = document.getElementById('username').value.trim();
            if (username === '') {
                document.getElementById('usernameError').textContent = 'Username is required';
                isValid = false;
            } else if (username.length < 3) {
                document.getElementById('usernameError').textContent = 'Username must be at least 3 characters';
                isValid = false;
            }
            
            // Validate country
            const country = document.getElementById('country').value.trim();
            if (country === '') {
                document.getElementById('countryError').textContent = 'Country is required';
                isValid = false;
            }
            
            // Validate contact number
            const contact = document.getElementById('contactnumber').value.trim();
            if (contact === '') {
                document.getElementById('contactError').textContent = 'Contact number is required';
                isValid = false;
            }
            
            // Validate email
            const email = document.getElementById('email').value.trim();
            if (email === '') {
                document.getElementById('emailError').textContent = 'Email is required';
                isValid = false;
            } else if (!email.includes('@') || !email.includes('.')) {
                document.getElementById('emailError').textContent = 'Invalid email format';
                isValid = false;
            }
            
            // Validate password
            const password = document.getElementById('password').value.trim();
            if (password === '') {
                document.getElementById('passwordError').textContent = 'Password is required';
                isValid = false;
            } else if (password.length < 8) {
                document.getElementById('passwordError').textContent = 'Password must be at least 8 characters';
                isValid = false;
            }
            
            return isValid;
        }
    </script>
</body>
</html>