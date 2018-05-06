package com.df.service;

import com.df.dao.DetailDao;
import com.df.dao.SendDao;
import com.df.domain.SendList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendService {
    @Autowired
    private SendDao sendDao;
    @Autowired
    private DetailDao detailDao;
    public Integer createNew(SendList sendList) {
        return this.sendDao.createNew(sendList);
    }

/*    public List<SendList> findSendListBySid(Integer sid) {
        return this.sendDao.findSendListBySid(sid);
    }*/

    public void refresh() {
        this.sendDao.refresh();
    }

    public SendList findSendListById(Integer id) {
        return this.sendDao.findSendListById(id);
    }

    public void updateSendList(SendList sendList) {
        this.sendDao.updateSendList(sendList);
    }

    public List<SendList> queryHistoryByTimeAndCarId(String loadTime, String carid,int start,int nums) {
        return this.sendDao.queryHistoryByTimeAndCarId(loadTime,carid,start,nums);
    }

    public int getCountByTimeAndCarId(String loadTime,String carid) {
        return this.sendDao.getCountByTimeAndCarId(loadTime,carid);
    }

    public Integer deleteBySid(Integer sid) {
        this.detailDao.deleteDetailBySid(sid);
        Integer num= this.sendDao.deleteBySid(sid);
        return num;
    }
}