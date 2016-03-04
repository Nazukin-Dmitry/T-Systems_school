package com.tsystems.nazukin.logiweb.model.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 1 on 14.02.2016.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "CityEntity.findAll", query = "SELECT c FROM CityEntity c ORDER BY c.name"),
        @NamedQuery(name = "CityEntity.findAllWithout", query = "SELECT c FROM CityEntity c WHERE c.id<>:city_id")
})
@Table(name = "cities", schema = "logiwebdb", catalog = "")
public class CityEntity {
    private Integer id;
    private String name;

    private List<TruckEntity> trucks;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityEntity that = (CityEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currentCity")
    public List<TruckEntity> getTrucks() {
        return trucks;
    }

    public void setTrucks(List<TruckEntity> trucks) {
        this.trucks = trucks;
    }

}
