package com.df.service;


import com.df.dao.DesDao;
import com.df.domain.Des;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesService {

    @Autowired
    private DesDao desDao;

    public List<Des> findByUid(Integer id) {
        return desDao.findByUid(id);
    }

    public void addDes(Des des) {
        this.desDao.addDes(des);
    }

    public void deleteById(Integer id) {
        this.desDao.deleteById(id);
    }
}
