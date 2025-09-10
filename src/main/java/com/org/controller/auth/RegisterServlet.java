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

@WebServlet("/auth/register")
public class RegisterServlet extends HttpServlet {
    CustomerDAO customerDAO = CustomerDAO
            .getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req
                .getRequestDispatcher("/templates/auth/register.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req
                .getParameter("firstName");
        String lastName = req
                .getParameter("lastName");
        String phone = req
                .getParameter("phone");
        String email = req
                .getParameter("email");
        String password = req
                .getParameter("password");

        Customer customer = Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .phone(phone)
                .email(email)
                .password(Base64Encoder
                        .encode(password))
                .build();

        customerDAO
                .save(customer);

        // SUCCESSFUL REGISTRATION
    }
}
