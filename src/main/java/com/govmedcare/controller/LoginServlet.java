package com.govmedcare.controller;
import com.govmedcare.exception.InvalidCredentialsException;
import com.govmedcare.exception.UserBlockedException;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.User;
import com.govmedcare.service.AuthService;
import com.govmedcare.service.UserService;
import com.govmedcare.types.UserRole;
import com.govmedcare.validator.UserValidator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class LoginServlet extends HttpServlet {
    private AuthService authService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Login Servlet initialized");
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            UserValidator.validateLoginCredentials(email, password);
            User user = authService.LoginUserService(email, password);
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("loggedInUser", user);
            newSession.setAttribute("role",user.getRole());
            request.setAttribute("success", "Login successful");
            String contextPath=request.getContextPath();
            if(UserRole.ADMIN.equals(user.getRole())) {
                response.sendRedirect(contextPath + "/admin/dashboard");
            }
            if(UserRole.SUPPLIER.equals(user.getRole())) {
                response.sendRedirect(contextPath + "/supplier/dashboard");
            }
            if(UserRole.PATIENT.equals(user.getRole())) {
                response.sendRedirect(contextPath + "/patient/dashboard");
            }

        } catch (IllegalArgumentException | InvalidCredentialsException | UserDoesNotExistsException |
                 UserBlockedException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
