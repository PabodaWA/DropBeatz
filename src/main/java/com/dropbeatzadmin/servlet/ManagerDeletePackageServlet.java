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

@WebServlet("/ManagerDeletePackage")
public class ManagerDeletePackageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection con = DBConnection.getConnection()) {
            int id = Integer.parseInt(request.getParameter("id"));

            String sql = "DELETE FROM packages WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    response.sendRedirect("managerPackages?message=Package deleted successfully!");
                } else {
                    response.sendRedirect("managerPackages?error=Failed to delete package.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("managerPackages?error=Failed to delete package: " + e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("managerPackages?error=Invalid input: " + e.getMessage());
        }
    }
}