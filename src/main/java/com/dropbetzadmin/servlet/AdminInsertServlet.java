// Fix 3: Updated AdminInsertServlet.java with improved error handling and debugging
package com.dropbetzadmin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
        // Set response content type
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // Get form parameters
            String fullname = request.getParameter("fullname");
            String username = request.getParameter("username");
            String country = request.getParameter("country");
            String contactnumber = request.getParameter("contactnumber");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            
            // Debug output - comment this out in production
            System.out.println("Admin Insert - Form data received:");
            System.out.println("Fullname: " + fullname);
            System.out.println("Username: " + username);
            System.out.println("Country: " + country);
            System.out.println("Contact: " + contactnumber);
            System.out.println("Email: " + email);
            System.out.println("Role: " + role);
            
            AdminController adminService = new AdminController();

            // Validation
            if (adminService.isUsernameTaken(username)) {
                out.println("<script>alert('Username already exists!'); window.history.back();</script>");
                return;
            }

            if (password.length() < 8) {
                out.println("<script>alert('Password must be at least 8 characters long!'); window.history.back();</script>");
                return;
            }

            if (!email.contains("@")) {
                out.println("<script>alert('Invalid email! Must contain @'); window.history.back();</script>");
                return;
            }

            // Insert data into database
            boolean isTrue = adminService.insertData(fullname, username, country, contactnumber, email, password, role);

            if (isTrue) {
                System.out.println("Admin insert successful");
                out.println("<script>alert('Data Insert Successful'); window.location.href='AdminGetAllServlet';</script>");
            } else {
                System.out.println("Admin insert failed");
                request.setAttribute("errorMessage", "Failed to insert admin data. Please try again.");
                RequestDispatcher dis2 = request.getRequestDispatcher("wrong.jsp");
                dis2.forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Exception in AdminInsertServlet: " + e.getMessage());
            e.printStackTrace();
            
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            RequestDispatcher dis = request.getRequestDispatcher("wrong.jsp");
            dis.forward(request, response);
        }
    }
}