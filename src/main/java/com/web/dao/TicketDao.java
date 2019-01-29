package com.web.dao;


import com.web.domain.Detail;
import com.web.domain.Ticket;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TicketDao {
    String  ticketMapper=" id,time as date,"+
            "goods_id as goodId,from_name as fromName,"+
            "from_place as fromPlace,from_phone as fromPhone,"+
            "to_name as toName,to_place as toPlace,"+
            "to_phone as toPhone,goods_name as goodsName,"+
            "goods_count as goodsCount,goods_cube as goodsCube,"+
            "goods_weight as goodsWeight,goods_unitprice as goodsUnitprice,"+
            "send_type as sendType,write_back as writeBack,"+
            "pay_type as payType,pay_count as payCount,"+
            "collection as collection,consignee as consignee,"+
            "consignee_phone as consigneePhone,state as state,"+
            "t_ticket.uid as uid,t_ticket.save_path as savePath,"+
            "t_ticket.sid as sid ";

    /**
     * 插入ticket
     * @param ticket
     * @return
     */
    @Insert("insert into t_ticket(time,goods_id," +
            "from_name,from_place,from_phone,to_name,to_place,to_phone,goods_name," +
            "goods_count,goods_cube,goods_weight,goods_unitprice,send_type," +
            "write_back,pay_type,pay_count,collection,consignee,consignee_phone,state,uid,save_path)" +
            "values(#{date},#{goodId},#{fromName},#{fromPlace},#{fromPhone}," +
            "#{toName},#{toPlace},#{toPhone},#{goodsName},#{goodsCount},#{goodsCube}," +
            "#{goodsWeight},#{goodsUnitprice},#{sendType},#{writeBack},#{payType}," +
            "#{payCount},#{collection},#{consignee},#{consigneePhone},#{state},#{uid},#{savePath})")
    int insert(Ticket ticket);


    /**
     * 根据用户id分页获取票据信息
     * @param start
     * @param nums
     * @param uid
     * @return
     */
    @Select(" select " +ticketMapper+ ",null as sendTime " +
            " from t_ticket " +
            " where t_ticket.uid=#{uid} and t_ticket.sid is null " +
            " union " +
            " select "+ ticketMapper +", send_time as sendTime "+
            " from t_ticket,t_sends " +
            " where t_ticket.uid=#{uid} and t_ticket.sid=t_sends.sid " +
            " order by id desc limit #{start},#{nums}")

    @ResultType(Detail.class)
    List<Detail> findByPageAndUid(@Param("start") Integer start, @Param("nums") Integer nums,
                                  @Param("uid") Integer uid);

    /**
     * 根据主键更新所属运输清单已经状态
     * @param id
     * @param sid
     * @param state
     */
    @Update("update t_ticket set sid=#{sid},state=#{state} where id=#{id}")
    void updateStateSidById(@Param("id") Integer id, @Param("sid") Integer sid, @Param("state") Integer state);

    /**
     * 刷新
     */
    @Update("update t_ticket set state=0,sid=null where sid in (" +
            " select sid from t_sends where load_time is null )")
    void refresh();

    /**
     * 根据运输清单主键获取本批次货物清单
     * @param sid
     * @return
     */
    @Select("select "+ ticketMapper +" ,send_time as sendTime " +
            " from t_ticket,t_sends " +
            " where t_ticket.sid=t_sends.sid and t_ticket.sid=#{sid} " +
            " order by id desc ")
    List<Detail> findDetailListBySid(@Param("sid") Integer sid);

    /**
     * 根据运输清单主键删除开票数据
     * @param sid
     */
    @Delete("delete from t_ticket where sid=#{sid}")
    void deleteDetailBySid(@Param("sid") Integer sid);

    /**
     * 根据用户主键条件查询票据数量
     * @param loadTime 装载时间
     * @param goodid 货物id
     * @param uid
     * @return
     */
    @Select("<script> select count(*) " +
            " from t_ticket " +
            " where uid=#{uid} " +
            " <when test='loadTime!=null'> and time=#{loadTime}</when> " +
            " <when test='goodid!=null'> and goods_id  like '%${goodid}%'</when> " +
            "</script>")
    int getCountByLoadTimeAndGoodidAndUid(@Param("loadTime") String loadTime, @Param("goodid") String goodid,@Param("uid") Integer uid);

    /**
     * 根据用户主键分页条件查询票据数量
     * @param loadTime 装载时间
     * @param goodid  货物id
     * @param start  开始位置
     * @param nums  数量
     * @param uid  用户主键
     * @return
     */
    @Select(" <script> select time as date,id," +
            " goods_id as goodId " +
            " from t_ticket where uid=#{uid} " +
            " <when test='loadTime!=null'> and time=#{loadTime} </when> " +
            " <when test='goodid!=null'> and goods_id like '%${goodid}%' </when> " +
            "  order by id desc limit #{start},#{nums} " +
            " </script>")
    List<Detail> findDetailByLoadTimeAndGoodidAndPageAndUid(@Param("loadTime") String loadTime,
                                                            @Param("goodid") String goodid,
                                                            @Param("start") int start,
                                                            @Param("nums") Integer nums,
                                                            @Param("uid") Integer uid);

    /**
     * 根据主键获取票据
     * @param id
     * @return
     */
    @Select("select " + ticketMapper +" from t_ticket where id=#{id}")
    Ticket findDetailById(@Param("id") Integer id);

    /**
     * 根据主键删除票据
     * @param id
     * @return
     */
    @Delete("delete from t_ticket where id=#{id}")
    Integer deleteById(@Param("id") Integer id);

    /**
     * 根据用户主键获取票据数量
     * @param uid
     * @return
     */
    @Select("select count(*) from t_ticket where uid=#{uid}")
    Integer getAllCountByUid(Integer uid);
}
