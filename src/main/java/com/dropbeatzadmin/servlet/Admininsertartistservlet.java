package com.dropbeatzadmin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dropbeatzadmin.control.AdminArtistController;

@WebServlet("/Admininsertartistservlet")
public class Admininsertartistservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get parameters
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String country = request.getParameter("country");
            String contactnumber = request.getParameter("contactnumber");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String role = request.getParameter("role");
            
            // Validation
            if (fullname == null || fullname.trim().isEmpty() ||
                username == null || username.trim().isEmpty() ||
                country == null || country.trim().isEmpty() ||
                contactnumber == null || contactnumber.trim().isEmpty() ||
                password == null || password.trim().isEmpty() ||
                email == null || email.trim().isEmpty() ||
                role == null || role.trim().isEmpty()) {
                
                response.getWriter().println("<script>alert('All fields are required!'); window.history.back();</script>");
                return;
            }
            
            if (AdminArtistController.isUsernameTaken(username)) {
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
            
            // Try to insert data
            boolean isSuccess = AdminArtistController.insertdata(fullname, username, country, contactnumber, password, email, role);
            
            if (isSuccess) {
                String alertMessage = "Data Insert Successful";
                response.getWriter().println("<script>alert('" + alertMessage + "'); window.location.href='AdminartistgetallServelet';</script>");
            } else {
                // Handle database insertion failure without redirecting to wrong.jsp
                String errorMessage = "Failed to insert data. Please try again.";
                response.getWriter().println("<script>alert('" + errorMessage + "'); window.history.back();</script>");
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            
            // Handle unexpected errors without redirecting to wrong.jsp
            String errorMessage = "An unexpected error occurred. Please try again.";
            response.getWriter().println("<script>alert('" + errorMessage + "'); window.history.back();</script>");
        }
    }
}