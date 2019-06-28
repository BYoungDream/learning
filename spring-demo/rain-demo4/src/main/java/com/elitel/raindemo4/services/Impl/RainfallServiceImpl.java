package com.elitel.raindemo4.services.Impl;

import com.elitel.raindemo4.dao.RainfallMapper;
import com.elitel.raindemo4.entity.Rainfall;
import com.elitel.raindemo4.services.RainfallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RainfallServiceImpl implements RainfallService {
    @Autowired
    private RainfallMapper rainfallMapper;

    @Override
    public List<Rainfall> getAllRain(String stcd, Integer year, Integer month) {
        return rainfallMapper.selectRainfall(stcd,year,month);
    }

    @Override
    public Integer UpdateModel(Integer id, float rainfall) {
        return rainfallMapper.UpdateModel(id,rainfall);
    }
}
