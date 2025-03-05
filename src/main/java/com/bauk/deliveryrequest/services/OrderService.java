package com.bauk.deliveryrequest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bauk.deliveryrequest.dto.OrderDto;
import com.bauk.deliveryrequest.dto.UserResponseDTO;
import com.bauk.deliveryrequest.exceptions.ObjectNotFoundException;
import com.bauk.deliveryrequest.models.Order;
import com.bauk.deliveryrequest.models.User;
import com.bauk.deliveryrequest.repositories.OrderRepository;
import com.bauk.deliveryrequest.repositories.UserRepository;
import com.bauk.deliveryrequest.security.JwtTokenProvider;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public OrderDto createOrder(OrderDto orderDto) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userRepository.findByNameOrEmail(username, username);

        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }

        UserResponseDTO userOrdering = new UserResponseDTO(user);

        Order userOrder = new Order();
        userOrder.setCustomer(userOrdering);
        userOrder.setOrder_date(orderDto.getOrder_date());
        userOrder.setQuantity(orderDto.getQuantity());
        Order prevOrder = orderRepository.save(userOrder);

        return new OrderDto(prevOrder);
    }
}
