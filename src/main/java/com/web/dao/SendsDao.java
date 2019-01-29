package com.web.dao;

import com.web.domain.Sends;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SendsDao {
    /**
     * 创建一个新的Send
     *
     * @return
     */
    @Insert("insert into t_sends(carid,send_time)values(null,#{sendTime})")
    @Options(useGeneratedKeys=true, keyProperty="sid")
    Integer createNew(Sends sends);


    @Delete("delete from t_sends  where load_time is null")
    void refresh();

    @Select("select sid,carid, " +
            " load_time as loadTime,from_line as fromLine,to_line as toLine, " +
            " send_time as sendTime , " +
            " save_path as savePath,uid as uid " +
            "from t_sends where sid=#{id}")
    Sends findSends(@Param("id") Integer id);

    @Update("update t_sends " +
            " set carid=#{carid},load_time=#{loadTime}," +
            " from_line=#{fromLine}," +
            " to_line=#{toLine},"+
            " save_path=#{savePath},uid=#{uid} "+
            " where sid=#{sid}")
    void updateSends(Sends sends);

    @Select("<script> " +
            " select sid,from_line as fromLine,to_line as toLine," +
            " carid,load_time as loadTime " +
            " from t_sends " +
            " where uid=#{uid} " +
            " <when test='loadTime!=null'> and load_time =#{loadTime} </when> " +
            " <when test='carid!=null'> and carid like '%${carid}%' </when> " +
            " order by sid desc " +
            " limit #{start},#{nums} </script>")
    List<Sends> queryHistoryByTimeAndCarIdAndUid(@Param("loadTime") String loadTime, @Param("carid") String carid,
                                                 @Param("start") int start, @Param("nums") int nums,
                                                 @Param("uid") Integer uid);

    @Select("<script> " +
            " select count(*) " +
            " from t_sends " +
            " where uid=#{uid} " +
            " <when test='loadTime!=null'> and load_time=#{loadTime} </when>" +
            " <when test='carid!=null'> and carid like '%${carid}%' </when> " +
            " </script>")
    int getCountByTimeAndCarIdAndUid(@Param("loadTime") String loadTime, @Param("carid") String carid,
                                     @Param("uid") Integer uid);

    @Delete("delete from t_sends where sid=#{sid}")
    Integer deleteBySid(@Param("sid") Integer sid);
}
