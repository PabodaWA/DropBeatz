<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/re31.css">
</head>
<body>
<jsp:include page="Header.jsp" />

	    <div class="container">
        <div class="box form-box">

 <header>Sign Up</header>

            <form action="UserInsertServlet" method="post">
                <div class="field input">
                    <label for="fullname">Full Name</label>
                    <input type="text" name="fullname" id="fullname" autocapitalize="off" required>
                </div>
                <div class="field input">
                    <label for="username">User name</label>
                    <input type="text" name="username" id="username" autocapitalize="off" required>
                </div>
                <div class="field input">
                    <label for="country">Country</label>
                    <input type="text" name="country" id="country" autocapitalize="off" required>
                </div>
                <div class="field input">
                    <label for="contactnumber">Contact Number</label>
                    <input type="text" name="contactnumber" id="contactnumber" autocapitalize="off" required>
                </div>
                <div class="field input">
                    <label for="email">Email</label>
                    <input type="text" name="email" id="email" autocapitalize="off" required>
                </div>
                <div class="field input">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password" autocapitalize="off" required>
                </div>
                <div class="field">
                    <input type="submit" class="btn" style="background-color: #8B4513;" name="submit" value="Register" required>
                </div>
                
            </form>
        </div>
        
    </div>
<jsp:include page="Footer.jsp" />

</body>
</html>