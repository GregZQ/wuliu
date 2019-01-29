package com.web.service;

import com.web.dao.SendsDao;
import com.web.dao.TicketDao;
import com.web.domain.Sends;
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

    /**
     * 订单刷新
     */
    public void refresh() {
        this.sendsDao.refresh();
    }

    /**
     * 根据id查询发货订单
     * @param id
     * @return
     */
    public Sends findSendsById(Integer id) {
        return this.sendsDao.findSends(id);
    }

    /**
     * 更新发货订单
     * @param sends
     */
    public void updateSends(Sends sends) {
        this.sendsDao.updateSends(sends);
    }

    /**
     * 分页查询发货订单
     * @param loadTime
     * @param carid
     * @param start
     * @param nums
     * @param uid
     * @return
     */
    public List<Sends> queryHistoryByTimeAndCarIdAndUid(String loadTime, String carid, int start, int nums,Integer uid) {
        return this.sendsDao.queryHistoryByTimeAndCarIdAndUid(loadTime,carid,start,nums,uid);
    }

    /**
     * 分页查询发货订单数量
     * @param loadTime
     * @param carid
     * @param uid
     * @return
     */
    public int getCountByTimeAndCarIdAndUid(String loadTime, String carid,Integer uid) {
        return this.sendsDao.getCountByTimeAndCarIdAndUid(loadTime,carid,uid);
    }

    /**
     * 根据id删除发货订单
     * @param sid
     * @return
     */
    public Integer deleteBySid(Integer sid) {
        this.ticketDao.deleteDetailBySid(sid);
        Integer num= this.sendsDao.deleteBySid(sid);
        return num;
    }
}