package com.bauk.deliveryrequest.controllers;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bauk.deliveryrequest.dto.OrderDto;
import com.bauk.deliveryrequest.dto.StatusUpdateRequest;
import com.bauk.deliveryrequest.exceptions.ObjectNotFoundException;
import com.bauk.deliveryrequest.models.Order;
import com.bauk.deliveryrequest.repositories.OrderRepository;
import com.bauk.deliveryrequest.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/order")
@Tag(name = "Order", description = "Order API")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Operation(summary = "Create a new order")
    @ApiResponse(responseCode = "200", description = "Returns an OrderDto")
    @ApiResponse(responseCode = "409", description = "User not found")
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto newOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(newOrder);
    }

    @Operation(summary = "Get all orders")
    @ApiResponse(responseCode = "200", description = "Returns a list of Orders")
    @GetMapping
    public ResponseEntity<List<Order>> findAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @Operation(summary = "Get an order by id")
    @ApiResponse(responseCode = "200", description = "Returns an Order")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findOrder(@PathVariable String orderId) {
        Optional<Order> order = orderService.findOrderById(orderId);
        if (order.isPresent()) {
            return ResponseEntity.ok().body(order.get());
        } else {
            throw new ObjectNotFoundException("Order not found");
        }
    }

    @Operation(summary = "Update an order status")
    @ApiResponse(responseCode = "200", description = "Returns an Order")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @PatchMapping("/{orderId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody StatusUpdateRequest status)
            throws AccessDeniedException {
        Optional<Order> order = orderService.findOrderById(orderId);
        if (order.isPresent()) {
            Order acceptedOrder = orderService.updateOrderStatus(orderId, status);
            return ResponseEntity.ok().body(acceptedOrder);
        } else {
            throw new ObjectNotFoundException("Order not found");
        }
    }

    @Operation(summary = "Delete an order")
    @ApiResponse(responseCode = "204", description = "No content")
    @ApiResponse(responseCode = "404", description = "Order not found with id {orderId}")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

}
