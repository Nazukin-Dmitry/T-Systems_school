package com.tsystems.nazukin.logiweb.controller;

import com.tsystems.nazukin.logiweb.controller.login.LoginAction;
import com.tsystems.nazukin.logiweb.customexceptions.WrongSerialNumberException;
import com.tsystems.nazukin.logiweb.model.entity.DriverEntity;
import com.tsystems.nazukin.logiweb.model.entity.EmployeeEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderItemEntity;
import com.tsystems.nazukin.logiweb.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 1 on 25.02.2016.
 * GET /page/driver/order
 */
public class GetDriverOrderPageAction implements Action {

    private final DriverService driverService = new DriverService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String serialNumber = request.getParameter("serialNumber");
        if (serialNumber == null) {
            request.getRequestDispatcher("/WEB-INF/driver/startPage.jsp").forward(request, response);
        } else {
            Integer serial = Integer.parseInt(serialNumber);
            EmployeeEntity employeeEntity = (EmployeeEntity) session.getAttribute(LoginAction.USER);
            DriverEntity driverEntity;

            //check right serial number
            try {
                driverEntity = driverService.findByEmployeeIdAndSerial(employeeEntity.getId(), serial);
            } catch (WrongSerialNumberException e) {
                request.setAttribute("error", "Wrong serial number!!! Try again.");
                request.getRequestDispatcher("/WEB-INF/driver/startPage.jsp").forward(request, response);
                return;
            }
            OrderEntity order = driverEntity.getCurrentOrder();
            if (order == null) {
                request.setAttribute("type", "noorder");
            } else {
                request.setAttribute("type", "order");
                List<OrderItemEntity> items = new ArrayList<>(order.getOrderItems());
                items.sort(new Comparator<OrderItemEntity>() {
                    @Override
                    public int compare(OrderItemEntity o1, OrderItemEntity o2) {
                        return o1.getSequenceNumber().compareTo(o2.getSequenceNumber());
                    }
                });

                request.setAttribute("orderItemList", items);
                request.setAttribute("order", order);
                request.setAttribute("codriverList", order.getDrivers());
                request.setAttribute("serialNumber", driverEntity.getSerialNumber());
            }
            request.getRequestDispatcher("/WEB-INF/driver/startPage.jsp").forward(request, response);
        }
    }
}
