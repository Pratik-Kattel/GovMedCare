package com.govmedcare.controller;

import com.govmedcare.model.Medicine;
import com.govmedcare.service.ApproveMedicineService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminApproveMedicines", value = "/admin/medicines")
public class AdminApproveMedicineServlet extends HttpServlet {

    private ApproveMedicineService approveMedicineService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        approveMedicineService = new ApproveMedicineService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Medicine> medicines = approveMedicineService.getPendingMedicines();
        request.setAttribute("medicines", medicines);

        request.getRequestDispatcher("/views/admin-medicine.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        try {
            int medicineId = Integer.parseInt(request.getParameter("medicine_id"));
            boolean approved = approveMedicineService.approveMedicine(medicineId);

            if (approved) {
                session.setAttribute("success", "Medicine approved successfully.");
            } else {
                session.setAttribute("error", "Failed to approve medicine.");
            }

        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/admin/medicines");
    }
}