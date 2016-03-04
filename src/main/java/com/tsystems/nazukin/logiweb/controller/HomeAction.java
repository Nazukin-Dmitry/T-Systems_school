package com.tsystems.nazukin.logiweb.controller;

import com.tsystems.nazukin.logiweb.model.enums.EmployeeType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 1 on 11.02.2016.
 */
public class HomeAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userType") == null) {
            response.sendRedirect("/page/login?type=normal");
            return;
        }

        EmployeeType employeeType = (EmployeeType) session.getAttribute("userType");
        switch (employeeType) {
            case MANAGER:
                response.sendRedirect("/page/manager/startPage");
                break;
            case DRIVER:
                response.sendRedirect("/page/driver/order");
                break;
            case NEW:
                response.sendRedirect("/page/login?type=new");
                break;
        }
    }
}