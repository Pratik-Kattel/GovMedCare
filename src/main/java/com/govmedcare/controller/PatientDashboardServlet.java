package com.govmedcare.controller;

import com.govmedcare.model.Medicine;
import com.govmedcare.service.MedicineService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PatientDashboard", value = "/patient/dashboard")
public class PatientDashboardServlet extends HttpServlet {
    private MedicineService medicineService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Patient dashboard servlet initialized");
        medicineService = new MedicineService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Medicine> medicines = medicineService.getAllApprovedMedicinesService();
        int totalApproved = medicineService.getApprovedCount();
        request.setAttribute("TotalApproved", totalApproved);
        request.setAttribute("medicine", medicines);
        request.getRequestDispatcher("/views/patient-medicine.jsp").forward(request, response);
    }
}
