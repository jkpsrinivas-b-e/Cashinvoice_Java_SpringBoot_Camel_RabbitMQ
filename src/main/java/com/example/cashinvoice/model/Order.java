package com.example.cashinvoice.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order {

    private UUID orderId;
    private String customerId;
    private String product;
    private double amount;
    private LocalDateTime createdAt;
    private String status;

    public Order() {
    }

    public Order(UUID orderId, String customerId, String product, double amount, LocalDateTime createdAt, String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.product = product;
        this.amount = amount;
        this.createdAt = createdAt;
        this.status = status;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
