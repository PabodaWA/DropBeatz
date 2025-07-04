package com.dropbeatzuser.servlet;

import java.io.IOException;
import java.util.List;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dropbeatzuser.control.UserController;
import com.dropbeatzuser.model.UserModel;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	
	try {
		List<UserModel> userloging = UserController.loginValidate(username, password);
		if (userloging != null && !userloging.isEmpty()) {
		   
		    request.getSession().setAttribute("user", userloging.get(0));
		    response.sendRedirect("Songs.jsp");
		    
		} else {
		    String alertMessage = "Invalid Credentials, please try again";
		    response.getWriter().println("<script>alert('" + alertMessage + "');window.location.href='loging.jsp'</script>");
		}

	}catch(Exception e){
		e.printStackTrace();
	}
	


}}