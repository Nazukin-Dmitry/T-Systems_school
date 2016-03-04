package com.tsystems.nazukin.logiweb.controller.map;

import com.tsystems.nazukin.logiweb.controller.Action;
import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.service.CityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by 1 on 15.02.2016.
 */
public class GetMapPageAction implements Action {

    public static final String TYPE_ERROR = "error";

    private final CityService service = new CityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<CityEntity> cities = service.getAll();
        String type = request.getParameter("type");
        String error = "";
        if (type != null) {
            switch (type) {
                case TYPE_ERROR:
                    error = "City with same name have created yet!!!";
                    break;
                default:
                    error = "";
            }
        }
        request.setAttribute("cityList", cities);
        request.setAttribute("error", error);
        request.getRequestDispatcher("/WEB-INF/manager/map.jsp").forward(request, response);
    }
}
