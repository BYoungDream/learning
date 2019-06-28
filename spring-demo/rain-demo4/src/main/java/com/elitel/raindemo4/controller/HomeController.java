package com.elitel.raindemo4.controller;

import com.elitel.raindemo4.services.RainfallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private RainfallService rainfallService;
    @RequestMapping("/index")
    public Object index(){
        return rainfallService.getAllRain("",2017,1);
    }
}
