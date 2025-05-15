package com.dropbetzadmin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dropbeatzadmin.model.NewUserModel;

@WebServlet("/profileServlet")
public class profileServlet extends HttpServlet {
private static final long serialVersionUID = 1L;


protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	processRequest(request,response);
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
}
private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Retrieve user information from session
	NewUserModel user = (NewUserModel) request.getSession().getAttribute("user");
    

    if (user != null) {
        // Attach user object to request
        request.setAttribute("user", user);
        // Forward user data to profile.jsp
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    } else {
        // No user found in session, redirect to login page
        response.sendRedirect("loging.jsp");
    }
}


}