package com.example.cashinvoice.controller;

import com.example.cashinvoice.dto.OrderRequest;
import com.example.cashinvoice.dto.OrderResponse;
import com.example.cashinvoice.model.Order;
import com.example.cashinvoice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    //Create new order
    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request, Authentication auth) throws IOException {
        // Only ADMIN or USER can create orders
        OrderResponse response = service.createOrder(request, auth);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Get order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable String orderId, Authentication auth) {
        return service.getOrderById(orderId, auth)
                .map(this::mapToResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }

    // List orders by Customer ID
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomer(
            @PathVariable String customerId,
            Authentication auth) {

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        List<Order> orders;

        if (isAdmin) {
            // ADMIN can view any customer’s orders
            orders = service.getOrdersByCustomer(customerId);
        } else {
            // USER can only view their own orders
            if (!auth.getName().equals(customerId)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            orders = service.getOrdersByCustomer(auth.getName());
        }

        List<OrderResponse> response = orders.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    //Map order to OrderResponse
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
