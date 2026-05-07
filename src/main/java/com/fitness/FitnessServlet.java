package com.fitness;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/calculate")
public class FitnessServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("user");

            int steps = Integer.parseInt(request.getParameter("steps"));

            int points = 0;
            float discount = 0;

            // 🎁 Reward Logic
            if (steps >= 10000) {
                points = 25;
            } else if (steps >= 5000) {
                points = 10;
            }

            // 🛡 Insurance Logic
            if (steps >= 10000) {
                discount = 5;
            } else if (steps >= 5000) {
                discount = 2;
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/fitnessdb",
                "root",
                "wellcome123"
            );

            // 🔍 Get user_id
            PreparedStatement psUser = con.prepareStatement(
                "SELECT id FROM users WHERE email=?"
            );
            psUser.setString(1, email);
            ResultSet rsUser = psUser.executeQuery();

            int userId = 0;
            if (rsUser.next()) {
                userId = rsUser.getInt("id");
            }

            // 1️⃣ fitness_data insert
            PreparedStatement ps1 = con.prepareStatement(
                "INSERT INTO fitness_data(user_id, steps, calories, distance, date) VALUES(?,?,?,?,CURDATE())"
            );

            ps1.setInt(1, userId);
            ps1.setInt(2, steps);
            ps1.setInt(3, steps / 20);
            ps1.setFloat(4, steps / 1300f);
            ps1.executeUpdate();

            // 2️⃣ rewards insert
            if (points > 0) {
                PreparedStatement ps2 = con.prepareStatement(
                    "INSERT INTO rewards(user_id, points, reward_type, date) VALUES(?,?,?,CURDATE())"
                );

                ps2.setInt(1, userId);
                ps2.setInt(2, points);
                ps2.setString(3, "Step Reward");
                ps2.executeUpdate();
            }

            // 3️⃣ insurance insert
            if (discount > 0) {
                PreparedStatement ps3 = con.prepareStatement(
                    "INSERT INTO insurance(user_id, steps, premium_discount, date) VALUES(?,?,?,CURDATE())"
                );

                ps3.setInt(1, userId);
                ps3.setInt(2, steps);
                ps3.setFloat(3, discount);
                ps3.executeUpdate();
            }

            con.close();

            // 👉 dashboard ला values पाठव
            request.setAttribute("steps", steps);
            request.setAttribute("calories", steps / 20);
            request.setAttribute("distance", steps / 1300f);
            request.setAttribute("points", points);
            request.setAttribute("discount", discount);

            request.getRequestDispatcher("dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}