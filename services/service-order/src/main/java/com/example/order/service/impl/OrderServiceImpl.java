package com.example.order.service.impl;

import com.example.order.bean.Order;
import com.example.order.feign.ProductFeignClient;
import com.example.order.service.OrderService;
import com.example.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public Order createOrder(Long productId, Long userId) {
//        Product product = getProductFromRemoteWithLoadBalanceAnnotation(productId);

        Product product = productFeignClient.getProductById(productId, "xxx");

        return Order.builder()
                .id(1L)
                .totalAmount(product.getPrice()
                        .multiply(new BigDecimal(product.getNum())))
                .userId(userId)
                .nickName("zhangsan")
                .address("address")
                .productList(Arrays.asList(product))
                .build();
    }

    private Product getProductFromRemote(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        String url = "http://" + instance.getHost() + ":" + instance.getPort()
                + "/product/" + productId;

        log.info("远程请求: {}", url);

        return restTemplate.getForObject(url, Product.class);
    }

    private Product getProductFromRemoteWithLoadBalance(Long productId) {
        ServiceInstance choose = loadBalancerClient.choose("service-product");

        String url = "http://" + choose.getHost() + ":" + choose.getPort()
                + "/product/" + productId;

        log.info("远程请求: {}", url);

        return restTemplate.getForObject(url, Product.class);
    }

    private Product getProductFromRemoteWithLoadBalanceAnnotation(Long productId) {

        String url = "http://service-product/product/" + productId;

        log.info("远程请求: {}", url);

        return restTemplate.getForObject(url, Product.class);
    }

}
