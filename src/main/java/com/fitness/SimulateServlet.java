package com.fitness;

import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/simulate")
public class SimulateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Random rand = new Random();
        int steps = rand.nextInt(9000) + 1000;

        double calories = steps * 0.04;
        double distance = steps * 0.0008;

        request.setAttribute("steps", steps);
        request.setAttribute("calories", calories);
        request.setAttribute("distance", distance);

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}