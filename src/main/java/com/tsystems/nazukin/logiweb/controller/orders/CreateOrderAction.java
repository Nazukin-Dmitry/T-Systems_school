package com.tsystems.nazukin.logiweb.controller.orders;

import com.tsystems.nazukin.logiweb.customexceptions.DriverAllreadyUsedException;
import com.tsystems.nazukin.logiweb.customexceptions.TruckAllreadyUsedException;
import com.tsystems.nazukin.logiweb.model.entity.*;
import com.tsystems.nazukin.logiweb.model.enums.CargoStatus;
import com.tsystems.nazukin.logiweb.model.enums.OrderItemType;
import com.tsystems.nazukin.logiweb.service.CityService;
import com.tsystems.nazukin.logiweb.service.MapService;
import com.tsystems.nazukin.logiweb.service.OrderService;
import com.tsystems.nazukin.logiweb.service.TruckService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 1 on 20.02.2016.
 * POST /page/manager/orders/create?action={chooseCargo,...
 */
public class CreateOrderAction implements com.tsystems.nazukin.logiweb.controller.Action {

    public static final String MAX_WEIGHT = "maxWeight";
    public static final String ORDER = "order";
    public static final String LIST_ORDER_ITEMS = "listOrderItems";
    public static final String CARGO_LIST = "cargoList";
    public static final String RETURN_TO_SELECT_DRIVERS = "returnSelect";
    private static final Logger logger = Logger.getLogger(CreateOrderAction.class);
    private static final int MAX_HOURS = 176;
    private final TruckService truckService = new TruckService();
    private final OrderService orderService = new OrderService();
    private final CityService cityService = new CityService();
    private final MapService mapService = new MapService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String action = request.getParameter("action");

        switch (action) {
            case "chooseCargo":
                chooseCargo(request, response);
                break;

            case "time":
                setTime(request, response);
                break;

            case "createTransit":
                createTransit(request, response);
                break;

            case "createLoading":
                createLoading(request, response);
                break;

            case "createUnloading":
                createUnloading(request, response);
                break;

            case "chooseCity":
                chooseCity(request, response);
                break;

            case "createCity":
                createCity(request, response);
                break;

            case "createInterval":
                createInterval(request, response);
                break;

            case "completeOrder":
                completeOrder(request, response);
                break;

            /// save order in database
            case "selectDrivers":
                selectDriversAndSaveOrder(request, response);
                break;

            case "createDrivers":
                createDrivers(request, response);
                break;
        }
    }

    public void chooseCargo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderEntity order = new OrderEntity();
        String truckId = request.getParameter("truckId");
        String cityId = request.getParameter("cityId");

        CityEntity cityEntity = cityService.find(Integer.parseInt(cityId));
        TruckEntity truck = truckService.find(Integer.parseInt(truckId));
        order.setTruck(truck);
        order.setStartCity(cityEntity);

        List<OrderItemEntity> listOrderItems = new ArrayList<>();
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setCity(cityEntity);
        listOrderItems.add(0, orderItemEntity);

        List<CargoEntity> cargoList = new ArrayList<>();

        HttpSession session = request.getSession();
        session.setAttribute(CARGO_LIST, cargoList); ///cargos to unloading
        session.setAttribute(LIST_ORDER_ITEMS, listOrderItems); ///items of order
        session.setAttribute(ORDER, order); ///order
        session.setAttribute(MAX_WEIGHT, truck.getCapacity());
        session.setAttribute(RETURN_TO_SELECT_DRIVERS, false);

        response.sendRedirect("/page/manager/orders/createDate");
    }

    public void setTime(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        OrderEntity order = (OrderEntity) request.getSession().getAttribute(ORDER);
        String dateS = request.getParameter("date");

        Date date = new SimpleDateFormat("MM/dd/yyyy hh:mm aa").parse(dateS);
        order.setStartTime(date);
        response.sendRedirect("/page/manager/orders/createPoint?number=0");
    }

    public void createTransit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer number = Integer.parseInt(request.getParameter("number"));
        List<OrderItemEntity> listOrderItems = (List<OrderItemEntity>) request.getSession()
                .getAttribute(LIST_ORDER_ITEMS);

        OrderItemEntity item = listOrderItems.get(number);
        item.setType(OrderItemType.TRANSIT);
        item.setSequenceNumber(number);
        response.sendRedirect("/page/manager/orders/createPointCity?number=" + (number + 1));
    }

    public void createLoading(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer cargoWeight = Integer.parseInt(request.getParameter("cargoWeight"));
        String cargoName = request.getParameter("cargoName");
        HttpSession session = request.getSession();
        List<OrderItemEntity> listOrderItems = (List<OrderItemEntity>) session.getAttribute(LIST_ORDER_ITEMS);
        List<CargoEntity> cargoList = (List<CargoEntity>) session.getAttribute(CARGO_LIST);

        OrderItemEntity item = listOrderItems.get(number);
        item.setType(OrderItemType.LOADING);
        item.setSequenceNumber(number);

        CargoEntity cargo = new CargoEntity();
        cargo.setName(cargoName);
        cargo.setStatus(CargoStatus.PREPARED);
        cargo.setWeight(cargoWeight);
        item.setCargo(cargo);

        cargoList.add(cargo);

        //update maxWeight it should be less
        Integer maxWeight = (Integer) session.getAttribute(MAX_WEIGHT);
        session.setAttribute(MAX_WEIGHT, maxWeight - cargoWeight);

        response.sendRedirect("/page/manager/orders/createPointCity?number=" + (number + 1));
    }

    public void createUnloading(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer cargoNumber = Integer.parseInt(request.getParameter("cargoNumber")); ///cargo to unload
        HttpSession session = request.getSession();
        List<OrderItemEntity> listOrderItems = (List<OrderItemEntity>) session.getAttribute(LIST_ORDER_ITEMS);
        List<CargoEntity> cargoList = (List<CargoEntity>) session.getAttribute(CARGO_LIST);

        OrderItemEntity item = listOrderItems.get(number);
        item.setType(OrderItemType.UNLOADING);
        item.setSequenceNumber(number);
        //find cargo to unloading
        CargoEntity cargoToUnloading = cargoList.get(cargoNumber);
        //set cargo to unloading
        item.setCargo(cargoToUnloading);
        //remove this cargo
        cargoList.remove(cargoNumber.intValue());

        //update maxWeight, it should be bigger after unloading
        Integer maxWeight = (Integer) session.getAttribute(MAX_WEIGHT);
        session.setAttribute(MAX_WEIGHT, maxWeight + cargoToUnloading.getWeight());

        response.sendRedirect("/page/manager/orders/createPointCity?number=" + (number + 1));
    }

    public void chooseCity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer cityIdTo = Integer.parseInt(request.getParameter("cityTo"));

        List<OrderItemEntity> listOrderItems = (List<OrderItemEntity>) request.getSession()
                .getAttribute(LIST_ORDER_ITEMS);
        Integer cityIdFrom = listOrderItems.get(number - 1).getCity().getId();

        Integer interval = mapService.getInterval(cityIdTo, cityIdFrom);
        if (interval == null) {
            response.sendRedirect("/page/manager/orders/createPointCity?number=" + number + "&type=interval&cityIdTo="
                    + cityIdTo + "&cityIdFrom=" + cityIdFrom);
            return;
        }

        CityEntity cityEntityTo = cityService.find(cityIdTo);

        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setCity(cityEntityTo);
        listOrderItems.add(orderItemEntity);

        response.sendRedirect("/page/manager/orders/createPoint?number=" + number);
    }

    public void createCity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer number = Integer.parseInt(request.getParameter("number"));
        String cityToName = request.getParameter("cityName");
        Integer interval = Integer.parseInt(request.getParameter("interval"));
        Integer cityFromId = Integer.parseInt(request.getParameter("cityFrom"));
        List<OrderItemEntity> listOrderItems = (List<OrderItemEntity>) request.getSession().getAttribute(LIST_ORDER_ITEMS);

        try {
            CityEntity cityTo = mapService.save(cityFromId, cityToName, interval);
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setCity(cityTo);
            listOrderItems.add(orderItemEntity);

            response.sendRedirect("/page/manager/orders/createPoint?number=" + number);
        } catch (RuntimeException e) {
            logger.warn("Try to save city with existing name");
            response.sendRedirect("/page/manager/orders/createPointCity?number=" + number + "&type=errorCreateCity");
        }
    }

    public void createInterval(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer cityFromId = Integer.parseInt(request.getParameter("cityFrom"));
        Integer cityToId = Integer.parseInt(request.getParameter("cityTo"));
        Integer interval = Integer.parseInt(request.getParameter("interval"));
        List<OrderItemEntity> listOrderItems = (List<OrderItemEntity>) request.getSession().getAttribute(LIST_ORDER_ITEMS);

        mapService.saveOrUpdate(cityFromId, cityToId, interval);
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setCity(cityService.find(cityToId));

        listOrderItems.add(orderItemEntity);
        response.sendRedirect("/page/manager/orders/createPoint?number=" + number);
    }

    public void completeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer number = Integer.parseInt(request.getParameter("number"));
        HttpSession session = request.getSession();
        List<OrderItemEntity> listOrderItems = (List<OrderItemEntity>) session.getAttribute(LIST_ORDER_ITEMS);
        OrderEntity order = (OrderEntity) session.getAttribute(ORDER);
        List<CargoEntity> cargoList = (List<CargoEntity>) session.getAttribute(CARGO_LIST);

        ///if there are loading cargos
        if (!cargoList.isEmpty()) {
            response.sendRedirect("/page/manager/orders/createPointCity?number=" + number + "&type=errorUnloadingCargos");
            return;
        }

        Integer duration = mapService.duration(listOrderItems);
        if (duration > MAX_HOURS) {
            response.sendRedirect("/page/manager/orders/createPointCity?number=" + number + "&type=errorMaxHours");
            return;
        }
        order.setDuration(duration);
        order.setOrderItems(new HashSet<>(listOrderItems));

        response.sendRedirect("/page/manager/orders/selectDrivers");
    }

    public void selectDriversAndSaveOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] driversIds = request.getParameterValues("drivers");
        OrderEntity order = (OrderEntity) request.getSession().getAttribute(ORDER);

        try {
            order = orderService.save(order, driversIds);
            clearSession(request);
            response.sendRedirect("/page/manager/orders/createResult?id=" + order.getId() + "&type=" + GetOrdersCreateResultPage.SUCCESS);
        } catch (TruckAllreadyUsedException e) {
            logger.warn("Try to use truck in 2 orders.");
            clearSession(request);
            response.sendRedirect("/page/manager/orders/createResult?type=" + GetOrdersCreateResultPage.ERROR);
        } catch (DriverAllreadyUsedException e) {
            logger.warn("Try to use driver in 2 orders.");
            response.sendRedirect("/page/manager/orders/selectDrivers?type=" + GetOrdersSelectDriversPage.ERROR_DRIVER_USED);
        }
    }

    public void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(ORDER);
        session.removeAttribute(MAX_WEIGHT);
        session.removeAttribute(LIST_ORDER_ITEMS);
        session.removeAttribute(CARGO_LIST);
        session.removeAttribute(RETURN_TO_SELECT_DRIVERS);
    }

    public void createDrivers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute(RETURN_TO_SELECT_DRIVERS, true);
        response.sendRedirect("/page/manager/newUsers");
    }


}
