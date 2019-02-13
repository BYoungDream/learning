package com.example.springboot2demo.demo.dao;

import com.example.springboot2demo.demo.entity.Rainfall;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * created by guoyanfei on 2019/2/12
 */
public interface RainfallMapper {
    List<Rainfall> selectRainfall(@Param("stcd") String stcd, @Param("year") Integer year, @Param("month") Integer month);
    List<Rainfall> selectRainfall1(Rainfall enity_);
    Integer UpdateModel(@Param("id") Integer id,@Param("rainfall") float rainfall);
    Integer UpdateModel1(Rainfall enity_);
}
