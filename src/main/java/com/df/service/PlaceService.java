package com.df.service;

import com.df.dao.PlaceDao;
import com.df.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    @Autowired
    private PlaceDao placeDao;

    public List<Place> findByUid(Integer id) {
        return  this.placeDao.findByUid(id);
    }

    public void addPlace(Place place) {
        this.placeDao.addPlace(place);
    }

    public void deleteById(Integer id) {
        this.placeDao.deleteById(id);
    }
}
