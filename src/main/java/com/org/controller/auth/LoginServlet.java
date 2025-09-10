package com.org.controller.auth;

import com.org.dao.CustomerDAO;
import com.org.entity.Customer;
import com.org.util.Base64Encoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    CustomerDAO customerDAO = CustomerDAO
            .getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req
                .getRequestDispatcher("/templates/auth/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req
                .getParameter("email");
        String password = req
                .getParameter("password");

        Optional<Customer> optionalCustomer = customerDAO
                .login(
                        email,
                        Base64Encoder
                                .encode(password)
                );

        if (optionalCustomer.isPresent()) {
            // SUCCESSFUL LOGIN
        }

        // COULD NOT LOG IN
        req
                .setAttribute("error", "Bad credentials");
        req
                .getRequestDispatcher("/auth/login")
                .forward(req, resp);
    }
}
