package com.dropbetzadmin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dropbetzadmin.control.AdminController;
import com.dropbeatzadmin.model.AdminModel;

@WebServlet("/Adminupdateservlet")
public class Adminupdateservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Optional: implement if needed for form pre-fill
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String id = request.getParameter("id");
	        String fullname = request.getParameter("fullname");
	        String username = request.getParameter("username");
	        String country = request.getParameter("country");
	        String contactnumber = request.getParameter("contactnumber");
	        String password = request.getParameter("password"); // Corrected parameter name
	        String email = request.getParameter("email");
	        String role = request.getParameter("role");

	        // Log parameters for debugging
	        System.out.println("Update Parameters:");
	        System.out.println("ID: " + id);
	        System.out.println("Full Name: " + fullname);
	        System.out.println("Username: " + username);
	        System.out.println("Country: " + country);
	        System.out.println("Contact: " + contactnumber);
	        System.out.println("Password: " + password);
	        System.out.println("Email: " + email);
	        System.out.println("Role: " + role);

	        AdminController adminController = new AdminController();
	        boolean isTrue = adminController.updateData(id, fullname, username, country, contactnumber, password, email, role);

	        if (isTrue) {
	            String alertMessage = "Data Update Successful";
	            response.getWriter().println("<script>alert('" + alertMessage + "'); window.location.href='AdminGetAllServlet';</script>");
	        } else {
	            // Log failure and redirect
	            System.out.println("Update failed for ID: " + id);
	            RequestDispatcher dis2 = request.getRequestDispatcher("wrong.jsp");
	            dis2.forward(request, response);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("An error occurred: " + e.getMessage());
	    }
	}
}