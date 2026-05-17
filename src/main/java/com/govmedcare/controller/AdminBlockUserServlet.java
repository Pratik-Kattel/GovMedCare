package com.govmedcare.controller;
import com.govmedcare.model.User;
import com.govmedcare.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminBlockUsers", value = "/admin/block-users")
public class AdminBlockUserServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Admin Users Servlet Initialized");
        this.userService = new UserService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // fetch all users
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/views/admin-manageuser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        try {
            Long userId = Long.parseLong(request.getParameter("user_id"));
            boolean blocked = userService.blockUserService(userId);
            if (blocked) {
                session.setAttribute("success", "User blocked successfully");
            } else {
                session.setAttribute("error", "Failed to block user");
            }
        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/admin/block-users");
    }
}