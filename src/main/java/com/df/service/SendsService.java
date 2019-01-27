package com.df.service;

import com.df.dao.SendsDao;
import com.df.dao.TicketDao;
import com.df.domain.Sends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SendsService {
    @Autowired
    private SendsDao sendsDao;
    @Autowired
    private TicketDao ticketDao;
    public Integer createNew(Sends sends) {
        return this.sendsDao.createNew(sends);
    }

/*    public List<Sends> findSendListBySid(Integer sid) {
        return this.sendsDao.findSendListBySid(sid);
    }*/

    public void refresh() {
        this.sendsDao.refresh();
    }

    public Sends findSendListById(Integer id) {
        return this.sendsDao.findSendListById(id);
    }

    public void updateSendList(Sends sends) {
        this.sendsDao.updateSendList(sends);
    }

    public List<Sends> queryHistoryByTimeAndCarIdAndUid(String loadTime, String carid, int start, int nums,Integer uid) {
        return this.sendsDao.queryHistoryByTimeAndCarIdAndUid(loadTime,carid,start,nums,uid);
    }

    public int getCountByTimeAndCarIdAndUid(String loadTime, String carid,Integer uid) {
        return this.sendsDao.getCountByTimeAndCarIdAndUid(loadTime,carid,uid);
    }

    public Integer deleteBySid(Integer sid) {
        this.ticketDao.deleteDetailBySid(sid);
        Integer num= this.sendsDao.deleteBySid(sid);
        return num;
    }
}