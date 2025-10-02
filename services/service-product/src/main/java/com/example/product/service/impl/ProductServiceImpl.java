package com.example.product.service.impl;

import com.example.product.bean.Product;
import com.example.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long productId) {

        return Product.builder()
                .id(productId)
                .price(new BigDecimal("99"))
                .productName("product-" + productId)
                .num(2)
                .build();

    }

}
