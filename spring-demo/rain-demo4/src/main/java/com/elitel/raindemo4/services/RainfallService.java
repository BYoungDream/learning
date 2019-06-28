package com.elitel.raindemo4.services;

import com.elitel.raindemo4.entity.Rainfall;

import java.util.List;

public interface RainfallService {
    List<Rainfall> getAllRain(String stcd, Integer year, Integer month);
    Integer UpdateModel(Integer id,float rainfall);
}
