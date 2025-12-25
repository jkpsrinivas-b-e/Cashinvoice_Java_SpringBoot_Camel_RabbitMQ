package com.example.cashinvoice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderResponse {

    private String orderId;
    private String customerId;
    private String product;
    private Double amount;
    private LocalDateTime createdAt;
    private String status;
}
