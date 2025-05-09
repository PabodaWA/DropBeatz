package AdminPackage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AdminInsertServlet")
public class AdminInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
		String Admin_id = request.getParameter("Admin_id");
		String Ad_first_name = request.getParameter("Ad_first_name");
		String Ad_last_name = request.getParameter("Ad_last_name");
		String Ad_username = request.getParameter("Ad_username");
		String Ad_contry  = request.getParameter("Ad_contry ");
		String Ad_phone_no = request.getParameter("Ad_phone_no");
		String Ad_email = request.getParameter("Ad_email");
		String Ad_password = request.getParameter("Ad_password");
		
   boolean isTrue;
		
		isTrue = AdminController.insertdata(Admin_id, Ad_first_name, Ad_last_name, Ad_username, Ad_contry, Ad_phone_no, Ad_email, Ad_password);
		
		if(isTrue == true) {
		
		String alertMesage = "Data Insert Successful";
		
		response.getWriter().println("<script> alert ('"+alertMesage+"');window.location.href='Admin_AgetallServlet'</script>");
		
		}
		else {
			
			RequestDispatcher dis2 = request.getRequestDispatcher("wrong.jsp");
			dis2.forward(request, response);
		}
		
	}


}
