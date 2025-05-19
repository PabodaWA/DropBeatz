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


@WebServlet("/AdminGetAllServlet")
public class AdminGetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//Polymorphism used
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		AdminController adminService = new AdminController(); // Create an object

		List<AdminModel> alladmins = adminService.getAllAdmin(); // Use object method
		request.setAttribute("alladmins", alladmins);

		RequestDispatcher dispatcher = request.getRequestDispatcher("admindashboardjsp.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}