package UserPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
			
	    try {
	        List<UserModel> userlogin = UserController.loginValidate(username, password);
	        
	        if (userlogin.isEmpty()) {
	            // Login failed
	            request.setAttribute("errorMessage", "Invalid username or password");
	            RequestDispatcher dis = request.getRequestDispatcher("Signin.jsp");
	            dis.forward(request, response);
	        } else {
	            // Login successful
	            request.setAttribute("userlogin", userlogin);
	            RequestDispatcher dis = request.getRequestDispatcher("Home.jsp");
	            dis.forward(request, response);
	        }
					
	    } catch(Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("Signin.jsp");
	    }
	}

}
