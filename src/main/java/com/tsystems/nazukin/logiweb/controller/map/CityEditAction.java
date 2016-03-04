package com.tsystems.nazukin.logiweb.controller.map;

import com.tsystems.nazukin.logiweb.controller.Action;
import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.service.CityService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 15.02.2016.
 */
public class CityEditAction implements Action {

    private static final Logger logger = Logger.getLogger(CityEditAction.class);

    private final CityService cityService = new CityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer id = Integer.parseInt(request.getParameter("id"));
        String cityName = request.getParameter("name");

        CityEntity entity = new CityEntity();
        entity.setId(id);
        entity.setName(cityName);
        try {
            cityService.saveOrUpdate(entity);
            response.sendRedirect("/page/manager/map/edit?id=" + id);
        } catch (RuntimeException e) {
            logger.warn("Try to update city to existing name", e);
            response.sendRedirect("/page/manager/map/edit?id=" + id + "&type=" + GetCityEditAction.TYPE_ERROR);
            return;
        }

    }
}
