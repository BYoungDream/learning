package com.example.springboot2demo.demo.service;

import com.example.springboot2demo.demo.entity.Rainfall;

import java.util.List;

/**
 * created by guoyanfei on 2019/2/12
 */
public interface RainfallService {
    List<Rainfall> getAllRain(String stcd, Integer year, Integer month);
    Integer UpdateModel(Integer id,float rainfall);
}
