package com.simple.weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WuChengXing
 * @date 2021/10/25
 */
@RequestMapping("/weather")
@RestController
public class WeatherController {

    @GetMapping("/get")
    public void get(@RequestParam("cityName") String cityName){
        return;
    }
}
