package com.org.controller.auth;


import com.org.dao.ManagerDAO;
import com.org.entity.Manager;
import com.org.util.Base64Encoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/auth/manager/login")
public class ManagerLoginServlet extends HttpServlet {
    ManagerDAO managerDAO = ManagerDAO
            .getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req
                .getRequestDispatcher("/templates/auth/manager-login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req
                .getParameter("email");
        String password = req
                .getParameter("password");

        Optional<Manager> optionalManager = managerDAO
                .login(
                        email,
                        Base64Encoder
                                .encode(password)
                );

        if (optionalManager.isPresent()) {
            // SUCCESSFUL LOGIN
        }

        // COULD NOT LOG IN
        req
                .setAttribute("error", "Bad credentials");
        req
                .getRequestDispatcher("/auth/manager-login")
                .forward(req, resp);
    }
}
