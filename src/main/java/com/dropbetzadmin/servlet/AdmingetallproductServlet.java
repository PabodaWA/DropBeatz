package com.dropbetzadmin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dropbetzadmin.control.AdminProductController;
import com.dropbeatzadmin.model.AdminProductModel;

@WebServlet("/AdmingetallproductServlet")
public class AdmingetallproductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   @Override    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}
   @Override  
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<AdminProductModel> allProducts = AdminProductController.getAllproduct();
		request.setAttribute("allProducts", allProducts);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Adminproduct.jsp");
		dispatcher.forward(request, response);
		
	


		

		
	}  
	
	



}