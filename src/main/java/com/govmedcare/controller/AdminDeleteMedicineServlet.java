package com.govmedcare.controller;
import com.govmedcare.model.Medicine;
import com.govmedcare.service.ApproveMedicineService;
import com.govmedcare.service.MedicineService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminApprovedMedicine", value = "/admin/approved-medicines")
public class AdminDeleteMedicineServlet extends HttpServlet {
    private MedicineService medicineService;
    private ApproveMedicineService approveMedicineService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Delete medicine servlet initialized");
        this.medicineService = new MedicineService();
        this.approveMedicineService = new ApproveMedicineService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Medicine> medicines = medicineService.getAllApprovedMedicinesService();
        request.setAttribute("medicines", medicines);
        request.getRequestDispatcher("/views/admin-approved-medicines.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            if ("delete".equals(action)) {
                boolean deleted = approveMedicineService.deleteMedicine(id);

                if (deleted) {
                    request.getSession().setAttribute("success", "Medicine removed successfully.");
                } else {
                    request.getSession().setAttribute("error", "Failed to remove medicine.");
                }
            }
        } catch (Exception e) {
            request.getSession().setAttribute("error", e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/admin/approved-medicines");
    }
}