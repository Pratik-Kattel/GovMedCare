package com.govmedcare.controller;

import com.govmedcare.service.MedicineService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Medicine",value = "/medicine")
public class AddMedicineServlet extends HttpServlet {
    private MedicineService medicineService;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Medicine Servlet initialized");
        this.medicineService=new MedicineService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd=request.getRequestDispatcher("/views/medicine.jsp");
        rd.forward(request,response);
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response){
        final String name=request.getParameter("medicineName");
        final String description=request.getParameter("description");
        final String quantity=request.getParameter("quantity");
        final String price=request.getParameter("price");
        final String imageURL=request.getParameter("imageURL");

    }
}
