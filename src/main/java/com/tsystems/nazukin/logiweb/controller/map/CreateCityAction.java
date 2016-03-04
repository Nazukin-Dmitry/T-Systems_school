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
public class CreateCityAction implements Action {

    private static final Logger logger = Logger.getLogger(CreateCityAction.class);

    private final CityService service = new CityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CityEntity entity = new CityEntity();
        entity.setName(request.getParameter("name"));

        try {
            service.saveOrUpdate(entity);
        } catch (RuntimeException e) {
            logger.warn("Save existing city", e);
            response.sendRedirect("/page/manager/map?type=" + GetMapPageAction.TYPE_ERROR);
            return;
        }
        response.sendRedirect("/page/manager/map");
    }
}
