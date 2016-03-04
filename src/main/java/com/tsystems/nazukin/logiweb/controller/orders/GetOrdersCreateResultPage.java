package com.tsystems.nazukin.logiweb.controller.orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 23.02.2016.
 * GET/manager/page/manager/orders/createResult?type=
 */
public class GetOrdersCreateResultPage implements com.tsystems.nazukin.logiweb.controller.Action {

    public static final String ERROR = "error";
    public static final String SUCCESS = "success";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String type = request.getParameter("type");
        String orderId = request.getParameter("id");
        request.setAttribute("type", type);
        request.setAttribute("orderId", orderId);
        request.getRequestDispatcher("/WEB-INF/manager/ordersCreateResult.jsp").forward(request, response);
    }
}
