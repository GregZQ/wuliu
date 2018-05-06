package com.df.dao;


import com.df.domain.Detail;
import com.df.domain.Ticket;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Mapper
public interface DetailDao {

    @Insert("insert into t_invoice(time,goods_id," +
            "from_name,from_place,from_phone,to_name,to_place,to_phone,goods_name," +
            "goods_count,goods_cube,goods_weight,goods_unitprice,send_type," +
            "write_back,pay_type,pay_count,collection,consignee,consignee_phone)" +
            "values(#{date},#{goodId},#{fromName},#{fromPlace},#{fromPhone}," +
            "#{toName},#{toPlace},#{toPhone},#{goodsName},#{goodsCount},#{goodsCube}," +
            "#{goodsWeight},#{goodsUnitprice},#{sendType},#{writeBack},#{payType}," +
            "#{payCount},#{collection},#{consignee},#{consigneePhone})")
    int insert(Ticket ticket);

    @Select("select id,sendtime as sendTime,time as date,goods_id as goodId,from_name as fromName,from_place as fromPlace,from_phone as fromPhone," +
            "to_name as toName,to_place as toPlace,to_phone as toPhone,goods_name as goodsName,goods_count as goodsCount," +
            "goods_cube as goodsCube,goods_weight as goodsWeight,goods_unitprice as goodsUnitprice," +
            "send_type as sendType,write_back as writeBack,pay_type as payType,pay_count as payCount," +
            "collection as collection,consignee as consignee,consignee_phone as consigneePhone " +
            "from t_invoice order by id desc  limit #{start},#{nums}")
    @ResultType(Detail.class)
    List<Detail> listByTimeAndPage(@Param("start") Integer start, @Param("nums") Integer nums);

    @Select("select count(*) from t_invoice")
    Integer getAllCount();

    @Delete("delete from t_invoice where id=#{id}")
    Integer deleteDetailById(@Param("id") Integer id);

    @Update("update t_invoice set sid=#{sid},sendtime=#{date} where id=#{id}")
    void updateDateSidById(@Param("id") Integer id, @Param("sid") Integer sid, @Param("date")Date date);

    /*到这了*/
    @Update("update t_invoice set sendTime=null,sid=null where sid in (" +
            " select sid from t_list where load_time is null )")
    void refresh();

    @Select("select goods_id as goodId,goods_name as goodsName," +
            "goods_count as goodsCount,goods_weight as goodsWeight,goods_cube as goodsCube," +
            " pay_count as payCount,pay_type as payType,collection,send_type as sendType," +
            "write_back as writeBack,to_name as toName,to_place as toPlace,to_phone as toPhone," +
            "sendtime as sendTime " +
            " from t_invoice where t_invoice.sid=#{sid} order by id desc")
    List<Detail> findDetailListBySid(@Param("sid") Integer sid);

    @Delete("delete from t_invoice where sid=#{sid}")
    void deleteDetailBySid(@Param("sid") Integer sid);

    @Select("<script> select count(*) from t_invoice where 1=1 <when test='loadTime!=null'> and time=#{loadTime}</when> <when test='goodid!=null'> and goods_id  like '%${goodid}%'</when> </script>")
    int getCountByLoadTimeAndGoodid(@Param("loadTime") String loadTime,@Param("goodid") String goodid);

    @Select("<script> select time as date,id,goods_id as goodId from t_invoice where 1=1 <when test='loadTime!=null'> and time=#{loadTime} </when> <when test='goodid!=null'> and goods_id like '%${goodid}%' </when> order by id desc limit #{start},#{nums}  </script>")
    List<Detail> findDetailByLoadTimeAndGoodidAndPage(@Param("loadTime") String loadTime,@Param("goodid") String goodid,
                                                      @Param("start") int start, @Param("nums") Integer nums);

    @Select("select id,sendtime as sendTime,time as date,goods_id as goodId,from_name as fromName,from_place as fromPlace,from_phone as fromPhone," +
            "to_name as toName,to_place as toPlace,to_phone as toPhone,goods_name as goodsName,goods_count as goodsCount," +
            "goods_cube as goodsCube,goods_weight as goodsWeight,goods_unitprice as goodsUnitprice," +
            "send_type as sendType,write_back as writeBack,pay_type as payType,pay_count as payCount," +
            "collection as collection,consignee as consignee,consignee_phone as consigneePhone " +
            "from t_invoice where id=#{id}")
    Detail findDetailById(@Param("id") Integer id);

    @Delete("delete from t_invoice where id=#{id}")
    Integer deleteById(@Param("id") Integer id);
}
