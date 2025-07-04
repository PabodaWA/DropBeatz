package com.dropbetzadmin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dropbeatzadmin.model.AdminArtistModel;
import com.dropbetzadmin.control.AdminArtistController;


@WebServlet("/AdminDeletArtistServelet")
public class AdminDeletArtistServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		boolean isTrue;
		isTrue = AdminArtistController.deletedata(id);
		
		 if(isTrue == true) {
	            String alertMessage = "Data Delete Successful";
	            response.getWriter().println("<script>alert('" + alertMessage + "');" + 
	                                        "window.location.href='AdminartistgetallServelet';</script>");
	        } else {
	            List<AdminArtistModel> artistdetails = AdminArtistController.getById(id);
	            request.setAttribute("artistdetails ", artistdetails );
	            
	            RequestDispatcher dispatcher = request.getRequestDispatcher("wrong.jsp");
	            dispatcher.forward(request, response);
		}
		
	}


}
