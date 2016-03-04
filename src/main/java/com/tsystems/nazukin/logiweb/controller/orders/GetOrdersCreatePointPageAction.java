package com.tsystems.nazukin.logiweb.controller.orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 22.02.2016.
 * GET /page/manager/orders/createPoint?number=x
 */
public class GetOrdersCreatePointPageAction implements com.tsystems.nazukin.logiweb.controller.Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer number = Integer.parseInt(request.getParameter("number"));

        request.setAttribute("number", number);
        request.getRequestDispatcher("/WEB-INF/manager/ordersCreateItem.jsp").forward(request, response);
    }
}
