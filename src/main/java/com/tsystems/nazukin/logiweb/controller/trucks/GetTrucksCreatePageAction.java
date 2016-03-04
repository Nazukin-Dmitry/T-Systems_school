package com.tsystems.nazukin.logiweb.controller.trucks;

import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.model.enums.TruckState;
import com.tsystems.nazukin.logiweb.service.CityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 1 on 17.02.2016.
 */
public class GetTrucksCreatePageAction implements com.tsystems.nazukin.logiweb.controller.Action {

    public static final String TYPE_ERROR = "error";
    private final CityService cityService = new CityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CityEntity> cities = cityService.getAll();
        request.setAttribute("cityList", cities);

        request.setAttribute("stateList", Arrays.asList(TruckState.values()));

        String error = "";
        String type = request.getParameter("type");
        if (type != null) {
            switch (type) {
                case TYPE_ERROR:
                    error = "Registration number exists!!!";
                    break;
                default:
                    error = "";
            }
        }

        request.setAttribute("error", error);

        request.getRequestDispatcher("/WEB-INF/manager/trucksCreate.jsp").forward(request, response);
    }
}
