package com.govmedcare.controller;
import com.govmedcare.exception.UserAlreadyExistsException;
import com.govmedcare.model.User;
import com.govmedcare.service.AuthService;
import com.govmedcare.validator.UserValidator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private AuthService authService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("RegisterServlet initialized");
        this.authService = new AuthService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/register.jsp");
        rd.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/register.jsp");
        String name = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        try{
        User user = new User(name, email, address, phone, password);
        UserValidator.validateRegistrationCredentials(user);
        boolean isRegistered = authService.registerUserService(user);
        if(isRegistered){
            request.setAttribute("success","Registration successful please login to continue");
            String contextPath=request.getContextPath();
            response.sendRedirect(contextPath+"/login");
        }
        else {
            request.setAttribute("error", "Internal error occurred, please try again");
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        }
    }
        catch (IllegalArgumentException | UserAlreadyExistsException e){
            request.setAttribute("error",e.getMessage());
            request.getRequestDispatcher("/views/register.jsp").forward(request, response);
        }
    }
}
