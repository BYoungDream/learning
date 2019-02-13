package com.example.springboot2demo.demo.service;

import com.example.springboot2demo.demo.dao.RainfallMapper;
import com.example.springboot2demo.demo.entity.Rainfall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by guoyanfei on 2019/2/12
 */
@Service
public class RainfallServiceImpl implements RainfallService {

    @Autowired
    private RainfallMapper rainfallMapper;

    @Override
    public List<Rainfall> getAllRain(String stcd, Integer year, Integer month) {
        return rainfallMapper.selectRainfall(stcd, year, month);
    }

    @Override
    public Integer UpdateModel(Integer id, float rainfall) {
        return rainfallMapper.UpdateModel(id, rainfall);
    }
}
