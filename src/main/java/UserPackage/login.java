package UserPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    try {
	        List<UserModel> userlogin = UserController.loginValidate(username, password);

	        if (userlogin.isEmpty()) {
	            request.setAttribute("errorMessage", "Invalid username or password");
	            RequestDispatcher dis = request.getRequestDispatcher("Signin.jsp");
	            dis.forward(request, response);
	        } else {
	            UserModel user = userlogin.get(0);
	            String role = user.getRole();

	            // ✅ Create session and store user info before forwarding
	            HttpSession session = request.getSession();
	            session.setAttribute("username", user.getUsername());
	            session.setAttribute("role", role);

	            // ✅ Forward based on role
	            if ("admin".equalsIgnoreCase(role)) {
	                RequestDispatcher dis = request.getRequestDispatcher("admindashboardjsp.jsp");
	                dis.forward(request, response);
	            } else if ("artist".equalsIgnoreCase(role)) {
	                RequestDispatcher dis = request.getRequestDispatcher("ArtistDashBoard.jsp");
	                dis.forward(request, response);
	            } else {
	                RequestDispatcher dis = request.getRequestDispatcher("Home.jsp");
	                dis.forward(request, response);
	            }
	        }

	    } catch(Exception e) {
	        e.printStackTrace();
	        response.sendRedirect("Signin.jsp");
	    }
	}
}
