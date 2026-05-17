package com.govmedcare.controller;
import com.govmedcare.model.OrderItem;
import com.govmedcare.model.User;
import com.govmedcare.service.OrderService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "View-Sold-History", value = "/supplier/viewsoldhistory")
public class ViewSoldHistoryServlet extends HttpServlet {
    private OrderService orderService;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("View sold Servlet initialized");
        this.orderService = new OrderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/logout");
            return;
        }
        try {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                response.sendRedirect(request.getContextPath() + "/logout");
                return;
            }
            List<OrderItem> soldMedicines = orderService.getSoldHistoryService(loggedInUser.getId());
            request.setAttribute("soldHistory", soldMedicines);
            request.getRequestDispatcher("/views/sold-history.jsp").forward(request, response);
        }
        catch (RuntimeException e){
            session.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/views/sold-history.jsp").forward(request, response);
        }
    }
}

