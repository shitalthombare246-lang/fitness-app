package com.fitness;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    // ✅ Test (URL hit check)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.getWriter().println("<h2>Register Servlet is Working 👍</h2>");
    }

    // ✅ Form submit
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 🔹 Get Data
            String name = request.getParameter("name");
            String email = request.getParameter("email");

            int age = Integer.parseInt(request.getParameter("age"));
            double weight = Double.parseDouble(request.getParameter("weight"));

            String password = request.getParameter("password");
            password = String.valueOf(password.hashCode());

            // 🔌 DB Connection
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fitnessdb?useSSL=false&serverTimezone=UTC",
                "root",
                "wellcome123"
            );

            // 🔥 Insert
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name,email,password,age,weight) VALUES(?,?,?,?,?)"
            );

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, age);
            ps.setDouble(5, weight);

            ps.executeUpdate();

            con.close();

            // ✅ Success redirect
            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            e.printStackTrace();

            response.setContentType("text/html");
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}