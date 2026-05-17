package com.govmedcare.controller;
import com.govmedcare.model.Report;
import com.govmedcare.service.MedicineService;
import com.govmedcare.service.ReportService;
import com.govmedcare.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Admin Dashboard", value = "/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private UserService userService;
    private MedicineService medicineService;
    private ReportService reportService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Admin Dashboard Initialized");
        this.userService = new UserService();
        this.medicineService = new MedicineService();
        this.reportService = new ReportService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int activeUsers = userService.getActiveUsers();
            int blockedUsers = userService.getBlockedUsers();
            int pendingMedicines = medicineService.getPendingCount();
            int approvedMedicines = medicineService.getApprovedCount();

            Report report = reportService.generateReport();
            double revenue = report.getRevenue();
            double tax = report.getTaxAmount();

            request.setAttribute("activeUsers", activeUsers);
            request.setAttribute("blockedUsers", blockedUsers);
            request.setAttribute("pendingMedicines", pendingMedicines);
            request.setAttribute("approvedMedicines", approvedMedicines);
            request.setAttribute("revenue", revenue);
            request.setAttribute("tax", tax);
            request.getRequestDispatcher("/views/admin-dashboard.jsp").forward(request, response);
        }
        catch (RuntimeException e){
            request.getSession().setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/views/admin-dashboard.jsp").forward(request, response);
        }
    }
}