package com.tsystems.nazukin.logiweb.controller.drivers;

import com.tsystems.nazukin.logiweb.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 17.02.2016.
 * POST  /page/manager/newUsers/createDriver
 */
public class CreateDriverAction implements com.tsystems.nazukin.logiweb.controller.Action {

    private final DriverService driverService = new DriverService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String employeeId = request.getParameter("employeeId");
        String cityId = request.getParameter("currentCity");

        driverService.save(Integer.parseInt(employeeId), Integer.parseInt(cityId));

        response.sendRedirect("/page/manager/newUsers");
    }
}
