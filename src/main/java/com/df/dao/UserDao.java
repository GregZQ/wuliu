package com.df.dao;

import com.df.domain.User;
import org.apache.ibatis.annotations.*;

/**
 * 用户dao
 */
@Mapper
public interface UserDao {

    @Select("select * from t_user where id = #{id}")
    User findUserById(Integer id);

    @Insert("insert into t_user(username,password,company) " +
            "values(#{username},#{password},#{company})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(User user);

    @Select("select * from t_user where username=#{username}")
    User findUserByUsername(@Param("username") String username);

    @Update("update t_user set ticket=#{ticket},fuben=#{fuben},end=#{end} " +
            "where id=#{id}")
    void updatePath(User user);

    @Update("update t_user set company=#{company} where id=#{id}")
    void updateCompany(User user);
}