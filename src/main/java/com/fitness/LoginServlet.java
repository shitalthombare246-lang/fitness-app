package com.fitness;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // ✅ GET method (test साठी)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.getWriter().println("<h2>Login Servlet is Working 👍</h2>");
    }

    // ✅ POST method (actual login)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        try {
            // 🔹 Input values
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // ❗ null check (important)
            if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                response.getWriter().println("<h3>Please enter email and password</h3>");
                return;
            }

            // 🔐 SAME hashing as register
            password = String.valueOf(password.hashCode());

            // 🔌 DB connection
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fitnessdb",
                "root",
                "wellcome123"
            );

            // 🔍 Query
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=?"
            );

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // ✅ Session create
                HttpSession session = request.getSession();
                session.setAttribute("user", email);

                // ✅ Redirect
                response.sendRedirect("dashboard.jsp");

            } else {
                response.getWriter().println("<h2 style='color:red;'>Invalid Email or Password ❌</h2>");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}