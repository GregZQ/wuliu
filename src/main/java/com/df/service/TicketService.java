package com.df.service;

import com.df.dao.TicketDao;
import com.df.domain.Detail;
import com.df.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    /**
     * 添加货物
     * @param ticket
     */
    public void addTicket(Ticket ticket) {
        this.ticketDao.insert(ticket);
    }

    /**
     * 分页查询用户货物
     * @param start 开始位置
     * @param nums  查询数量
     * @param uid  用户id
     * @return
     */
    public List<Detail> findByPageAndUid(Integer start, Integer nums, Integer uid) {
       return  this.ticketDao.findByPageAndUid(start,nums,uid);
    }

    /**
     * 根据发票id更新货物所属发货订单和状态
     * @param id 发票id
     * @param sid 发货订单id
     * @param code 货物状态
     */
    public void updateStateSidById(Integer id, Integer sid, Integer code) {
         this.ticketDao.updateStateSidById(id,sid,code);
    }

    /**
     * 刷新发票
     */
    public void refresh() {
        this.ticketDao.refresh();
    }

    /**
     * 根据发货订单id查询这批货物
     * @param sid  发货订单id
     * @return
     */
    public List<Detail>findDetailListBySid(Integer sid){
        return this.ticketDao.findDetailListBySid(sid);
    }

    /**
     * 根据id查询发票
     * @param id
     * @return
     */
    public Ticket findDetailById(Integer id) {
        return  this.ticketDao.findDetailById(id);
    }

    /**
     * 根据id删除发票
     * @param id
     * @return
     */
    public Integer deleteById(Integer id) {
        return  this.ticketDao.deleteById(id);
    }

    /**
     * 查询当前用户的开票数量
     * @param uid
     * @return
     */
    public Integer getAllCountByUid(Integer uid){
       return this.ticketDao.getAllCountByUid(uid);
    }

    public int getCountByLoadTimeAndGoodidAndUid(String loadTime, String goodid,Integer uid) {
        return this.ticketDao.getCountByLoadTimeAndGoodidAndUid(loadTime,goodid,uid);
    }


    public List<Detail> findDetailByLoadTimeAndGoodidAndPageAndUid(String loadTime, String goodid, int start, Integer nums,Integer uid) {
        return this.ticketDao.findDetailByLoadTimeAndGoodidAndPageAndUid(loadTime,goodid,start,nums,uid);
    }
}
