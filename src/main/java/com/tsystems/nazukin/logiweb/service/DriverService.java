package com.tsystems.nazukin.logiweb.service;

import com.tsystems.nazukin.logiweb.JPAUtil;
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
import com.tsystems.nazukin.logiweb.model.entity.EmployeeEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderEntity;
import com.tsystems.nazukin.logiweb.model.enums.DriverStatus;
import com.tsystems.nazukin.logiweb.model.enums.EmployeeType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Service that provides functionality for working with {@link DriverEntity}, that representing driver's data.
 */
public class DriverService {

    /**
     * Max work time for driver in a month.
     */
    private static final Integer MAX_WORK_TIME = 176;

    /**
     * Data access object for driver's data
     */
    private final DriverDao driverDao;
    /**
     * Data access object for city's data
     */
    private final CityDao cityDao;
    /**
     * Data access object for employee's data
     */
    private final EmployeeDao employeeDao;

    /**
     * Constructs service.
     */
    public DriverService() {
        driverDao = new DriverDaoImpl();
        cityDao = new CityDaoImpl();
        employeeDao = new EmployeeDaoImpl();
    }

    /**
     * Constructs service.
     *
     * @param driverDao   data access object for driver's data
     * @param cityDao     data access object for citiy's data
     * @param employeeDao data access object for employee's data
     */
    public DriverService(DriverDao driverDao, CityDao cityDao, EmployeeDao employeeDao) {
        this.driverDao = driverDao;
        this.cityDao = cityDao;
        this.employeeDao = employeeDao;
    }

    /**
     * Make and saves driver from specified employee. Sets driver's work time to 0, status to 'FREE'.
     * Generates driver's serial number.
     *
     * @param employeeId - employee's id
     * @param cityId     - city's id
     */
    public void save(Integer employeeId, Integer cityId) {
        try {
            JPAUtil.beginTransaction();
            DriverEntity driverEntity = new DriverEntity();
            CityEntity cityEntity = cityDao.findById(CityEntity.class, cityId);
            EmployeeEntity employeeEntity = employeeDao.findById(EmployeeEntity.class, employeeId);
            Integer serialNumber = driverDao.maxSerialNumber();
            if (serialNumber == null) {
                serialNumber = 0;
            }

            driverEntity.setCurrentCity(cityEntity);
            driverEntity.setEmployee(employeeEntity);
            driverEntity.setStatus(DriverStatus.FREE);
            driverEntity.setWorkTime(0);
            driverEntity.setSerialNumber(serialNumber + 1);

            employeeEntity.setEmployeeType(EmployeeType.DRIVER);
            driverDao.save(driverEntity);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
    }

    /**
     * Finds all drivers from specified city.
     *
     * @param id city's id
     * @return a list of drivers from specified city
     */
    public List<DriverEntity> findAllByCityId(Integer id) {
        List<DriverEntity> result;
        try {
            JPAUtil.beginTransaction();
            result = driverDao.findByCityId(id);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return result;
    }

    /**
     * Finds {@link DriverEntity} with specified id.
     *
     * @param id driver's  id
     * @return driver with specified id
     */
    public DriverEntity find(Integer id) {
        DriverEntity result;
        try {
            JPAUtil.beginTransaction();
            result = driverDao.findById(DriverEntity.class, id);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return result;
    }

    /**
     * Updates the specified driver.
     *
     * @param id         driver's id
     * @param firstName  driver's new first name
     * @param secondName driver's new second name
     * @param workTime   driver's new work time
     * @param cityId     driver's new city
     */
    public void update(String id, String firstName, String secondName, String workTime, String cityId) {
        try {
            JPAUtil.beginTransaction();
            DriverEntity driverEntity = driverDao.findById(DriverEntity.class, Integer.parseInt(id));
            driverEntity.getEmployee().setFirstName(firstName);
            driverEntity.getEmployee().setSecondName(secondName);
            driverEntity.setWorkTime(Integer.parseInt(workTime));
            driverEntity.setCurrentCity(cityDao.findById(CityEntity.class, Integer.parseInt(cityId)));
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
    }

    /**
     * Deletes the specified driver.
     *
     * @param id driver's id
     * @throws DriverAllreadyUsedException if we want to delete driver, which used in order
     */
    public void delete(Integer id) {
        try {
            JPAUtil.beginTransaction();
            DriverEntity entity = driverDao.findById(DriverEntity.class, id);
            ///check that driver don't have any order now
            if (entity.getCurrentOrder() != null) {
                throw new DriverAllreadyUsedException();
            }

            driverDao.delete(entity);
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
    }

    /**
     * Selects drivers for the order. The driver does not perform other orders at a the time
     * and working hours should not exceed 176 hours at the end of the order.
     *
     * @param orderEntity order's data
     * @return a list of suitable drivers for order
     */
    public List<DriverEntity> findForOrder(OrderEntity orderEntity) {
        Date startTime = orderEntity.getStartTime();
        Integer duration = orderEntity.getDuration();
        List<DriverEntity> drivers;
        List<DriverEntity> result = new ArrayList<>();

        try {
            JPAUtil.beginTransaction();
            drivers = driverDao.findForOrder(orderEntity.getStartCity().getId());
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        //sets start time and end time of order
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(startTime);
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTimeInMillis(startTime.getTime() + TimeUnit.HOURS.toMillis(duration));

        //if startTime of order is not in next month
        if (Calendar.getInstance().get(Calendar.MONTH) == calendarStart.get(Calendar.MONTH)) {
            //if end of order in the next month
            long diffHours = duration;
            if (calendarStart.get(Calendar.MONTH) != calendarEnd.get(Calendar.MONTH)) {
                //set time to start of neext month
                calendarEnd.set(Calendar.DAY_OF_MONTH, calendarEnd.getActualMinimum(Calendar.DAY_OF_MONTH));
                calendarEnd.set(Calendar.HOUR_OF_DAY, calendarEnd.getActualMinimum(Calendar.HOUR_OF_DAY));
                calendarEnd.set(Calendar.MINUTE, calendarEnd.getActualMinimum(Calendar.MINUTE));
                calendarEnd.set(Calendar.SECOND, calendarEnd.getActualMinimum(Calendar.SECOND));

                long diff = calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis();
                diffHours = TimeUnit.MILLISECONDS.toHours(diff);
            }

            for (DriverEntity driver : drivers) {
                if (driver.getWorkTime() + diffHours <= MAX_WORK_TIME) {
                    result.add(driver);
                }
            }
        } else {
            result = drivers;
        }
        return result;
    }

    /**
     * Finds driver with specified emplooyee's id and checks, that correct serial number has been entered.
     *
     * @param employeeId   employee's id
     * @param serialNumber driver's serial number
     * @return driver's data, if serial number was correct
     * @throws WrongSerialNumberException if serial number was wrong
     */
    public DriverEntity findByEmployeeIdAndSerial(Integer employeeId, Integer serialNumber) {
        DriverEntity result;
        try {
            JPAUtil.beginTransaction();
            result = driverDao.findByEmployeeId(employeeId);
            if (result.getSerialNumber() != serialNumber) {
                throw new WrongSerialNumberException();
            }
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return result;
    }

    /**
     * Finds all drivers.
     *
     * @return a list of drivers
     */
    public List<DriverEntity> findAll() {
        List<DriverEntity> result;
        try {
            JPAUtil.beginTransaction();
            result = driverDao.findAll();
            JPAUtil.commitTransaction();
        } finally {
            JPAUtil.rollbackTransaction();
            JPAUtil.closeEntityManager();
        }
        return result;
    }
}
