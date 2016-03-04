package com.tsystems.nazukin.logiweb.controller.orders;

import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderEntity;
import com.tsystems.nazukin.logiweb.service.CityService;
import com.tsystems.nazukin.logiweb.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Created by 1 on 24.02.2016.
 * GET /page/manager/orders
 */
public class GetOrdersStatusPageAction implements com.tsystems.nazukin.logiweb.controller.Action {

    private final OrderService orderService = new OrderService();
    private final CityService cityService = new CityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cityId = request.getParameter("cityId");
        CityEntity currentCity;
        List<CityEntity> cities = cityService.getAll();
        List<OrderEntity> orders;
        if (cityId != null) {
            if (cityId.equals("all")){
                currentCity = new CityEntity();
                currentCity.setName("All cities");
                request.setAttribute("currentCity", currentCity);
                orders = orderService.findAll();
            } else {
                currentCity = cityService.find(Integer.parseInt(cityId));
                request.setAttribute("currentCity", currentCity);
                orders = orderService.findByStartCityId(Integer.parseInt(cityId));
            }
        } else {
            orders = Collections.emptyList();
        }

        request.setAttribute("cityList", cities);
        request.setAttribute("orderList", orders);

        request.getRequestDispatcher("/WEB-INF/manager/orders.jsp").forward(request, response);
    }
}
