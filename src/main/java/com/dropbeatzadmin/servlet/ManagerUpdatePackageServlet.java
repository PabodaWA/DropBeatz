package com.dropbeatzadmin.servlet;

import com.dropbeatzadmin.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/ManagerUpdatePackage")
public class ManagerUpdatePackageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection con = DBConnection.getConnection()) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            double discount = Double.parseDouble(request.getParameter("discount"));

            String sql = "UPDATE packages SET name = ?, price = ?, discount = ? WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setDouble(2, price);
                ps.setDouble(3, discount);
                ps.setInt(4, id);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    response.sendRedirect("managerPackages?message=Package updated successfully!");
                } else {
                    response.sendRedirect("managerPackages?error=Failed to update package.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("managerPackages?error=Failed to update package: " + e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("managerPackages?error=Invalid input: " + e.getMessage());
        }
    }
}