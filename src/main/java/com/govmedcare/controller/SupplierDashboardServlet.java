package com.govmedcare.controller;

import com.govmedcare.service.MedicineService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Dashboard", value = "/admin/dashboard")
public class SupplierDashboardServlet extends HttpServlet {

    private MedicineService medicineService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.medicineService = new MedicineService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("pendingCount",  medicineService.getPendingCount());
        request.setAttribute("approvedCount", medicineService.getApprovedCount());

        request.getRequestDispatcher("/views/dashboard.jsp")
                .forward(request, response);
    }
}