package com.govmedcare.controller;
import com.govmedcare.exception.InvalidOrderException;
import com.govmedcare.exception.InvalidQuantityException;
import com.govmedcare.exception.PaymentMethodException;
import com.govmedcare.exception.RequiredPaymentException;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.CartItem;
import com.govmedcare.model.User;
import com.govmedcare.service.CartService;
import com.govmedcare.service.CheckOutService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/patient/checkout")
public class CheckoutServlet extends HttpServlet {

    private CheckOutService checkOutService;
    private CartService cartService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Checkout servlet initialized");
        checkOutService = new CheckOutService();
        cartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");

        List<CartItem> cartItems = cartService.getCartItemsService(user.getId());

        double grandTotal = 0;

        for (CartItem item : cartItems) {
            grandTotal += item.getPrice() * item.getQuantity();
        }

        request.setAttribute("cartItems", cartItems);
        request.setAttribute("grandTotal", grandTotal);

        request.getRequestDispatcher("/views/checkout.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");

        try {
            double paidAmount = Double.parseDouble(request.getParameter("paidAmount"));
            String paymentMethod = request.getParameter("paymentMethod");

            boolean success = checkOutService.checkOut(
                    user.getId(),
                    paidAmount,
                    paymentMethod
            );

            if (success) {
                session.setAttribute("success", "Checkout successful!");
                response.sendRedirect(request.getContextPath() + "/patient/checkout");
            }
            else {
                session.setAttribute("error", "Checkout failed.");
                response.sendRedirect(request.getContextPath() + "/patient/checkout");
            }

        } catch (UserDoesNotExistsException | InvalidQuantityException |
                 PaymentMethodException | RequiredPaymentException |
                 InvalidOrderException e) {

            session.setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/patient/checkout");

        } catch (NumberFormatException e) {

            session.setAttribute("error", "Invalid payment amount.");
            response.sendRedirect(request.getContextPath() + "/patient/checkout");
        }
    }
}