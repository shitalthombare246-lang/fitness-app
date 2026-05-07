package com.fitness;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    // ✅ GET method (logout button click)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔒 Cache disable (back button ने dashboard दिसू नये)
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        // 🔹 session घे (new create नको)
        HttpSession session = request.getSession(false);

        // 🔥 session delete
        if (session != null) {
            session.invalidate();
        }

        // ✅ login page कडे redirect
        response.sendRedirect("login.jsp");
    }

    // ❗ POST request handle (optional but safe)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response); // same logout logic
    }
}