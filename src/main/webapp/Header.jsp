<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
    body {
      margin: 0;
      font-family: Arial, sans-serif;
    }

    header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      background-color: #333;
      padding: 10px 20px;
      color: white;
    }

    .logo img {
      height: 40px;
    }

    nav a {
      color: white;
      text-decoration: none;
      margin: 0 15px;
      font-size: 16px;
    }

    nav a:hover {
      text-decoration: underline;
    }

    .signin-button button {
      padding: 8px 16px;
      background-color: #007bff;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }

    .signin-button button:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<header>
  <!-- Left Logo -->
  <div class="logo">
    <img src="Pictures/logo.png" alt="Logo">
  </div>

  <!-- Center Navigation -->
  <nav>
    <a href="home.html">Home</a>
    <a href="about.html">About Us</a>
    <a href="contact.html">Contact Us</a>
    <a href="products.html">Product</a>
  </nav>

  <!-- Right Sign In -->
  <div class="signin-button">
    <form action="signin" method="post">
      <button type="submit">Sign In</button>
    </form>
  </div>
</header>


</body>
</html>