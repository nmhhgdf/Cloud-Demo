package com.example.order.feign.fallback;

import com.example.order.feign.ProductFeignClient;
import com.example.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {

    @Override
    public Product getProductById(Long id, String token) {
        System.out.println("ProductFeignClientFallback...");
        return Product.builder()
                .id(id)
                .price(new BigDecimal("0"))
                .productName("未知商品")
                .num(0)
                .build();
    }

}
