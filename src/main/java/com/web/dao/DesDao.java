package com.web.dao;


import com.web.domain.Des;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DesDao {

    @Select(" select id,load_place as loadPlace,uid " +
            " from t_des " +
            " where uid=#{uid} ")
    List<Des> findByUid(@Param("uid") Integer uid);

    @Insert("insert into t_des(load_place,uid)values(#{loadPlace},#{uid})")
    void addDes(Des des);

    @Delete("delete from t_des where id=#{id}")
    void deleteById(@Param("id") Integer id);
}
