package com.example.order.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "order")
@Component
@Data
public class OrderProperties {

    String timeOut;

    String autoConfirm;

    String dbUrl;

}
