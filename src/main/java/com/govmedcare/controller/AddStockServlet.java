package com.govmedcare.controller;
import com.govmedcare.exception.InvalidQuantityException;
import com.govmedcare.model.Medicine;
import com.govmedcare.service.MedicineService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateStock", value = "/supplier/medicines/updateStock")
public class AddStockServlet extends HttpServlet {
    private MedicineService medicineService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("UpdateStock Servlet Initialized");
        medicineService = new MedicineService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            long medicineID = Long.parseLong(request.getParameter("medicine_ID"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Medicine medicine = new Medicine();
            medicine.setMedicineID(medicineID);
            boolean isSuccess = medicineService.addStockService(quantity, medicine);
            if (isSuccess) {
                request.getSession().setAttribute("success", "Stock updated successfully");
            }
            response.sendRedirect(request.getContextPath() + "/supplier/medicines/updateStock");
        } catch (InvalidQuantityException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/supplier/medicines/updateStock");
        }
    }
}
