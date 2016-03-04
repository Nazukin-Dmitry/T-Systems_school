package com.tsystems.nazukin.logiweb.controller.orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 21.02.2016.
 * GET  /page/manager/orders/createDate
 */
public class GetOrdersTimePageAction implements com.tsystems.nazukin.logiweb.controller.Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/WEB-INF/manager/ordersCreateTime.jsp").forward(request, response);
    }
}
