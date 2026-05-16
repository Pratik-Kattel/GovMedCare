package com.govmedcare.controller;
import com.govmedcare.dao.CategoryDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "AddMedicine", value = "/supplier/medicines/add")
public class AddMedicineServlet extends HttpServlet {

    private CategoryDao categoryDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.categoryDao = new CategoryDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // redirect to log in if no session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect(request.getContextPath() + "/logout");
            return;
        }

        // Load categories for the dropdown
        request.setAttribute("categories", categoryDao.getAllCategory());

        request.getRequestDispatcher("/views/add-medicine.jsp")
                .forward(request, response);
    }
}