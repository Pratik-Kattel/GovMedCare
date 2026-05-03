package com.govmedcare.controller;
import com.govmedcare.dao.CategoryDao;
import com.govmedcare.exception.MedicineAlreadyExistsException;
import com.govmedcare.model.Medicine;
import com.govmedcare.model.User;
import com.govmedcare.service.MedicineService;
import com.govmedcare.validator.MedicineValidator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Medicine", value = "/supplier/medicine")
public class MedicineServlet extends HttpServlet {
    private MedicineService saveMedicineService;
    private CategoryDao categoryDao;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Medicine Servlet initialized");
        this.saveMedicineService = new MedicineService();
        this.categoryDao=new CategoryDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", categoryDao.getAllCategory());
        RequestDispatcher rd = request.getRequestDispatcher("/views/medicine.jsp");
        rd.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String name = request.getParameter("medicineName");
        final String description = request.getParameter("description");
        final int quantity = Integer.parseInt(request.getParameter("quantity"));
        final double price = Double.parseDouble(request.getParameter("price"));
        final String imageURL = request.getParameter("image_url");
        final Long category_id = Long.parseLong(request.getParameter("category_id"));
        try {
            Medicine medicine = new Medicine(name, description, price, quantity, imageURL,category_id);
            HttpSession session = request.getSession(false);
            if (session == null) {
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/logout");
                return;
            }
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            MedicineValidator.validateMedicine(medicine);
            boolean saveMedicine=saveMedicineService.saveMedicineService(medicine, loggedInUser.getId());
            if(saveMedicine){
                request.setAttribute("success","Medicine saved successfully");
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/supplier/medicine");
            }
            else{
                request.setAttribute("Failed","Failed to save the medicine");
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/supplier/medicine");
            }

        } catch (IllegalArgumentException | MedicineAlreadyExistsException e) {
            request.setAttribute("Error",e.getMessage());
        }
    }

}
