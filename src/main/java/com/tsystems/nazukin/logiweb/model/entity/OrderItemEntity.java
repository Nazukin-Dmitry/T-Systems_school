package com.tsystems.nazukin.logiweb.model.entity;

import com.tsystems.nazukin.logiweb.model.enums.OrderItemType;

import javax.persistence.*;

/**
 * Created by 1 on 15.02.2016.
 */
@Entity
@Table(name = "order_item", schema = "logiwebdb", catalog = "")
public class OrderItemEntity {
    private Integer id;
    private Integer sequenceNumber;
    private OrderItemType type;

    private OrderEntity order;
    private CargoEntity cargo;
    private CityEntity city;

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
    @Column(name = "sequence_number", nullable = false)
    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    public OrderItemType getType() {
        return type;
    }

    public void setType(OrderItemType type) {
        this.type = type;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id")
    public CargoEntity getCargo() {
        return cargo;
    }

    public void setCargo(CargoEntity cargo) {
        this.cargo = cargo;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

}
