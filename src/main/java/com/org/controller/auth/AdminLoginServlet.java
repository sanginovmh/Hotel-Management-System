package com.org.controller.auth;

import com.org.dao.AdminDAO;
import com.org.entity.Admin;
import com.org.util.Base64Encoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/auth/admin/login")
public class AdminLoginServlet extends HttpServlet {
    AdminDAO adminDAO = AdminDAO
            .getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req
                .getRequestDispatcher("/templates/auth/admin-login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req
                .getParameter("email");
        String password = req
                .getParameter("password");

        Optional<Admin> optionalAdmin = adminDAO
                .login(
                        email,
                        Base64Encoder
                                .encode(password)
                );

        if (optionalAdmin.isPresent()) {
            // SUCCESSFUL LOGIN
        }

        // COULD NOT LOG IN
        req
                .setAttribute("error", "Bad credentials");
        req
                .getRequestDispatcher("/auth/admin-login")
                .forward(req, resp);
    }
}
