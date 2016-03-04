package com.tsystems.nazukin.logiweb.controller.orders;

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
 * Created by 1 on 20.02.2016.
 * GET /manager/orders/createStart
 */
public class GetOrdersCreateStartPageAction implements Action {

    private final TruckService truckService = new TruckService();
    private final CityService cityService = new CityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ///if manager created new drivers he come back to select drivers
        Object returnToSelectDrivers = request.getSession().getAttribute(CreateOrderAction.RETURN_TO_SELECT_DRIVERS);

        if (returnToSelectDrivers != null && (Boolean) returnToSelectDrivers) {
            request.getSession().setAttribute(CreateOrderAction.RETURN_TO_SELECT_DRIVERS, false);
            response.sendRedirect("/page/manager/orders/selectDrivers");
        } else {

            String cityId = request.getParameter("cityId");
            CityEntity currentCity;
            List<CityEntity> cities = cityService.getAll();
            List<TruckEntity> trucks;
            if (cityId != null) {
                currentCity = cityService.find(Integer.parseInt(cityId));
                request.setAttribute("currentCity", currentCity);
                trucks = truckService.findAllForOrder(Integer.parseInt(cityId));
            } else {
                trucks = Collections.emptyList();
            }

            request.setAttribute("cityList", cities);
            request.setAttribute("truckList", trucks);

            request.getRequestDispatcher("/WEB-INF/manager/ordersCreateStartPage.jsp").forward(request, response);
        }

    }
}
