package com.example.order;

import com.example.order.feign.WeatherFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherTest {

    @Autowired
    private WeatherFeignClient weatherFeignClient;

    @Test
    void test01() {
        String weather = weatherFeignClient.getWeather("aaa", "ttt", "39");
        System.out.println(weather);
    }

}
