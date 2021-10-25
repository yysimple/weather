package com.simple.weather.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.simple.weather.entity.WeatherInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.GZIPInputStream;

/**
 * @author WuChengXing
 * @date 2021/10/25
 */
public class WeatherUtils {
    private static String weatherUrl = "http://wthrcdn.etouch.cn/weather_mini?city=";

    /**
     * 通过城市名称获取该城市的天气信息
     */
    public static String GetWeatherData(String cityname) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            URL url = new URL(weatherUrl + cityname);
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            GZIPInputStream gzin = new GZIPInputStream(is);
            // 设置读取流的编码格式，自定义编码
            InputStreamReader isr = new InputStreamReader(gzin, "utf-8");
            reader = new BufferedReader(isr);
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + " ");
            }
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 将JSON格式数据进行解析 ，返回一个weather对象
     */
    public static WeatherInfo GetWeather(String weatherInfobyJson) {
        // json天气数据
        JSONObject dataOfJson = JSONObject.parseObject(weatherInfobyJson);
        if (dataOfJson.getIntValue("status") != 1000) {
            return null;
        }
        // 创建WeatherInfo对象，提取所需的天气信息
        WeatherInfo weatherInfo = new WeatherInfo();

        // 获取当前日期：日期、星期
        Calendar cal = Calendar.getInstance();
        // Calendar类的实例化
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");
        // 时间的格式化
        weatherInfo.setDate(sdf1.format(cal.getTime()));
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
        weatherInfo.setWeek(sdf2.format(cal.getTime()));
        // 从json数据中提取数据：城市、温度、小提醒
        dataOfJson = JSONObject.parseObject(dataOfJson.getString("data"));
        // 城市
        weatherInfo.setCityname(dataOfJson.getString("city"));
        // 温度
        weatherInfo.setTemp(dataOfJson.getString("wendu"));
        // 小提示
        weatherInfo.setTips(dataOfJson.getString("ganmao"));

        // 获取今天的天气预报信息：最高温度、最低温度、天气
        JSONArray forecast = dataOfJson.getJSONArray("forecast");
        JSONObject result = forecast.getJSONObject(0);
        // 最高气温
        weatherInfo.setHighTemp(result.getString("high").substring(2));
        // 最低气温
        weatherInfo.setLowTemp(result.getString("low").substring(2));
        // 天气
        weatherInfo.setWeather(result.getString("type"));
        return weatherInfo;
    }
}
