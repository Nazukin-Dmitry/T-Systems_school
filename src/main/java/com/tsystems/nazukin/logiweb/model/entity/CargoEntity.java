package com.tsystems.nazukin.logiweb.model.entity;

import com.tsystems.nazukin.logiweb.model.enums.CargoStatus;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 1 on 15.02.2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "CargoEntity.findByOrderId", query = "SELECT DISTINCT a FROM CargoEntity a " +
                "INNER JOIN a.orderItemEntities b WHERE b.order.id=:id ")
})
@Table(name = "cargos", schema = "logiwebdb", catalog = "")
public class CargoEntity {
    private Integer id;
    private String name;
    private Integer weight;
    private CargoStatus status;

    private List<OrderItemEntity> orderItemEntities;

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
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "weight", nullable = false)
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    public CargoStatus getStatus() {
        return status;
    }

    public void setStatus(CargoStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    @OneToMany(mappedBy = "cargo")
    public List<OrderItemEntity> getOrderItemEntities() {
        return orderItemEntities;
    }

    public void setOrderItemEntities(List<OrderItemEntity> orderItemEntities) {
        this.orderItemEntities = orderItemEntities;
    }
}
