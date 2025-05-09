package AdminPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Admin_AgetallServlet")
public class Admin_AgetallServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<AdminModel> alladmin = AdminController.getAlladmi();
		request.setAttribute("alladmin", alladmin);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Admin.jsp");
		dispatcher.forward(request, response);
		
	
}

}
