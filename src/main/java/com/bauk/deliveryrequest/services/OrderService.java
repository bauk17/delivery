package com.bauk.deliveryrequest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bauk.deliveryrequest.dto.OrderDto;
import com.bauk.deliveryrequest.models.Order;
import com.bauk.deliveryrequest.models.User;
import com.bauk.deliveryrequest.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    User customer = new User(null, "cauapereira_@hotmail.com", "randombullshit", true, "Cauã");

    public OrderDto createOrder(OrderDto orderDto) {
        Order userOrder = new Order();
        userOrder.setCustomer(customer);
        userOrder.setOrder_date(orderDto.getOrder_date());
        userOrder.setQuantity(orderDto.getQuantity());
        Order prevOrder = orderRepository.save(userOrder);

        return new OrderDto(prevOrder);
    }
}
