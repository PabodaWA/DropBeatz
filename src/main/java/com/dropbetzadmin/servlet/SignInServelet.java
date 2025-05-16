package com.dropbetzadmin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dropbetzadmin.control.UserController;
import com.dropbeatzadmin.model.UserModel;


@WebServlet("/SignInServelet")
public class SignInServelet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
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

                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", role);
                // Store user details in session for easy access
                session.setAttribute("currentUser", user);

                if ("admin".equalsIgnoreCase(role)) {
                    // Redirect to AdminProfileServlet to display admin profile
                    response.sendRedirect("admindashboardjsp.jsp");
                } else if ("artist".equalsIgnoreCase(role)) {
                    RequestDispatcher dis = request.getRequestDispatcher("ArtistDashBoard.jsp");
                    dis.forward(request, response);
                } else {
                    RequestDispatcher dis = request.getRequestDispatcher("profile.jsp");
                    dis.forward(request, response);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("Signin.jsp");
        }
    }
}