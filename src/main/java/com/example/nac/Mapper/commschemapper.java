package com.example.nac.Mapper;

import com.example.nac.model.commsche;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//DB에서 정기회의 정보를 읽어와 정기회의 모델 객체에 맵핑
@Mapper
public interface commschemapper {

    @Select("SELECT * FROM commsche")
    List<commsche> GetcommscheList();
    @Select("SELECT * FROM commsche where TITLE = #{TITLE}")
    commsche GetcommscheTitle(@Param("TITLE") String TITLE);

    @Select("SELECT * FROM project2022.commsche where MEETING_DATE = #{date}")
    List<commsche> GetcommscheDate(@Param("date") String date);

    /* -------------------------------*/
    @Select("SELECT * FROM project2022.commsche where MEETING_DATE like '%${month}%'")
    List<commsche> GetcommscheMonth(@Param("month") String month);

    @Insert("INSERT INTO commsche VALUES(#{meetingsession}, #{cha}, #{title}, #{meeting_date}, #{meeting_time}, #{link_url}, #{unit_cd}, #{unit_nm})")
    int Putcommsche(@Param("meetingsession") String meetingsession,@Param("cha") String cha,@Param("title") String title, @Param("meeting_date") String meeting_date, @Param("meeting_time") String meeting_time, @Param("link_url") String link_url, @Param("unit_cd") String unit_cd, @Param("unit_nm") String unit_nm);
}
