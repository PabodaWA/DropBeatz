package musicStore;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id =request.getParameter("id");
		String fullname =request.getParameter("fullname");
		String username =request.getParameter("username");
		String country =request.getParameter("country");
		String contactnumber =request.getParameter("contactnumber");
		String email =request.getParameter("email");
		String password =request.getParameter("password");
		
		boolean isTrue;
		
		isTrue = registerController.insertdata(fullname, username, country, contactnumber, email, password);
		
		if(isTrue == true) {
			List<registerModel> registerDetails = registerController.getById(id);
			request.setAttribute("registerDetails", registerDetails);
			
			String alertMessage = "Data update Successful";
			response.getWriter().println("<script> alert('"+alertMessage+"');window.location.href='UpdateServlet'</script>");
		}
		else {
			RequestDispatcher dis2 =request.getRequestDispatcher("wrong.jsp");
			dis2.forward(request, response);
		}
	}

}
