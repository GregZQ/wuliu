package com.df.dao;

import com.df.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户dao
 */
@Mapper
public interface UserDao {

    @Select("select * from t_user where id = #{id}")
    User findUserById(Integer id);

    @Insert("insert into t_user(username,password,company) " +
            "values(#{username},#{password},#{company})")
    void insert(User user);

    @Select("select * from t_user where username=#{username}")
    User findUserByUsername(@Param("username") String username);
}