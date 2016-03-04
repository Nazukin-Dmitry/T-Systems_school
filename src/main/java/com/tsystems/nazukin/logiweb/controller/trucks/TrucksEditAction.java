package com.tsystems.nazukin.logiweb.controller.trucks;

import com.tsystems.nazukin.logiweb.customexceptions.TruckAllreadyUsedException;
import com.tsystems.nazukin.logiweb.model.entity.TruckEntity;
import com.tsystems.nazukin.logiweb.model.enums.TruckState;
import com.tsystems.nazukin.logiweb.service.TruckService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 16.02.2016.
 */
public class TrucksEditAction implements com.tsystems.nazukin.logiweb.controller.Action {

    private static final Logger logger = Logger.getLogger(TrucksEditAction.class);

    private final TruckService truckService = new TruckService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String regNumber = request.getParameter("regNumber");
        String state = request.getParameter("state");
        String capacity = request.getParameter("capacity");
        String driverCount = request.getParameter("driverCount");
        String currentCityId = request.getParameter("currentCity");
        String pastCurrentCityID = request.getParameter("pastCurrentCity");

        TruckEntity truckEntity = new TruckEntity();

        switch (request.getParameter("action")) {
            case ("update"):
                try {
                    truckEntity.setRegNumber(regNumber);
                    truckEntity.setState(TruckState.valueOf(state));
                    truckEntity.setCapacity(Integer.parseInt(capacity));
                    truckEntity.setDriverCount(Integer.parseInt(driverCount));

                    String id = request.getParameter("id");
                    truckEntity.setId(Integer.parseInt(id));
                    truckService.saveOrUpdate(truckEntity, currentCityId);
                    response.sendRedirect("/page/manager/trucks/edit?id=" + truckEntity.getId());
                } catch (RuntimeException e) {
                    logger.warn("Registration number exception");
                    response.sendRedirect("/page/manager/trucks/edit?id=" + truckEntity.getId() + "&type=" + "error");
                }
                break;

            case ("delete"):
                String id = request.getParameter("id");
                try {
                    truckService.delete(Integer.parseInt(id));
                    response.sendRedirect("/page/manager/trucks?cityId=" + pastCurrentCityID);
                } catch (TruckAllreadyUsedException e) {
                    logger.warn("Registration number exception");
                    response.sendRedirect("/page/manager/trucks/edit?id=" + id + "&type=" + "errorDelete");
                }
                break;

            case ("create"):
                try {
                    truckEntity.setRegNumber(regNumber);
                    truckEntity.setState(TruckState.valueOf(state));
                    truckEntity.setCapacity(Integer.parseInt(capacity));
                    truckEntity.setDriverCount(Integer.parseInt(driverCount));

                    truckService.saveOrUpdate(truckEntity, currentCityId);
                    response.sendRedirect("/page/manager/trucks");
                } catch (RuntimeException e) {
                    logger.warn("Registration number exception");
                    response.sendRedirect("/page/manager/trucks/create?type=error");
                }
                break;
        }
    }
}
