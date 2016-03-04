package com.tsystems.nazukin.logiweb.service;

import com.tsystems.nazukin.logiweb.JPAUtil;
import com.tsystems.nazukin.logiweb.customexceptions.DriverAllreadyUsedException;
import com.tsystems.nazukin.logiweb.customexceptions.TruckAllreadyUsedException;
import com.tsystems.nazukin.logiweb.model.dao.implementations.CargoDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.implementations.DriverDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.implementations.OrderDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.CargoDao;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.DriverDao;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.OrderDao;
import com.tsystems.nazukin.logiweb.model.entity.CargoEntity;
import com.tsystems.nazukin.logiweb.model.entity.DriverEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderItemEntity;
import com.tsystems.nazukin.logiweb.model.enums.OrderStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service that provides functionality for working with {@link OrderEntity}, that representing order's data.
 */
public class OrderService {

    /**
     * data access object for order's data.
     */
    private final OrderDao orderDao;

    /**
     * Data access object for driver's data.
     */
    private final DriverDao driverDao;

    /**
     * Data access object for cargo's data.
     */
    private final CargoDao cargoDao;

    /**
     * Constructs service.
     */
    public OrderService() {
        orderDao = new OrderDaoImpl();
        driverDao = new DriverDaoImpl();
        cargoDao = new CargoDaoImpl();
    }

    /**
     * Constructs service.
     *
     * @param orderDao  data access object for order's data
     * @param driverDao data access object for driver's data
     * @param cargoDao  data access object for cargo's data
     */
    public OrderService(OrderDao orderDao, DriverDao driverDao, CargoDao cargoDao) {
        this.orderDao = orderDao;
        this.driverDao = driverDao;
        this.cargoDao = cargoDao;
    }

    /**
     * Saves order, order items in order and cargos. Sets drivers with specified ids to the order.
     *
     * @param orderEntity order's data
     * @param driversIds  drivers ids
     * @return saved order
     * @throws TruckAllreadyUsedException  if order's truck has been used by other order yet
     * @throws DriverAllreadyUsedException if order's driver has been used by other order yet
     */
    public OrderEntity save(OrderEntity orderEntity, String[] driversIds) {
        orderEntity.setStatus(OrderStatus.NEW);
        Set<OrderItemEntity> orderItems = orderEntity.getOrderItems();

        for (OrderItemEntity item : orderItems) {
            item.setOrder(orderEntity);
        }

        try {
            JPAUtil.beginTransaction();
            //check that truck isn't used
            OrderEntity entity = orderDao.findByTruckId(orderEntity.getTruck().getId());
            if (entity != null) {
                throw new TruckAllreadyUsedException();
            }
            //add drivers
            Set<DriverEntity> drivers = new HashSet<>();
            for (int i = 0; i < driversIds.length; i++) {
                Integer driverId = Integer.parseInt(driversIds[i]);
                DriverEntity driverEntity = driverDao.findById(DriverEntity.class, driverId);
                ///if driver is selected yet
                if (driverEntity.getCurrentOrder() != null) {
                    throw new DriverAllreadyUsedException();
                }
                driverEntity.setCurrentOrder(orderEntity);
                drivers.add(driverEntity);
            }
            orderEntity.setDrivers(drivers);

            orderEntity = orderDao.merge(orderEntity);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return orderEntity;
    }

    /**
     * Finds all orders, which has start city with specified id.
     *
     * @param cityId city's id
     * @return list of orders with specified start city
     */
    public List<OrderEntity> findByStartCityId(Integer cityId) {
        List<OrderEntity> result;
        try {
            JPAUtil.beginTransaction();
            result = orderDao.findByStartCityId(cityId);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return result;
    }

    /**
     * Finds all cargos in specified order.
     *
     * @param orderId order's id
     * @return list of cargos in specified order
     */
    public List<CargoEntity> findCargosByOrderId(Integer orderId) {
        List<CargoEntity> result = new ArrayList<>();
        try {
            JPAUtil.beginTransaction();
            result = cargoDao.findByOrderId(orderId);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return result;
    }

    /**
     * Finds all orders.
     *
     * @return list of all orders
     */
    public List<OrderEntity> findAll() {
        List<OrderEntity> result;
        try {
            JPAUtil.beginTransaction();
            result = orderDao.findAll();
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return result;
    }

}
