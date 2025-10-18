package com.example.order.feign;

import com.example.order.feign.fallback.ProductFeignClientFallback;
import com.example.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "service-product", fallback = ProductFeignClientFallback.class)
//@FeignClient(value = "gateway", fallback = ProductFeignClientFallback.class)  #这两个注释是为服务间调用过网关的写法，不建议使用
public interface ProductFeignClient {

//    @GetMapping("/api/product/product/{id}")  #这两个注释是为服务间调用过网关的写法，不建议使用
    @GetMapping("/product/{id}")
    Product getProductById(@PathVariable("id") Long id,
                           @RequestHeader("token") String token);

}
