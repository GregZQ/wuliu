package com.df.dao;

import com.df.domain.Detail;
import com.df.domain.SendList;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SendDao {
    /**
     * 创建一个新的Send
     * @return
     */
    @Insert("insert into t_list(carid)values(null)")
    @Options(useGeneratedKeys=true, keyProperty="sid")
    Integer createNew(SendList sendList);


    @Delete("delete from t_list  where load_time is null")
    void refresh();

    @Select("select sid,carid,load_time as loadTime,place as Place," +
            "moneys_me as moneyMe,moneys_he as moneyHe,putplace as putPlace," +
            "linkphone as linkPhone from t_list where sid=#{id}")
    SendList findSendListById(@Param("id") Integer id);

    @Update("update t_list set carid=#{carid},load_time=#{loadTime},place=#{place}," +
            "moneys_he=#{moneysHe},moneys_me=#{moneysMe},putplace=#{putPlace},linkphone=#{linkPhone},totalPages=#{totalPages} " +
            "where sid=#{sid}")
    void updateSendList(SendList sendList);

    @Select("<script> select sid,place as Place,carid,load_time as loadTime from t_list where 1=1 <when test='loadTime!=null'> and load_time =#{loadTime} </when> <when test='carid!=null'> and carid like '%${carid}%' </when> order by sid desc " +
            " limit #{start},#{nums} </script>")
    List<SendList> queryHistoryByTimeAndCarId(@Param("loadTime") String loadTime,@Param("carid") String carid,@Param("start") int start,@Param("nums") int nums);

    @Select("<script> select count(*) from t_list where 1=1 <when test='loadTime!=null'> and load_time=#{loadTime} </when> <when test='carid!=null'> and carid like '%${carid}%' </when> </script>")
    int getCountByTimeAndCarId(@Param("loadTime") String loadTime, @Param("carid") String carid);

    @Delete("delete from t_list where sid=#{sid}")
    Integer deleteBySid(@Param("sid") Integer sid);
}
