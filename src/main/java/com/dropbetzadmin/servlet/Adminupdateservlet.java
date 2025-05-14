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

		String id = request.getParameter("id");
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("username");
		String country = request.getParameter("country");
		String contactnumber = request.getParameter("contactnumber");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String role = request.getParameter("role");

		//Create an object of AdminController
		AdminController adminController = new AdminController();

		boolean isTrue = adminController.updateData(id, fullname, username, country, contactnumber, password, email, role);

		if (isTrue) {
			List<AdminModel> admin = adminController.getById(id);
			request.setAttribute("admin", admin);

			String alertMesage = "Data Update Successful";
			response.getWriter().println("<script> alert('" + alertMesage + "'); window.location.href='AdminGetAllServlet'; </script>");
		} else {
			RequestDispatcher dis2 = request.getRequestDispatcher("wrong.jsp");
			dis2.forward(request, response);
		}
	}
}
