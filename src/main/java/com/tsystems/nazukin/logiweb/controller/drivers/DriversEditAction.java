package com.tsystems.nazukin.logiweb.controller.drivers;

import com.tsystems.nazukin.logiweb.customexceptions.DriverAllreadyUsedException;
import com.tsystems.nazukin.logiweb.service.DriverService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 18.02.2016.
 * POST page/manager/drivers/edit?id=
 */
public class DriversEditAction implements com.tsystems.nazukin.logiweb.controller.Action {

    private static final Logger logger = Logger.getLogger(DriversEditAction.class);

    private final DriverService driverService = new DriverService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String workTime = request.getParameter("workTime");
        String cityId = request.getParameter("currentCity");
        String pastCurrentCityID = request.getParameter("pastCurrentCity");

        switch (request.getParameter("action")) {
            case "update":
                driverService.update(id, firstName, secondName, workTime, cityId);
                response.sendRedirect("/page/manager/drivers/edit?id=" + id);
                break;
            case "delete":
                try {
                    driverService.delete(Integer.parseInt(id));
                    response.sendRedirect("/page/manager/drivers?cityId=" + pastCurrentCityID);
                } catch (DriverAllreadyUsedException e) {
                    logger.warn("Try to delete driver, that used in order.");
                    response.sendRedirect("/page/manager/drivers/edit?id=" + id +
                            "&type=" + GetDriversEditPageAction.TYPE_ERROR_DELETE);
                }
                break;
        }
    }
}
