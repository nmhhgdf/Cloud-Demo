package com.example.product.controller;

import com.example.product.bean.Product;
import com.example.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/product")
@Slf4j
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public Product getProduct(HttpServletRequest request,
            @PathVariable("id") Long productId) {
        String xToken = request.getHeader("X-Token");
        log.info("Get product with id {}, xtoken {}", productId, xToken);
        Product product = productService.getProductById(productId);
        return product;
    }

}
