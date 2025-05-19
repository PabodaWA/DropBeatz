package musicStore;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/InsertServlet")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String fullname =request.getParameter("fullname");
		String username =request.getParameter("username");
		String country =request.getParameter("country");
		String contactnumber =request.getParameter("contactnumber");
		String email =request.getParameter("email");
		String password =request.getParameter("password");
		
		boolean isTrue;
		
		isTrue = registerController.insertdata(fullname, username, country, contactnumber, email, password);
		
		if(isTrue == true) {
			String alertMessage = "Data Insert Successful";
			response.getWriter().println("<script> alert('"+alertMessage+"');window.location.href='GetAllServelet'</script>");
		}
		else {
			RequestDispatcher dis2 =request.getRequestDispatcher("wrong.jsp");
			dis2.forward(request, response);
		}
	}

}
