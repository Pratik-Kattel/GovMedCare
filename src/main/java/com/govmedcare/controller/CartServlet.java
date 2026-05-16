package com.govmedcare.controller;
import com.govmedcare.model.CartItem;
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
import java.util.List;


@WebServlet(name = "CartItems",value = "/patient/cart")
public class CartServlet extends HttpServlet {
    private CartService cartService;
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Cart servlet initialized");
        this.cartService=new CartService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("loggedInUser");
        List<CartItem> cartItems = cartService.getCartItemsService(user.getId());
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("/views/my-cart.jsp").forward(request, response);
    }
}