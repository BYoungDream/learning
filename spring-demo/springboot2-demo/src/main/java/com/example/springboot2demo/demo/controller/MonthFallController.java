package com.example.springboot2demo.demo.controller;

import com.example.springboot2demo.demo.entity.Rainfall;
import com.example.springboot2demo.demo.service.RainfallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by guoyanfei on 2019/2/12
 */
@RestController
@RequestMapping(value = "/month")
public class MonthFallController {
    @Autowired
    private RainfallService rainfallService;

    @RequestMapping(value = "/getRain", method ={RequestMethod.GET, RequestMethod.POST})
    public Object getRainfall(String stcd, Integer year, Integer month) {
        List<Rainfall> rainfallList = null;
        rainfallList = rainfallService.getAllRain(stcd, year, month);
        return rainfallList;
    }

    @RequestMapping(value = "/setModel")
    public boolean UpdateModel(Integer id, float rainfall) {
        rainfallService.UpdateModel(id, rainfall);
        return true;
    }
}
