package com.tsystems.nazukin.logiweb.controller.map;

import com.tsystems.nazukin.logiweb.controller.Action;
import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.model.entity.MapEntity;
import com.tsystems.nazukin.logiweb.service.CityService;
import com.tsystems.nazukin.logiweb.service.MapService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 15.02.2016.
 */
public class GetCityEditAction implements Action {

    public static final String TYPE_ERROR = "error";

    private final CityService cityService = new CityService();
    private final MapService mapService = new MapService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = Integer.parseInt(request.getParameter("id"));

        CityEntity entity = cityService.find(id);
        request.setAttribute("name", entity.getName());
        request.setAttribute("id", entity.getId());
        Map<CityEntity, MapEntity> intervalMap = mapService.getCityIntervals(id);
        request.setAttribute("intervalMap", intervalMap);

        List<CityEntity> cities = cityService.getAll();
        request.setAttribute("cityList", cities);

        String error = "";
        String type = request.getParameter("type");
        if (type != null) {
            switch (type) {
                case TYPE_ERROR:
                    error = "City with same name have created yet!!!";
                    break;
                default:
                    error = "";
            }
        }
        request.setAttribute("error", error);

        request.getRequestDispatcher("/WEB-INF/manager/mapEdit.jsp").forward(request, response);
    }
}
