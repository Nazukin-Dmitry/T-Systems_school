package com.tsystems.nazukin.logiweb.controller.orders;

import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderItemEntity;
import com.tsystems.nazukin.logiweb.service.CityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 1 on 22.02.2016.
 * GET /page/manager/orders/createPointCity
 */
public class GetOrdersCityPageAction implements com.tsystems.nazukin.logiweb.controller.Action {

    private final CityService cityService = new CityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer number = Integer.parseInt(request.getParameter("number"));
        List<OrderItemEntity> listOrderItems = (List<OrderItemEntity>) request.getSession()
                .getAttribute(CreateOrderAction.LIST_ORDER_ITEMS);

        List<CityEntity> cityList = cityService.getAll();
        request.setAttribute("cityList", cityList);

        CityEntity cityFrom = listOrderItems.get(number - 1).getCity();
        request.setAttribute("cityFrom", cityFrom);

        request.setAttribute("number", number);

        String type = request.getParameter("type");
        if (type != null) {
            switch (type) {
                case "errorCreateCity":
                    request.setAttribute("errorCreateCity", "City with same name already exists!!!");
                    break;
                case "interval":
                    CityEntity cityTo = cityService.find(Integer.parseInt(request.getParameter("cityIdTo")));
                    request.setAttribute("visible", true);
                    request.setAttribute("cityTo", cityTo);
                    break;
                case "errorUnloadingCargos":
                    request.setAttribute("errorWithOrder", "Not all cargos unloaded!!!");
                    break;
                case "errorMaxHours":
                    request.setAttribute("errorWithOrder", "The duration of the order is bigger than 176 hours!!! Please recreate order.");
                    break;
            }
        }

        request.getRequestDispatcher("/WEB-INF/manager/ordersCreateCity.jsp").forward(request, response);
    }
}
