package com.dropbetzadmin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dropbetzadmin.control.AdminController;

@WebServlet("/AdminInsertServlet")
public class AdminInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // Get form parameters and trim whitespace
            String fullname = request.getParameter("fullname") != null ? request.getParameter("fullname").trim() : "";
            String username = request.getParameter("username") != null ? request.getParameter("username").trim() : "";
            String country = request.getParameter("country") != null ? request.getParameter("country").trim() : "";
            String contactnumber = request.getParameter("contactnumber") != null ? request.getParameter("contactnumber").trim() : "";
            String email = request.getParameter("email") != null ? request.getParameter("email").trim() : "";
            String password = request.getParameter("password") != null ? request.getParameter("password").trim() : "";
            String role = request.getParameter("role") != null ? request.getParameter("role").trim() : "admin";
            
            // Debug output
            System.out.println("==========================================");
            System.out.println("Admin Insert - Form data received:");
            System.out.println("Fullname: " + fullname);
            System.out.println("Username: " + username);
            System.out.println("Country: " + country);
            System.out.println("Contact: " + contactnumber);
            System.out.println("Email: " + email);
            System.out.println("Role: " + role);
            System.out.println("==========================================");
            
            // Server-side validation
            if (fullname.isEmpty() || username.isEmpty() || country.isEmpty() || 
                contactnumber.isEmpty() || email.isEmpty() || password.isEmpty()) {
                out.println("<script>alert('All fields are required!'); window.history.back();</script>");
                return;
            }
            
            AdminController adminService = new AdminController();
            
            // Check if username is already taken
            if (adminService.isUsernameTaken(username)) {
                out.println("<script>alert('Username already exists!'); window.history.back();</script>");
                return;
            }
            
            // Password validation
            if (password.length() < 8) {
                out.println("<script>alert('Password must be at least 8 characters long!'); window.history.back();</script>");
                return;
            }
            
            // Email validation
            if (!email.contains("@") || !email.contains(".")) {
                out.println("<script>alert('Invalid email format!'); window.history.back();</script>");
                return;
            }
            
            // Insert data into database
            boolean isTrue = adminService.insertData(fullname, username, country, contactnumber, email, password, role);
            
            if (isTrue) {
                System.out.println("Admin insert successful! Redirecting to AdminGetAllServlet");
                response.sendRedirect("AdminGetAllServlet");
            } else {
                System.out.println("Admin insert failed: Database operation returned false");
                out.println("<script>alert('Failed to insert admin data. Please try again.'); window.history.back();</script>");
            }
        } catch (Exception e) {
            System.out.println("Exception in AdminInsertServlet: " + e.getMessage());
            e.printStackTrace();
            out.println("<script>alert('An error occurred: " + e.getMessage().replace("'", "\\'") + "'); window.history.back();</script>");
        } finally {
            out.close();
        }
    }
}