package com.tsystems.nazukin.logiweb.service;

import com.tsystems.nazukin.logiweb.customexceptions.DriverAllreadyUsedException;
import com.tsystems.nazukin.logiweb.customexceptions.WrongSerialNumberException;
import com.tsystems.nazukin.logiweb.model.dao.implementations.CityDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.implementations.DriverDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.implementations.EmployeeDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.CityDao;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.DriverDao;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.EmployeeDao;
import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.model.entity.DriverEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by 1 on 29.02.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DriverServiceTest {

    private DriverService driverService;
    private DriverDao driverDao;
    private CityDao cityDao;
    private EmployeeDao employeeDao;

    private OrderEntity orderEntity;

    @Before
    public void init(){
        driverDao = mock(DriverDaoImpl.class);
        cityDao = mock(CityDaoImpl.class);
        employeeDao = mock(EmployeeDaoImpl.class);
        driverService = new DriverService(driverDao, cityDao, employeeDao);
    }


    @Test
    public void testFindForOrder() throws Exception {

        List<DriverEntity> driversForOrder = new ArrayList<>();
        DriverEntity driverEntity1 = new DriverEntity();
        DriverEntity driverEntity2 = new DriverEntity();
        DriverEntity driverEntity3 = new DriverEntity();
        driverEntity1.setWorkTime(150);
        driverEntity2.setWorkTime(170);
        driverEntity3.setWorkTime(160);
        driversForOrder.add(driverEntity1);
        driversForOrder.add(driverEntity2);
        driversForOrder.add(driverEntity3);
        when(driverDao.findForOrder(Mockito.anyInt())).thenReturn(driversForOrder);

        OrderEntity orderEntity = new OrderEntity();
        //set the end of this month
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.add(Calendar.MONTH, 1);
        calendarEnd.set(Calendar.DAY_OF_MONTH, calendarEnd.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendarEnd.set(Calendar.HOUR_OF_DAY, calendarEnd.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendarEnd.set(Calendar.MINUTE, calendarEnd.getActualMinimum(Calendar.MINUTE));
        calendarEnd.set(Calendar.SECOND, calendarEnd.getActualMinimum(Calendar.SECOND));
        calendarEnd.add(Calendar.DAY_OF_YEAR, -1);

        orderEntity.setStartTime(calendarEnd.getTime());
        orderEntity.setDuration(6);
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId(1);
        orderEntity.setStartCity(cityEntity);
        assertEquals(driversForOrder, driverService.findForOrder(orderEntity));


        driversForOrder.remove(driverEntity2);
        orderEntity.setDuration(16);
        assertEquals(driversForOrder, driverService.findForOrder(orderEntity));


        driversForOrder.remove(driverEntity3);
        orderEntity.setDuration(36);
        assertEquals(driversForOrder, driverService.findForOrder(orderEntity));


        //check start in the next month
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        orderEntity.setStartTime(calendar.getTime());
        driversForOrder.add(driverEntity2);
        driversForOrder.add(driverEntity3);
        orderEntity.setDuration(36);

        assertEquals(driversForOrder, driverService.findForOrder(orderEntity));

    }

    @Test(expected = DriverAllreadyUsedException.class)
    public void testDelete() throws Exception {
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setCurrentOrder(new OrderEntity());

        when(driverDao.findById(DriverEntity.class, 1)).thenReturn(driverEntity);

        driverService.delete(1);
    }

    @Test
    public void testFindAll() throws Exception {
        List<DriverEntity> drivers = new ArrayList<>();
        DriverEntity driverEntity1 = new DriverEntity();
        DriverEntity driverEntity2 = new DriverEntity();
        drivers.add(driverEntity1);
        drivers.add(driverEntity2);
        when(driverDao.findAll()).thenReturn(drivers);

        assertEquals(drivers, driverService.findAll());
    }

    @Test
    public void testFindByEmployeeIdAndSerial() throws Exception {
        Integer serialNumber = 15;
        DriverEntity driver1 = new DriverEntity();
        driver1.setSerialNumber(serialNumber);

        when(driverDao.findByEmployeeId(1)).thenReturn(driver1);
        assertEquals(driver1, driverService.findByEmployeeIdAndSerial(1, serialNumber));

    }

    @Test(expected = WrongSerialNumberException.class)
    public void testFindByEmployeeIdAndSerialException() throws Exception {
        Integer serialNumber = 15;
        Integer serialNumberOther = 14;
        DriverEntity driver1 = new DriverEntity();
        driver1.setSerialNumber(serialNumber);

        when(driverDao.findByEmployeeId(1)).thenReturn(driver1);
        assertEquals(driver1, driverService.findByEmployeeIdAndSerial(1, serialNumberOther));

    }

    @Test
    public void testFindAllByCityId() throws Exception {
        List<DriverEntity> drivers = new ArrayList<>();
        DriverEntity driverEntity1 = new DriverEntity();
        DriverEntity driverEntity2 = new DriverEntity();
        drivers.add(driverEntity1);
        drivers.add(driverEntity2);
        when(driverDao.findByCityId(1)).thenReturn(drivers);

        assertEquals(drivers, driverService.findAllByCityId(1));
    }

    @Test
    public void testFind() throws Exception {
        DriverEntity driverEntity = new DriverEntity();

        when(driverDao.findById(DriverEntity.class, 1)).thenReturn(driverEntity);

        assertEquals(driverEntity, driverService.find(1));
    }

}