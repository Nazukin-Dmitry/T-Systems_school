package com.tsystems.nazukin.logiweb.controller.trucks;

import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.model.entity.TruckEntity;
import com.tsystems.nazukin.logiweb.model.enums.TruckState;
import com.tsystems.nazukin.logiweb.service.CityService;
import com.tsystems.nazukin.logiweb.service.TruckService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 1 on 16.02.2016.
 */
public class GetTrucksEditPageAction implements com.tsystems.nazukin.logiweb.controller.Action {

    public static final String TYPE_ERROR = "error";
    public static final String TYPE_ERROR_DELETE = "errorDelete";

    private final TruckService truckService = new TruckService();
    private final CityService cityService = new CityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer truckId = Integer.parseInt(request.getParameter("id"));

        TruckEntity truck = truckService.find(truckId);
        List<CityEntity> cityList = cityService.getAll();

        request.setAttribute("truck", truck);
        request.setAttribute("cityList", cityList);
        request.setAttribute("stateList", Arrays.asList(TruckState.values()));

        String error = "";
        String type = request.getParameter("type");
        if (type != null) {
            switch (type) {
                case TYPE_ERROR:
                    error = "Registration number exists!!!";
                    break;
                case TYPE_ERROR_DELETE:
                    error = "Unable to remove the truck, it is used in order.";
                    break;
                default:
                    error = "";
            }
        }

        request.setAttribute("error", error);

        request.getRequestDispatcher("/WEB-INF/manager/trucksEdit.jsp").forward(request, response);
    }
}
