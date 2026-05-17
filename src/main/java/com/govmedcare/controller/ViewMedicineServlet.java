package com.govmedcare.controller;
import com.govmedcare.dao.CategoryDao;
import com.govmedcare.model.Medicine;
import com.govmedcare.service.CartService;
import com.govmedcare.service.MedicineService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ViewMedicine",value = "/patient/medicines")
public class ViewMedicineServlet extends HttpServlet {
    private MedicineService medicineService;
    private CategoryDao categoryDao ;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("View Medicine servlet initialized");
       this.medicineService=new MedicineService();
       this.categoryDao=new CategoryDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("category_id");
        String sort = request.getParameter("sort");
        List<Medicine> medicines = null;
        try {
            request.setAttribute("categories", categoryDao.getAllCategory());
            if (categoryId != null && !categoryId.isEmpty()) {
                Long id = Long.parseLong(categoryId);
                medicines = medicineService.getPatientMedicinesService(id, sort);

            } else {
                medicines = medicineService.getAllApprovedMedicinesService();
            }
            request.setAttribute("medicines", medicines);
            request.getRequestDispatcher("/views/view-medicines.jsp").forward(request, response);
        } catch (RuntimeException e) {
            request.getSession().setAttribute("Error occurred", e.getMessage());
            request.getRequestDispatcher("/views/view-medicines.jsp").forward(request, response);
        }
        request.setAttribute("medicines", medicines);
        request.getRequestDispatcher("/views/patient-medicines.jsp").forward(request, response);
    }
}