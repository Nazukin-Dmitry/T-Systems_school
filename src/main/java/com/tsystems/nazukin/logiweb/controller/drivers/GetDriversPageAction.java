package com.tsystems.nazukin.logiweb.controller.drivers;

import com.tsystems.nazukin.logiweb.controller.Action;
import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.model.entity.DriverEntity;
import com.tsystems.nazukin.logiweb.service.CityService;
import com.tsystems.nazukin.logiweb.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Created by 1 on 18.02.2016.
 * GET /page/manager/drivers
 */
public class GetDriversPageAction implements Action {

    private final CityService cityService = new CityService();
    private final DriverService driverService = new DriverService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cityId = request.getParameter("cityId");
        List<CityEntity> cities = cityService.getAll();
        List<DriverEntity> drivers;
        if (cityId != null) {
            if (cityId.equals("all")){
                CityEntity currentCity = new CityEntity();
                currentCity.setName("All cities");
                request.setAttribute("currentCity", currentCity);
                drivers = driverService.findAll();
            } else {
                CityEntity currentCity;
                currentCity = cityService.find(Integer.parseInt(cityId));
                request.setAttribute("currentCity", currentCity);
                drivers = driverService.findAllByCityId(Integer.parseInt(cityId));
            }
        } else {
            drivers = Collections.emptyList();
        }

        request.setAttribute("cityList", cities);
        request.setAttribute("driverList", drivers);

        request.getRequestDispatcher("/WEB-INF/manager/drivers.jsp").forward(request, response);
    }
}
