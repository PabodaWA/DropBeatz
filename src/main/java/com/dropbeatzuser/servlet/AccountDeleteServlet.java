package com.dropbeatzuser.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dropbeatzuser.control.UserController;
import com.dropbeatzuser.model.UserModel;
import com.dropbeatzuser.util.LoggerUtil;


@WebServlet("/AccountDeleteServlet")
public class AccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private static final Logger logger = LoggerUtil.getLogger(AccountDeleteServlet.class.getName());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		logger.info("Received request to delete user with ID: " + id);logger.info("Received request to delete user  with ID: " + id);
		
		Boolean isTrue;
		isTrue = UserController.deleteAccount(id);
		if(isTrue == true) {
			
			logger.info("Task with ID " + id + " deleted successfully.");
			String alterMessage = "Account Delete Successful";
			
			response.getWriter().println("<script>alert('" + alterMessage + "'); window.location.href='loging.jsp';</script>");

		}
		else {
			List<UserModel> userDetails = UserController.getById(id);
			request.setAttribute("userDetails", userDetails);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("wrong.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}

}
