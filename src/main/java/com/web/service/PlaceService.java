package com.web.service;

import com.web.dao.PlaceDao;
import com.web.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceDao placeDao;

    /**
     * 根据id查询订单发/收地址
     * @param id
     * @return
     */
    public List<Place> findByUid(Integer id) {
        return  this.placeDao.findByUid(id);
    }

    /**
     * 添加订单发/收地址
     * @param place
     */
    public void addPlace(Place place) {
        this.placeDao.addPlace(place);
    }

    /**
     * 根据id删除订单发/收地址
     * @param id
     */
    public void deleteById(Integer id) {
        this.placeDao.deleteById(id);
    }
}
