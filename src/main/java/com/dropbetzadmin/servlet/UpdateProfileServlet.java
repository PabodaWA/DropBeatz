package com.dropbetzadmin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
private static final long serialVersionUID = 1L;


protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String id =request.getParameter("id");
	String fullname =request.getParameter("fullname");
	String username =request.getParameter("username");
	String country =request.getParameter("country");
	String contactnumber =request.getParameter("contactnumber");
	String email =request.getParameter("email");
	String password =request.getParameter("password");
	


boolean isTrue;


	isTrue = NewUserModel.updateprofile(id, fullname, username, country, contactnumber, email, password);
	
	if (isTrue == true) {
        // After successful update, update the user object in the session
        List<UserModel> userDetails = UserController.getById(id);
        if (userDetails.size() > 0) {
            UserModel updatedUser = userDetails.get(0);
            request.getSession().setAttribute("user", updatedUser);  // Update session attribute

            String alertMessage = "Data update Successful";
            response.getWriter().println("<script> alert('" + alertMessage + "');window.location.href='profileServlet'</script>");
        }
	else {
		RequestDispatcher dis2 =request.getRequestDispatcher("wrong.jsp");
		dis2.forward(request, response);
	}
}



}
}