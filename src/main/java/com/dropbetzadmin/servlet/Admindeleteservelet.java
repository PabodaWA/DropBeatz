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

@WebServlet("/Admindeleteservelet")
public class Admindeleteservelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");

		// ✅ Create AdminController object
		AdminController adminController = new AdminController();

		boolean isTrue = adminController.deleteData(id);  // ✅ non-static call

		if (isTrue) {
			String alertMessage = "Data Delete Successful";
			response.getWriter().println("<script>alert('" + alertMessage + "');" + 
			                             "window.location.href='AdminGetAllServlet';</script>");
		} else {
			List<AdminModel> admindetails = adminController.getById(id);  // ✅ non-static call
			request.setAttribute("admindetails", admindetails);

			RequestDispatcher dispatcher = request.getRequestDispatcher("wrong.jsp");
			dispatcher.forward(request, response);
		}
	}
}