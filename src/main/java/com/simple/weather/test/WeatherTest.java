package com.simple.weather.test;

import com.simple.weather.entity.WeatherInfo;
import com.simple.weather.util.WeatherUtils;

/**
 * @author WuChengXing
 * @date 2021/10/25
 */
public class WeatherTest {
    public static void main(String[] args) {
        String info = WeatherUtils.GetWeatherData("杭州");
        // 全部天气数据，含预测
        System.out.println("1.预测结果：" + info);
        WeatherInfo weatherinfo = WeatherUtils.GetWeather(info);
        // 当天天气数据
        System.out.println("2.今天天气：" + weatherinfo.toString());
    }
}
