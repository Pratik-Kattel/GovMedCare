package com.govmedcare.controller;
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

@WebServlet(name = "Add to Cart" ,value="/patient/cart/add")
public class AddToCartServlet extends HttpServlet {
    private CartService cartService;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Add to cart servlet initialized");
        this.cartService=new CartService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("loggedInUser");
        Long medicineId = Long.parseLong(request.getParameter("medicine_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        boolean success = cartService.addToCartService(user.getId(), medicineId, quantity);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/patient/cart");
        } else {
            response.sendRedirect(request.getContextPath() + "/patient/medicines");
        }
    }
}