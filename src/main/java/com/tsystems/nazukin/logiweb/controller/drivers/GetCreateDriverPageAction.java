package com.tsystems.nazukin.logiweb.controller.drivers;

import com.tsystems.nazukin.logiweb.service.CityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 17.02.2016.
 * GET  /page/manager/newUsers/createDriver?id=
 */
public class GetCreateDriverPageAction implements com.tsystems.nazukin.logiweb.controller.Action {

    private final CityService cityService = new CityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String employeeId = request.getParameter("id");
        request.setAttribute("employeeId", employeeId);
        request.setAttribute("cityList", cityService.getAll());

        request.getRequestDispatcher("/WEB-INF/manager/driversCreate.jsp").forward(request, response);
    }
}
