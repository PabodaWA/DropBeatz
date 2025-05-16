package com.dropbeatzadmin.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.dropbeatzadmin.control.NewUserController;

@WebServlet("/UserInsertServlet")
public class UserInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * Handle POST request for user registration
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve form data
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String country = request.getParameter("country");
        String contactnumber = request.getParameter("contactnumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        // If role is not specified, set default role to "user"
        if (role == null || role.isEmpty()) {
            role = "user";
        }
        
        // Basic validation
        if (fullname == null || username == null || country == null || 
            contactnumber == null || email == null || password == null ||
            fullname.isEmpty() || username.isEmpty() || country.isEmpty() || 
            contactnumber.isEmpty() || email.isEmpty() || password.isEmpty()) {
            
            // Send error message back to the registration page
            request.setAttribute("errorMessage", "All fields are required!");
            RequestDispatcher dis = request.getRequestDispatcher("register.jsp");
            dis.forward(request, response);
            return;
        }
        
        // Process form data by calling the controller
        String result = NewUserController.insertdata(fullname, username, country, contactnumber, email, password, role);
        
        if (result.equals("success")) {
            // Registration successful - show alert and redirect to login page
            String alertMessage = "Registration successful! Please log in.";
            response.setContentType("text/html");
            response.getWriter().println("<script type='text/javascript'>");
            response.getWriter().println("alert('" + alertMessage + "');");
            response.getWriter().println("window.location.href='Signin.jsp';");
            response.getWriter().println("</script>");
        } else {
            // Registration failed - return to registration page with error message
            request.setAttribute("errorMessage", result);
            RequestDispatcher dis = request.getRequestDispatcher("register.jsp");
            dis.forward(request, response);
        }
    }
}