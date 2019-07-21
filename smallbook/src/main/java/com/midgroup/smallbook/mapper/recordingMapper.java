package com.midgroup.smallbook.mapper;


import com.midgroup.smallbook.pojo.Recording;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Author:ZZY
 * Date:2019-7-21
 * Target:账目记录Mapper
 */
@Mapper
@Repository("recordingMapper")
public interface recordingMapper {
    @Insert("insert into tb_recording values(#{date},#{ordering},#{menber},#{dine},#{takeaway},#{income},#{pay},#{profit},#{year},#{month},#{day})")
    public int addRecording(Recording recording);
    @Update("update tb_recording set date=#{date},ordering=#{ordering},menber=#{menber},dine=#{dine}" +
            ",takeaway=#{takeaway},income=#{income},pay=#{pay},profit=#{profit}" +
            ",year=#{year},month=#{month},day=#{day} where date=#{olddate}")
    public int UpdateRecordingByDate(Recording recording);
    @Select("select * from tb_recording where date=#{date}")
    public Recording getRecordingByDate(@Param("date")String date);
    @Select("select * from tb_recording where year=#{year}")
    public ArrayList<Recording> getRecordingByYear(@Param("year")String year);
    @Select("select * from tb_recording where year=#{year} and month=#{month}")
    public ArrayList<Recording> getRecordingByMonth(@Param("year")String year,@Param("month")String month);

}
