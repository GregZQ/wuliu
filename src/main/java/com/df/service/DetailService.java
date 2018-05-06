package com.df.service;

import com.df.dao.DetailDao;
import com.df.domain.Detail;
import com.df.domain.SendList;
import com.df.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DetailService {

    @Autowired
    private DetailDao detailDao;

    public void addTicket(Ticket ticket) {
        this.detailDao.insert(ticket);
    }

    public List listByTimeAndPage(Integer start, Integer nums) {
       return  this.detailDao.listByTimeAndPage(start,nums);
    }

    public Integer getAllCount(){
        return this.detailDao.getAllCount();
    }

    public Integer deleteDetailById(Integer id) {
            return  this.detailDao.deleteDetailById(id);
    }

    public void updateDateSidById(Integer id,Integer sid,Date currentDate) {

         this.detailDao.updateDateSidById(id,sid,currentDate);
    }

    public void refresh() {
        this.detailDao.refresh();
    }
    public List<Detail>findDetailListBySid(Integer sid){
        return this.detailDao.findDetailListBySid(sid);
    }

    public int getCountByLoadTimeAndGoodid(String loadTime, String goodid) {
        return this.detailDao.getCountByLoadTimeAndGoodid(loadTime,goodid);
    }

    public List<Detail> findDetailByLoadTimeAndGoodidAndPage(String loadTime, String goodid, int start, Integer nums) {
        return this.detailDao.findDetailByLoadTimeAndGoodidAndPage(loadTime,goodid,start,nums);
    }

    public Detail findDetailById(Integer id) {
        return  this.detailDao.findDetailById(id);
    }

    public Integer deleteById(Integer id) {
        return  this.detailDao.deleteById(id);
    }
}
