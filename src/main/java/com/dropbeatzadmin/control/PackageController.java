package com.dropbeatzadmin.control;

import com.dropbeatzadmin.model.Package;
import com.dropbeatzadmin.util.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PackageController
 */
@WebServlet("/PackageController")
public class PackageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PackageController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Package> packages = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM packages";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Package pkg = new Package(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getDouble("discount")
                        );
                        packages.add(pkg);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load packages: " + e.getMessage());
        }

        request.setAttribute("packages", packages);
        request.getRequestDispatcher("/WEB-INF/packages.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try (Connection con = DBConnection.getConnection()) {
            if ("create".equals(action)) {
                String name = request.getParameter("name");
                double price = Double.parseDouble(request.getParameter("price"));
                double discount = Double.parseDouble(request.getParameter("discount"));

                String sql = "INSERT INTO packages (name, price, discount) VALUES (?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, name);
                    ps.setDouble(2, price);
                    ps.setDouble(3, discount);
                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        request.setAttribute("message", "Package added successfully!");
                    } else {
                        request.setAttribute("error", "Failed to add package.");
                    }
                }
            } else if ("update".equals(action)) {
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
                        request.setAttribute("message", "Package updated successfully!");
                    } else {
                        request.setAttribute("error", "Failed to update package.");
                    }
                }
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));

                String sql = "DELETE FROM packages WHERE id = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    int rowsAffected = ps.executeUpdate();

                    if (rowsAffected > 0) {
                        request.setAttribute("message", "Package deleted successfully!");
                    } else {
                        request.setAttribute("error", "Failed to delete package.");
                    }
                }
            } else {
                request.setAttribute("error", "Invalid action specified.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Invalid input format: " + e.getMessage());
        }

        doGet(request, response);
    }
}