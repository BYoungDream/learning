package com.elitel.raindemo4.dao;

import com.elitel.raindemo4.entity.Rainfall;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RainfallMapper {
    List<Rainfall> selectRainfall(@Param("stcd") String stcd, @Param("year") Integer year, @Param("month") Integer month);
    List<Rainfall> selectRainfall1(Rainfall enity_);
    Integer UpdateModel(@Param("id") Integer id,@Param("rainfall") float rainfall);
    Integer UpdateModel1(Rainfall enity_);
}
