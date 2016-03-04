package com.tsystems.nazukin.logiweb.model.entity;

import com.tsystems.nazukin.logiweb.model.enums.DriverStatus;

import javax.persistence.*;

/**
 * Created by 1 on 11.02.2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "DriverEntity.findMaxSerial", query = "SELECT MAX(c.serialNumber) FROM DriverEntity c"),
        @NamedQuery(name = "DriverEntity.findByCityId", query = "SELECT c FROM DriverEntity c WHERE c.currentCity.id=:id"),
        @NamedQuery(name = "DriverEntity.findForOrder", query = "SELECT c FROM DriverEntity c " +
                "WHERE c.currentCity.id=:id AND c.currentOrder IS NULL"),
        @NamedQuery(name = "DriverEntity.findByEmployeeId", query = "SELECT c FROM DriverEntity c WHERE c.employee.id=:id"),
        @NamedQuery(name = "DriverEntity.findAll", query = "SELECT c FROM DriverEntity c")

})
@Table(name = "drivers", schema = "logiwebdb", catalog = "")
public class DriverEntity {
    private int id;
    private int workTime;
    private DriverStatus status;
    private int serialNumber;

    private EmployeeEntity employee;
    private CityEntity currentCity;
    private OrderEntity currentOrder;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "work_time")
    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    @Basic
    @Column(name = "serial_number")
    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employeesByEmployeeId) {
        this.employee = employeesByEmployeeId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    public CityEntity getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(CityEntity currentCity) {
        this.currentCity = currentCity;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    public OrderEntity getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(OrderEntity currentOrder) {
        this.currentOrder = currentOrder;
    }
}
