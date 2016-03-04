package com.tsystems.nazukin.logiweb.controller.drivers;

import com.tsystems.nazukin.logiweb.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 17.02.2016.
 * POST /page/manager/newUsers/createManager
 */
public class CreateManagerAction implements com.tsystems.nazukin.logiweb.controller.Action {

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String employeeId = request.getParameter("id");
        employeeService.newToManager(Integer.parseInt(employeeId));

        response.sendRedirect("/page/manager/newUsers");
    }
}
