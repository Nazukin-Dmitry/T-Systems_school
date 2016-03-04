package com.tsystems.nazukin.logiweb.controller.trucks;

import com.tsystems.nazukin.logiweb.controller.Action;
import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.model.entity.TruckEntity;
import com.tsystems.nazukin.logiweb.service.CityService;
import com.tsystems.nazukin.logiweb.service.TruckService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Created by 1 on 16.02.2016.
 */
public class GetTrucksPageAction implements Action {

    private final TruckService truckService = new TruckService();
    private final CityService cityService = new CityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cityId = request.getParameter("cityId");
        CityEntity currentCity;
        List<CityEntity> cities = cityService.getAll();
        List<TruckEntity> trucks;
        if (cityId != null) {
            if (cityId.equals("all")) {
                currentCity = new CityEntity();
                currentCity.setName("All cities");
                request.setAttribute("currentCity", currentCity);
                trucks = truckService.findAll();
            } else {
                currentCity = cityService.find(Integer.parseInt(cityId));
                request.setAttribute("currentCity", currentCity);
                trucks = truckService.findAllByCityId(Integer.parseInt(cityId));
            }
        } else {
            trucks = Collections.emptyList();
        }

        request.setAttribute("cityList", cities);
        request.setAttribute("truckList", trucks);


        request.getRequestDispatcher("/WEB-INF/manager/trucks.jsp").forward(request, response);
    }
}
