package com.tsystems.nazukin.logiweb.service;

import com.tsystems.nazukin.logiweb.model.dao.implementations.CityDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.implementations.MapDaoImpl;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.CityDao;
import com.tsystems.nazukin.logiweb.model.dao.interfaces.MapDao;
import com.tsystems.nazukin.logiweb.model.entity.CityEntity;
import com.tsystems.nazukin.logiweb.model.entity.MapEntity;
import com.tsystems.nazukin.logiweb.model.entity.OrderItemEntity;
import com.tsystems.nazukin.logiweb.model.enums.OrderItemType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by 1 on 01.03.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MapServiceTest {

    private MapDao mapDao;
    private CityDao cityDao;

    private MapService mapService;

    @Before
    public void init() throws Exception{
        mapDao = mock(MapDaoImpl.class);
        cityDao = mock(CityDaoImpl.class);
        mapService = new MapService(mapDao, cityDao);
    }

    @Test
    public void testGetInterval() throws Exception {
        Integer cityId1 = 1;
        Integer cityId2 = 2;
        Integer interval = 24;
        Assert.assertEquals(Integer.valueOf(0), mapService.getInterval(cityId1, cityId1));

        MapEntity mapEntity = new MapEntity();
        mapEntity.setIntervalValue(interval);
        when(mapDao.findByIds(cityId1, cityId2)).thenReturn(mapEntity);
        when(mapDao.findByIds(cityId2, cityId1)).thenReturn(mapEntity);
        Assert.assertEquals(interval, mapService.getInterval(cityId1, cityId2));

    }

    @Test
    public void testDuration() throws Exception {
        CityEntity moscow = new CityEntity();
        moscow.setId(1);
        CityEntity saintP = new CityEntity();
        saintP.setId(2);

        OrderItemEntity item1 = new OrderItemEntity();
        item1.setType(OrderItemType.LOADING);
        item1.setCity(moscow);
        OrderItemEntity item2 = new OrderItemEntity();
        item2.setType(OrderItemType.UNLOADING);
        item2.setCity(saintP);

        List<OrderItemEntity> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        MapEntity mapEntity = new MapEntity();
        mapEntity.setIntervalValue(24);
        when(mapDao.findByIds(1, 2)).thenReturn(mapEntity);
        when(mapDao.findByIds(2, 1)).thenReturn(mapEntity);

        Assert.assertEquals(Integer.valueOf(28), mapService.duration(items));

        OrderItemEntity item3 = new OrderItemEntity();
        item3.setCity(moscow);
        item3.setType(OrderItemType.TRANSIT);
        items.add(item3);

        Assert.assertEquals(Integer.valueOf(28+24), mapService.duration(items));
    }
}