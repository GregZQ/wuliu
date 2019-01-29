package com.web.service;


import com.web.dao.DesDao;
import com.web.domain.Des;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesService {

    @Autowired
    private DesDao desDao;

    /**
     * 根据id查询卸货/装货地址
     * @param id
     * @return
     */
    public List<Des> findByUid(Integer id) {
        return desDao.findByUid(id);
    }

    /**
     * 添加卸货/装货地址
     * @param des
     */
    public void addDes(Des des) {
        this.desDao.addDes(des);
    }

    /**
     * 根据id删除卸货/装货地址
     * @param id
     */
    public void deleteById(Integer id) {
        this.desDao.deleteById(id);
    }
}
