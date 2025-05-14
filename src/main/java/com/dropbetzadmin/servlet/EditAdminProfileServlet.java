package com.dropbetzadmin.servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import com.dropbetzadmin.control.UserController;
import com.dropbeatzadmin.model.UserModel;

@WebServlet("/EditAdminProfileServlet")
public class EditAdminProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Display the edit form with current admin details
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the username from session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        
        // Check if user is logged in and is an admin
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
                
                // Forward to edit admin profile page
                RequestDispatcher dispatcher = request.getRequestDispatcher("EditAdminProfile.jsp");
                dispatcher.forward(request, response);
            } else {
                // If admin user not found, redirect to login
                session.invalidate();
                response.sendRedirect("Signin.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error retrieving profile data");
            response.sendRedirect("Signin.jsp");
        }
    }
    
    // Process the edit form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the username from session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        
        // Check if user is logged in and is an admin
        if (username == null || !("admin".equalsIgnoreCase(role))) {
            response.sendRedirect("Signin.jsp");
            return;
        }
        
        // Get the form data
        String fullname = request.getParameter("fullname");
        String country = request.getParameter("country");
        String contactnumber = request.getParameter("contactnumber");
        String email = request.getParameter("email");
        
        // Update the user details in the database
        boolean updated = UserController.updateUserProfile(username, fullname, country, contactnumber, email);
        
        if (updated) {
            // Redirect to admin profile page with success message
            session.setAttribute("successMessage", "Profile updated successfully");
            response.sendRedirect("AdminProfileServlet");
        } else {
            // Redirect back to edit form with error message
            session.setAttribute("errorMessage", "Failed to update profile");
            response.sendRedirect("EditAdminProfileServlet");
        }
    }
}