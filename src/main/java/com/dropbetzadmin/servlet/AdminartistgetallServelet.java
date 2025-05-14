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

@WebServlet("/AdminartistgetallServelet")
public class AdminartistgetallServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 @Override      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}
 @Override     	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<AdminArtistModel> allartist = AdminArtistController.getAllArtist();
		request.setAttribute("allartist", allartist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("AdminArtist.jsp");
		dispatcher.forward(request, response);
	}

}