package com.tsystems.nazukin.logiweb.controller;

import com.tsystems.nazukin.logiweb.controller.drivers.*;
import com.tsystems.nazukin.logiweb.controller.login.*;
import com.tsystems.nazukin.logiweb.controller.map.*;
import com.tsystems.nazukin.logiweb.controller.orders.*;
import com.tsystems.nazukin.logiweb.controller.trucks.GetTrucksCreatePageAction;
import com.tsystems.nazukin.logiweb.controller.trucks.GetTrucksEditPageAction;
import com.tsystems.nazukin.logiweb.controller.trucks.GetTrucksPageAction;
import com.tsystems.nazukin.logiweb.controller.trucks.TrucksEditAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1 on 11.02.2016.
 */
public class ActionFactory {

    private final static Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("POST/login", new LoginAction());
        actions.put("GET/login", new GetLoginPageAction());

        actions.put("GET/home", new HomeAction());

        actions.put("POST/registration", new RegistrationAction());
        actions.put("GET/registration", new GetRegistrationPageAction());

        actions.put("GET/logout", new LogoutAction());

        actions.put("GET/manager/startPage", new GetStartManagerPageAction());

        actions.put("GET/manager/map", new GetMapPageAction());
        actions.put("POST/manager/map/create", new CreateCityAction());
        actions.put("GET/manager/map/edit", new GetCityEditAction());
        actions.put("POST/manager/map/edit", new CityEditAction());
        actions.put("POST/manager/map/editInterval", new EditIntervalAction());

        actions.put("GET/manager/trucks", new GetTrucksPageAction());
        actions.put("GET/manager/trucks/edit", new GetTrucksEditPageAction());
        actions.put("GET/manager/trucks/create", new GetTrucksCreatePageAction());
        actions.put("POST/manager/trucks/edit", new TrucksEditAction());

        actions.put("GET/manager/newUsers", new GetDriversNewPageAction());
        actions.put("POST/manager/newUsers/createManager", new CreateManagerAction());
        actions.put("GET/manager/newUsers/createDriver", new GetCreateDriverPageAction());
        actions.put("POST/manager/newUsers/createDriver", new CreateDriverAction());
        actions.put("GET/manager/drivers", new GetDriversPageAction());
        actions.put("GET/manager/drivers/edit", new GetDriversEditPageAction());
        actions.put("POST/manager/drivers/edit", new DriversEditAction());

        actions.put("GET/manager/orders/createStart", new GetOrdersCreateStartPageAction());
        actions.put("GET/manager/orders/createDate", new GetOrdersTimePageAction());
        actions.put("POST/manager/orders/create", new CreateOrderAction());
        actions.put("GET/manager/orders/createPoint", new GetOrdersCreatePointPageAction());
        actions.put("GET/manager/orders/createPointCity", new GetOrdersCityPageAction());
        actions.put("GET/manager/orders/selectDrivers", new GetOrdersSelectDriversPage());
        actions.put("GET/manager/orders/createResult", new GetOrdersCreateResultPage());
        actions.put("GET/manager/orders", new GetOrdersStatusPageAction());
        actions.put("GET/manager/orders/cargos", new GetOrdersCargosStatusPageAction());

        actions.put("GET/driver/order", new GetDriverOrderPageAction());
    }

    public static Action getAction(HttpServletRequest request) {
        String s = request.getPathInfo();
        Action action = actions.get(request.getMethod() + request.getPathInfo());
        if (action != null) {
            return action;
        } else {
            return new Get404Page();
        }
    }
}
