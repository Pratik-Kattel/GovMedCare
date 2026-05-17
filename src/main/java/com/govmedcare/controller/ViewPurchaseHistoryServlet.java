package com.govmedcare.controller;

import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.Order;
import com.govmedcare.model.User;
import com.govmedcare.service.OrderService;
import com.govmedcare.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "View Purchase History", value = "/patient/purchase-history")
public class ViewPurchaseHistoryServlet extends HttpServlet {
    private OrderService orderService;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("View Purchase Servlet initialized");
        this.orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/logout");
            return;
        }
        try {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                response.sendRedirect(request.getContextPath() + "/logout");
                return;
            }
            List<Order> orders;
            orders = orderService.getPurchaseHistoryService(loggedInUser.getId());
            request.setAttribute("purchaseHistory", orders);
            request.getRequestDispatcher("/views/purchase-history.jsp").forward(request, response);
        } catch (UserDoesNotExistsException e) {
            session.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/purchase-history.jsp").forward(request, response);

        }
    }
}
