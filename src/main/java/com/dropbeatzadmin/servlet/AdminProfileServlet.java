package com.dropbeatzadmin.servlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.dropbeatzadmin.control.UserController;
import com.dropbeatzadmin.model.UserModel;

@WebServlet("/AdminProfileServlet")
public class AdminProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Check if user is logged in and is an admin
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        
        if (username == null || !("admin".equalsIgnoreCase(role))) {
            response.sendRedirect("Signin.jsp");
            return;
        }
        
        try {
            // Get user data using the username from session
            List<UserModel> userList = UserController.loginValidate(username, null);
            
            if (!userList.isEmpty()) {
                UserModel adminUser = userList.get(0);
                // Set user data as request attribute
                request.setAttribute("adminUser", adminUser);
                
                // Forward to the profile JSP
                // The JSP file name seems to be different than what you're using
                // Change from "adminprofile.jsp" to match your actual JSP file name
                // Forward to the correct JSP file name
                request.getRequestDispatcher("/adminprofile.jsp").forward(request, response);
            } else {
                // If admin user not found, redirect to login
                session.invalidate();
                response.sendRedirect("Signin.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error retrieving profile data");
            response.sendRedirect("admindashboardjsp.jsp");
        }
    }
}