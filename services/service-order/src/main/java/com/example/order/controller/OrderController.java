package com.example.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.order.bean.Order;
import com.example.order.properties.OrderProperties;
import com.example.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("/api/order")
@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProperties orderProperties;


    @GetMapping("/config")
    public String config() {
        return "order.timeout=" + orderProperties.getTimeOut()
                + ", order.auto-confirm=" + orderProperties.getAutoConfirm()
                + ", order.db-url=" + orderProperties.getDbUrl();
    }

    @GetMapping("/create")
    public Order createOrder(@RequestParam("userId") Long userId,
                             @RequestParam("productId") Long productId) {
        return orderService.createOrder(productId, userId);
    }

    @GetMapping("/seckill")
    @SentinelResource(value = "seckill-order", fallback = "seckillFallback")
    public Order seckill(@RequestParam(value = "userId", required = false) Long userId,
                             @RequestParam(value = "productId", defaultValue = "1000") Long productId) {
        Order order = orderService.createOrder(productId, userId);
        order.setId(Long.MAX_VALUE);
        return order;
    }

    public Order seckillFallback(Long userId, Long productId, Throwable ex) {
        log.error("seckillFallback...");
        return Order.builder()
                .id(productId)
                .userId(userId)
                .address("异常信息:" + ex.getClass())
                .build();
    }

    @GetMapping("/writeDb")
    public String writeDb() {
        return "writeDb success...";
    }

    @GetMapping("/readDb")
    public String readDb() {
        log.info("readDb...");
        return "readDb success...";
    }

}
