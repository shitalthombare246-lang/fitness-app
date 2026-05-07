package com.fitness;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/save")
public class SaveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("user");

            int steps = Integer.parseInt(request.getParameter("steps"));
            double calories = Double.parseDouble(request.getParameter("calories"));
            double distance = Double.parseDouble(request.getParameter("distance"));

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fitnessdb", "root", "wellcome123");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO fitness_data(email, steps, calories, distance) VALUES(?,?,?,?)");

            ps.setString(1, email);
            ps.setInt(2, steps);
            ps.setDouble(3, calories);
            ps.setDouble(4, distance);

            ps.executeUpdate();

            response.sendRedirect("dashboard.jsp");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}