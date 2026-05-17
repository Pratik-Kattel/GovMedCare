package com.govmedcare.controller;
import com.govmedcare.exception.UserDoesNotExistsException;
import com.govmedcare.model.User;
import com.govmedcare.service.UserService;
import com.govmedcare.validator.UserValidator;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@WebServlet(name = "Update-info", value = "/patient/update-info")
public class UpdateInfoServlet extends HttpServlet {
    private UserService userService;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        System.out.println("Update Profile Servlet Initialized");
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null) {
            response.sendRedirect(request.getContextPath() + "/logout");
            return;
        }
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
        request.setAttribute("loggedInUser", loggedInUser);
        request.getRequestDispatcher("/views/update-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null) {
            response.sendRedirect(request.getContextPath() + "/logout");
            return;
        }
        try {
            User loggedInUser = (User) httpSession.getAttribute("loggedInUser");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            Long id=loggedInUser.getId();

            User user = new User();
            user.setName(name);
            user.setPhone(phone);
            user.setAddress(address);
            user.setId(id);
            user.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));
            UserValidator.validateInfoUpdateCredentials(user);

            boolean isUpdated = userService.updateUserInfoService(user);
            if (isUpdated) {
                loggedInUser.setName(name);
                loggedInUser.setPhone(phone);
                loggedInUser.setAddress(address);
                httpSession.setAttribute("loggedInUser", user);
                httpSession.setAttribute("success", "Info updated successfully");
            } else {
                httpSession.setAttribute("Failed", "Failed to update info");
            }
            response.sendRedirect(request.getContextPath() + "/patient/update-info");
        }
        catch (UserDoesNotExistsException | IllegalArgumentException e){
            httpSession.setAttribute("error",e.getMessage());
            response.sendRedirect(request.getContextPath() + "/patient/update-info");
        }
    }
}
