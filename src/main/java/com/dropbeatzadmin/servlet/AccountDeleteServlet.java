package com.dropbeatzadmin.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dropbeatzadmin.model.NewUserModel;
import com.dropbeatzadmin.control.NewUserController;

@WebServlet("/AccountDeleteServlet")
public class AccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		Boolean isTrue;
		isTrue = NewUserController.deleteAccount(id);
		if(isTrue == true) {
			String alterMessage = "Account Delete Successful";
			
			response.getWriter().println("<script>alert('" + alterMessage + "'); window.location.href='loging.jsp';</script>");

		}
		else {
			List<NewUserModel> userDetails = NewUserController.getById(id);
			request.setAttribute("userDetails", userDetails);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("wrong.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}

}
