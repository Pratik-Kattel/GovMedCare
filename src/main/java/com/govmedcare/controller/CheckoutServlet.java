package com.govmedcare.controller;
import com.govmedcare.exception.InvalidQuantityException;
import com.govmedcare.exception.PaymentMethodException;
import com.govmedcare.exception.RequiredPaymentException;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.service.CheckOutService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/patient/checkout")
public class CheckoutServlet extends HttpServlet {
    private CheckOutService checkOutService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException{
        super.init(servletConfig);
        System.out.println("Checkout servlet initialized");
        checkOutService = new CheckOutService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("loggedInUser") == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            Long patientId = ((com.govmedcare.model.User) session.getAttribute("loggedInUser")).getId();
            double paidAmount = Double.parseDouble(request.getParameter("paidAmount"));
            String paymentMethod = request.getParameter("paymentMethod");
            boolean success = checkOutService.checkOut(patientId, paidAmount, paymentMethod);
            if (success) {
                request.getSession().setAttribute("success", "Checkout successful!");
                response.sendRedirect(request.getContextPath() + "/patient/cart");
            }
            else {
                request.setAttribute("error", "Checkout failed");
                request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
            }
        } catch (UserDoesNotExistsException | InvalidQuantityException | PaymentMethodException |
                 RequiredPaymentException  e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/cart.jsp").forward(request, response);
        }
    }
}