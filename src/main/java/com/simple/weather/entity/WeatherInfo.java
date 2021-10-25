package com.simple.weather.entity;

import lombok.Data;

/**
 * @author WuChengXing
 * @date 2021/10/25
 */
@Data
public class WeatherInfo {
    private String date;
    private String week;
    /**
     * 农历时间
     */
    private String lunar;
    private String cityname;
    private String weather;
    /**
     * 当前温度
     */
    private String temp;
    private String highTemp;
    private String lowTemp;
    private String tips;
}
