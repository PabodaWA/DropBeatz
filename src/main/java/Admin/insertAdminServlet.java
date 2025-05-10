package Admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/insertAdminServlet")
public class insertAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
		String fullname = request.getParameter("fullname");
		String username = request.getParameter("username");
		String country = request.getParameter("country");
		String contactnumber = request.getParameter("contactnumber");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String role = request.getParameter("role");
		
		//Validation
		
		if (AdminController.isUsernameTaken(username)) {
			response.getWriter().println("<script>alert('Username already exists!'); window.history.back();</script>");
			return;
		}

		if (password.length() < 8) {
			response.getWriter().println("<script>alert('Password must be at least 8 characters long!'); window.history.back();</script>");
			return;
		}

		if (!email.contains("@")) {
			response.getWriter().println("<script>alert('Invalid email! Must contain @'); window.history.back();</script>");
			return;
		}
		
   boolean isTrue;
		
		isTrue = AdminController.insertdata(fullname, username, country, contactnumber, password, email, role);
		
		if(isTrue == true) {
		
		String alertMesage = "Data Insert Successful";
		
		response.getWriter().println("<script> alert ('"+alertMesage+"');window.location.href='getallAdminServlet'</script>");
		
		}
		else {
			
			RequestDispatcher dis2 = request.getRequestDispatcher("wrong.jsp");
			dis2.forward(request, response);
		}
		
	}

}
