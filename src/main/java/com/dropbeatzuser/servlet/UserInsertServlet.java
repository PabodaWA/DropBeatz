package com.dropbeatzuser.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dropbeatzuser.control.UserController;
import com.dropbeatzuser.util.NewUserValidation;

@WebServlet("/UserInsertServlet")
public class UserInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// HANDLE POST (Form submission)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("username");
		String country = request.getParameter("country");
		String contactnumber = request.getParameter("contactnumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//Validate phone number and email
		if(!NewUserValidation.validatePhoneNumber(contactnumber)) {
        	response.getWriter().println("<script>alert('Phone number must be 10 digits.'); window.history.back();</script>");
        	return;
        }
		
		if(!NewUserValidation.isValidEmail(email)) {
        	response.getWriter().println("<script>alert('Invalid Email Address.'); window.history.back();</script>");
        	return;
        }
		
		
		boolean isTrue = UserController.insertdata(fullname, username, country, contactnumber, email, password);

		
		if (isTrue == true) {
			String alertMessage = "Register Successful";
			response.getWriter().println("<script> alert('" + alertMessage + "');window.location.href='loging.jsp'</script>");//loging.jsp
		} else {
			RequestDispatcher dis2 = request.getRequestDispatcher("wrong.jsp");
			dis2.forward(request, response);
		}
	}

	
}
