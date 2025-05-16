package com.dropbeatzadmin.servlet;

import com.dropbeatzadmin.util.DBConnection;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/ManagerInsertPackage")
public class ManagerInsertPackageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ManagerInsertPackageServlet: doPost called");

        try (Connection con = DBConnection.getConnection()) {
            System.out.println("DB connection established: " + (con != null));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            double discount = Double.parseDouble(request.getParameter("discount"));
            System.out.println("Received params: name=" + name + ", price=" + price + ", discount=" + discount);

            String sql = "INSERT INTO packages (name, price, discount) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setDouble(2, price);
                ps.setDouble(3, discount);
                int rowsAffected = ps.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);

                if (rowsAffected > 0) {
                    response.sendRedirect("managerPackages?message=Package added successfully!");
                } else {
                    response.sendRedirect("managerPackages?error=Failed to add package.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("managerPackages?error=Failed to add package: " + e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("managerPackages?error=Invalid input: " + e.getMessage());
        }
    }
}