package com.df.dao;

import com.df.domain.Place;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlaceDao {

    @Select("select * from t_place where uid=#{uid}")
    List<Place> findByUid(@Param("uid") Integer uid);

    @Insert("insert into t_place(place,uid)values(#{place},#{uid})")
    void addPlace(Place place);

    @Delete("delete from t_place where id=#{id}")
    void deleteById(@Param("id") Integer id);
}
