package com.tsystems.nazukin.logiweb.controller.filters;

import com.tsystems.nazukin.logiweb.controller.login.LoginAction;
import com.tsystems.nazukin.logiweb.model.enums.EmployeeType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/page/driver/*")
public class LoginFilterDriver implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        EmployeeType userType = (EmployeeType) session.getAttribute(LoginAction.USER_TYPE);

        if (session == null || userType == null || !userType.equals(EmployeeType.DRIVER)) {
            response.sendRedirect("/page/home"); // No logged-in user found, so redirect to login page.
        } else {
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }
    }

    @Override
    public void destroy() {
    }

}

