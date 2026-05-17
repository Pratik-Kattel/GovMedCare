package com.govmedcare.controller;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.User;
import com.govmedcare.service.CartService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Clear cart", value = "/cart/clear")
public class ClearCartServlet extends HttpServlet {
    private CartService cartService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("Clear cart servlet initialized");
        this.cartService = new CartService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession httpSession = request.getSession(false);
            if (httpSession == null) {
                response.sendRedirect(request.getContextPath() + "/logout");
                return;
            }
            User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                response.sendRedirect(request.getContextPath() + "/logout");
                return;
            }
            Long patientId = loggedInUser.getId();
            cartService.clearCartService(patientId);
            response.sendRedirect(request.getContextPath() + "/patient/cart");
        } catch (UserDoesNotExistsException e) {
            request.getSession().setAttribute("error", e.getMessage());
            response.sendRedirect(request.getContextPath() + "/patient/cart");
        }

    }
}