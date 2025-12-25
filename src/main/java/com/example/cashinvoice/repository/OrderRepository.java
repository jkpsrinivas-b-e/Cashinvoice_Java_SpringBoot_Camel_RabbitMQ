package com.example.cashinvoice.repository;

import com.example.cashinvoice.model.Order;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    private final Map<UUID, Order> store = new ConcurrentHashMap<>();

    public Order save(Order order) {
        store.put(order.getOrderId(), order);
        return order;
    }

    public Optional<Order> findById(UUID orderId) {
        return Optional.ofNullable(store.get(orderId));
    }

    public List<Order> findByCustomerId(String customerId) {
        return store.values().stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }

    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }
}
