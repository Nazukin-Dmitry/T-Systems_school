package com.tsystems.nazukin.logiweb.controller.orders;

import com.tsystems.nazukin.logiweb.model.entity.CargoEntity;
import com.tsystems.nazukin.logiweb.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 1 on 24.02.2016.
 * GET /page/manager/orders/cargos?orderId=
 */
public class GetOrdersCargosStatusPageAction implements com.tsystems.nazukin.logiweb.controller.Action {

    private final OrderService orderService = new OrderService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));

        List<CargoEntity> cargoList = orderService.findCargosByOrderId(orderId);
        request.setAttribute("cargoList", cargoList);

        request.getRequestDispatcher("/WEB-INF/manager/orderCargos.jsp").forward(request, response);
    }
}
