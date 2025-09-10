package com.org.controller.branch;

import com.org.dao.BranchDAO;
import com.org.entity.Branch;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/customer/branches")
public class AllBranchesServlet extends HttpServlet {
    BranchDAO branchDAO = BranchDAO
            .getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Branch> all = branchDAO
                .findAll();

        req
                .setAttribute("branches", all);

        // GOT ALL BRANCHES
    }
}
