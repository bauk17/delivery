package com.bauk.deliveryrequest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bauk.deliveryrequest.dto.OrderDto;
import com.bauk.deliveryrequest.models.Order;
import com.bauk.deliveryrequest.repositories.OrderRepository;
import com.bauk.deliveryrequest.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/newOrder")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto newOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Order>> findAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> findOrder(@PathVariable String orderId) {
        Optional<Order> order = orderService.findOrderById(orderId);
        if (order.isPresent()) {
            return ResponseEntity.ok().body(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/myOrders/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String userId) {
        List<Order> order = orderService.getUserOrders(userId);

        return ResponseEntity.ok().body(order);
    }

}
