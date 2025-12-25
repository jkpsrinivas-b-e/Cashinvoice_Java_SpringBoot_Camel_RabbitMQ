package com.example.cashinvoice.service;

import com.example.cashinvoice.dto.OrderRequest;
import com.example.cashinvoice.dto.OrderResponse;
import com.example.cashinvoice.model.Order;
import com.example.cashinvoice.repository.OrderRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    //Create new order
    public OrderResponse createOrder(OrderRequest request, Authentication auth) {
        Order order = new Order(
                UUID.randomUUID(),
                auth.getName(), // current user as customerId
                request.getProduct(),
                request.getAmount(),
                LocalDateTime.now(),
                "CREATED"
        );

        repository.save(order);

        return mapToResponse(order);
    }

    //Get an order by ID
    public Optional<Order> getOrderById(String orderId, Authentication auth) {
        Optional<Order> orderOpt = repository.findById(UUID.fromString(orderId));

        if (orderOpt.isEmpty()) return Optional.empty();

        Order order = orderOpt.get();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin && !auth.getName().equals(order.getCustomerId())) {
            return Optional.empty(); // USER cannot access others' orders
        }

        return orderOpt;
    }

    //Get all orders for a customer
    public List<Order> getOrdersByCustomer(String customerId) {
        return repository.findByCustomerId(customerId);
    }

    private OrderResponse mapToResponse(Order order) {
        return new OrderResponse(
                order.getOrderId().toString(),
                order.getCustomerId(),
                order.getProduct(),
                order.getAmount(),
                order.getCreatedAt(),
                order.getStatus()
        );
    }
}
