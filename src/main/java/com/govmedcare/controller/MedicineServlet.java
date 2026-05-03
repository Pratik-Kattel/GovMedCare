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
import java.util.List;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("categories", categoryDao.getAllCategory());

        String categoryId = request.getParameter("category_id");

        List<Medicine> medicines;

        if (categoryId != null && !categoryId.isEmpty()) {
            Long id = Long.parseLong(categoryId);
            medicines = saveMedicineService.getAllMedicineByCategory(id);
        } else {
            medicines = saveMedicineService.getAllMedicines();
        }

        request.setAttribute("medicines", medicines);

        request.getRequestDispatcher("/views/medicine.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
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
                request.getSession().setAttribute("success", "Medicine saved successfully");
                response.sendRedirect(request.getContextPath() + "/supplier/medicine");
            }
            else{
                request.setAttribute("Failed","Failed to save the medicine");
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/supplier/medicine");
            }

        } catch (IllegalArgumentException | MedicineAlreadyExistsException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("categories", categoryDao.getAllCategory());
            request.getRequestDispatcher("/views/medicine.jsp")
                    .forward(request, response);
        }
    }

}
