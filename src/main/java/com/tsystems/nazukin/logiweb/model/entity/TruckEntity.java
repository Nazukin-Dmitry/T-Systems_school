package com.tsystems.nazukin.logiweb.model.entity;

import com.tsystems.nazukin.logiweb.model.enums.TruckState;

import javax.persistence.*;

/**
 * Created by 1 on 15.02.2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "TruckEntity.findByRegNumber", query = "SELECT c FROM TruckEntity c WHERE c.regNumber=:regNumber"),
        @NamedQuery(name = "TruckEntity.findByCityId", query = "SELECT c FROM TruckEntity c WHERE c.currentCity.id=:id"),
        @NamedQuery(name = "TruckEntity.findForOrder", query = "SELECT c FROM TruckEntity c LEFT JOIN c.currentOrder d " +
                "WHERE c.currentCity.id=:id AND c.state = 'WORK' AND (d.truck IS NULL)"),
        @NamedQuery(name = "TruckEntity.findAll", query = "SELECT c FROM TruckEntity c")
})
@Table(name = "trucks", schema = "logiwebdb", catalog = "")
public class TruckEntity {
    private Integer id;
    private String regNumber;
    private TruckState state;
    private Integer capacity;
    private Integer driverCount;

    private OrderEntity currentOrder;
    private CityEntity currentCity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "reg_number", nullable = false, length = 45)
    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    @Basic
    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    public TruckState getState() {
        return state;
    }

    public void setState(TruckState state) {
        this.state = state;
    }

    @Basic
    @Column(name = "capacity", nullable = false)
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "driver_count", nullable = false)
    public Integer getDriverCount() {
        return driverCount;
    }

    public void setDriverCount(Integer driverCount) {
        this.driverCount = driverCount;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    public CityEntity getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(CityEntity currentCity) {
        this.currentCity = currentCity;
    }


    @OneToOne(mappedBy = "truck", fetch = FetchType.EAGER)
    public OrderEntity getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(OrderEntity currentOrder) {
        this.currentOrder = currentOrder;
    }
}
