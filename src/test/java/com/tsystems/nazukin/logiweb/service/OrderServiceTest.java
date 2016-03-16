package com.tsystems.nazukin.logiweb.service;

import com.tsystems.nazukin.logiweb.customexceptions.DriverAllreadyUsedException;
import com.tsystems.nazukin.logiweb.customexceptions.TruckAllreadyUsedException;
import com.tsystems.nazukin.logiweb.model.dao.implementations.CargoDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.implementations.DriverDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.implementations.OrderDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.CargoDao;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.DriverDao;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.OrderDao;
import com.tsystems.nazukin.logiweb.model.entity.DriverEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderEntity;
import com.tsystems.nazukin.logiweb.model.entity.TruckEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by 1 on 01.03.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    private OrderDao orderDao;
    private DriverDao driverDao;
    private CargoDao cargoDao;

    private OrderService orderService;

    @Before
    public void init() throws Exception {
        orderDao = mock(OrderDaoImpl.class);
        driverDao = mock(DriverDaoImpl.class);
        cargoDao = mock(CargoDaoImpl.class);

        orderService = new OrderService(orderDao, driverDao, cargoDao);
    }

    @Test(expected = TruckAllreadyUsedException.class)
    public void testSaveExceptionTruck() throws Exception {
        OrderEntity orderEntityWithTruck = new OrderEntity();
        when(orderDao.findByTruckId(Mockito.anyInt())).thenReturn(orderEntityWithTruck);
        when(orderDao.merge(Mockito.any(OrderEntity.class))).thenReturn(new OrderEntity());

        OrderEntity orderEntityToSave = new OrderEntity();
        orderEntityToSave.setOrderItems(new HashSet<>());
        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setId(1);
        orderEntityToSave.setTruck(truckEntity);
        orderService.save(orderEntityToSave, new String[]{"1", "2"});
    }

    @Test(expected = DriverAllreadyUsedException.class)
    public void testSaveExceptionDriver() throws Exception {
        when(orderDao.findByTruckId(Mockito.anyInt())).thenReturn(null);

        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setCurrentOrder(new OrderEntity());
        when(driverDao.findById(DriverEntity.class, 1)).thenReturn(driverEntity);
        when(orderDao.merge(Mockito.any(OrderEntity.class))).thenReturn(new OrderEntity());

        OrderEntity orderEntityToSave = new OrderEntity();
        orderEntityToSave.setOrderItems(new HashSet<>());
        TruckEntity truckEntity = new TruckEntity();
        truckEntity.setId(1);
        orderEntityToSave.setTruck(truckEntity);

        orderService.save(orderEntityToSave, new String[]{"1", "2"});
    }


    @Test
    public void testFindAll() throws Exception {
        List<OrderEntity> orders = new ArrayList<>();
        OrderEntity order1 = new OrderEntity();
        OrderEntity order2 = new OrderEntity();
        orders.add(order1);
        orders.add(order2);
        when(orderDao.findAll()).thenReturn(orders);
        Assert.assertEquals(orders, orderService.findAll());

    }
}