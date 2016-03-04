package com.tsystems.nazukin.logiweb.controller.map;

import com.tsystems.nazukin.logiweb.controller.Action;
import com.tsystems.nazukin.logiweb.service.MapService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 15.02.2016.
 */
public class EditIntervalAction implements Action {

    private final MapService mapService = new MapService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer id1 = Integer.parseInt(request.getParameter("id1"));
        Integer id2 = Integer.parseInt(request.getParameter("id2"));
        Integer interval = Integer.parseInt(request.getParameter("interval"));

        mapService.saveOrUpdate(id1, id2, interval);

        response.sendRedirect("/page/manager/map/edit?id=" + id1);

    }
}
