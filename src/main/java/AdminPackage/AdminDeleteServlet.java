package AdminPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminDeleteServlet")
public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String Admin_id = request.getParameter("Admin_id");
		boolean isTrue;
		isTrue = AdminController.deletedata(Admin_id);
		
		 if(isTrue == true) {
	            String alertMessage = "Data Delete Successful";
	            response.getWriter().println("<script>alert('" + alertMessage + "');" + 
	                                        "window.location.href='Admin_AgetallServlet';</script>");
	        } else {
	            List<AdminModel> admindetails = AdminController.getById(Admin_id);
	            request.setAttribute("admindetails", admindetails);
	            
	            RequestDispatcher dispatcher = request.getRequestDispatcher("wrong.jsp");
	            dispatcher.forward(request, response);
		}
		
	}

}
