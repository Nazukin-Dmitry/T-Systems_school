package com.tsystems.nazukin.logiweb.controller.drivers;

import com.tsystems.nazukin.logiweb.model.entity.EmployeeEntity;
import com.tsystems.nazukin.logiweb.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 1 on 17.02.2016.
 * /page/manager/newUsers get
 */
public class GetDriversNewPageAction implements com.tsystems.nazukin.logiweb.controller.Action {

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<EmployeeEntity> newEmployees = employeeService.getAllNew();
        request.setAttribute("userList", newEmployees);
        request.getRequestDispatcher("/WEB-INF/manager/driversNew.jsp").forward(request, response);
    }
}
