package com.tsystems.nazukin.logiweb.controller.drivers;

import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.model.entity.DriverEntity;
import com.tsystems.nazukin.logiweb.model.enums.DriverStatus;
import com.tsystems.nazukin.logiweb.service.CityService;
import com.tsystems.nazukin.logiweb.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 1 on 18.02.2016.
 * <p>
 * GET /page/manager/drivers/edit?type=
 */
public class GetDriversEditPageAction implements com.tsystems.nazukin.logiweb.controller.Action {

    public static final String TYPE_ERROR_DELETE = "errorDelete";

    private final DriverService driverService = new DriverService();
    private final CityService cityService = new CityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer driverId = Integer.parseInt(request.getParameter("id"));

        DriverEntity driver = driverService.find(driverId);
        List<CityEntity> cityList = cityService.getAll();

        request.setAttribute("driver", driver);
        request.setAttribute("cityList", cityList);
        request.setAttribute("statusList", Arrays.asList(DriverStatus.values()));

        String error = "";
        String type = request.getParameter("type");
        if (type != null) {
            switch (type) {
                case TYPE_ERROR_DELETE:
                    error = "Unable to remove the driver, it is used in order.";
                    break;
                default:
                    error = "";
            }
        }

        request.setAttribute("error", error);

        request.getRequestDispatcher("/WEB-INF/manager/driversEdit.jsp").forward(request, response);
    }
}
