package com.govmedcare.filter;
import com.govmedcare.types.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        boolean isLoggedIn = session != null && session.getAttribute("loggedInUser") != null;
        String role = (session != null && session.getAttribute("role") != null) ? session.getAttribute("role").toString() : null;
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        boolean isAuthPage = uri.equals(contextPath + "/login") || uri.equals(contextPath + "/register");
        boolean isAdminPage = uri.startsWith(contextPath + "/admin");
        boolean isSupplierPage=uri.startsWith(contextPath+"/supplier");

        if (isLoggedIn && isAuthPage) {
            resp.sendRedirect(contextPath+"/");
            return;
        }
        if (isLoggedIn || isAuthPage) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(contextPath+"/login");
        }
        if (isLoggedIn && isAdminPage && !UserRole.ADMIN.name().equals(role)) {
            resp.sendRedirect(contextPath + "/unauthorized");
            return;
        }
        if (isLoggedIn && isSupplierPage && !UserRole.SUPPLIER.name().equals(role)) {
            resp.sendRedirect(contextPath + "/unauthorized");
            return;
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
