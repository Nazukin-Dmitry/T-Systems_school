package com.tsystems.nazukin.logiweb.controller.orders;

import com.tsystems.nazukin.logiweb.model.entity.DriverEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderEntity;
import com.tsystems.nazukin.logiweb.service.CityService;
import com.tsystems.nazukin.logiweb.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 1 on 23.02.2016.
 * GET /manager/orders/selectDrivers
 */

public class GetOrdersSelectDriversPage implements com.tsystems.nazukin.logiweb.controller.Action {

    public final static String ERROR_DRIVER_USED = "errorDriverUsed";

    private final CityService cityService = new CityService();
    private final DriverService driverService = new DriverService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String type = request.getParameter("type");
        if (type != null) {
            switch (type) {
                case ERROR_DRIVER_USED:
                    request.setAttribute("error", "Some of the drivers you selected are already used!!! Please, reselect.");
                    break;
            }
        }

        OrderEntity orderEntity = (OrderEntity) request.getSession().getAttribute(CreateOrderAction.ORDER);
        List<DriverEntity> driverList = driverService.findForOrder(orderEntity);

        request.setAttribute("driverNumber", orderEntity.getTruck().getDriverCount());
        request.setAttribute("driverList", driverList);


        if ((Boolean) request.getSession().getAttribute(CreateOrderAction.RETURN_TO_SELECT_DRIVERS)) {
            request.getSession().setAttribute(CreateOrderAction.RETURN_TO_SELECT_DRIVERS, false);
        }

        request.getRequestDispatcher("/WEB-INF/manager/ordersSelectDrivers.jsp").forward(request, response);
    }
}
