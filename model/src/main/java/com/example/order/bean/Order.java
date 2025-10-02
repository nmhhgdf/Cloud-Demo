package com.example.order.bean;

import com.example.product.bean.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private BigDecimal totalAmount;
    private Long userId;
    private Long productId;
    private String nickName;
    private String address;
    private List<Product> productList;

}
