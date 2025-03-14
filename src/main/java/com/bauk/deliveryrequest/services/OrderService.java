package com.bauk.deliveryrequest.services;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bauk.deliveryrequest.dto.OrderDto;
import com.bauk.deliveryrequest.dto.StatusUpdateRequest;
import com.bauk.deliveryrequest.dto.UserResponseDTO;
import com.bauk.deliveryrequest.enums.OrderStatus;
import com.bauk.deliveryrequest.exceptions.InvalidStatusException;
import com.bauk.deliveryrequest.exceptions.ObjectNotFoundException;
import com.bauk.deliveryrequest.models.Order;
import com.bauk.deliveryrequest.models.User;
import com.bauk.deliveryrequest.repositories.OrderRepository;
import com.bauk.deliveryrequest.repositories.UserRepository;
import com.bauk.deliveryrequest.security.SecurityUtil;

@Service
public class OrderService {

    private static final Set<String> VALID_STATUSES = Set.of(
            OrderStatus.PENDING.toString(),
            OrderStatus.ACCEPTED.toString(),
            OrderStatus.SHIPPED.toString(),
            OrderStatus.DELIVERED.toString(),
            OrderStatus.CANCELED.toString());

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityUtil securityUtil;

    public OrderDto createOrder(OrderDto orderDto) {

        String user = securityUtil.getAuthenticatedUsername();
        System.out.print(user);
        User findUser = userRepository.findByNameOrEmail(user, user);

        if (findUser == null) {
            throw new ObjectNotFoundException("User not found");
        }

        UserResponseDTO userOrdering = new UserResponseDTO(findUser);

        Order userOrder = new Order();
        userOrder.setCustomerId(userOrdering.getId());
        userOrder.setOrder_date(orderDto.getOrder_date());
        userOrder.setQuantity(orderDto.getQuantity());
        userOrder.setId(orderDto.getId());

        Order prevOrder = orderRepository.save(userOrder);

        return new OrderDto(prevOrder);
    }

    public Optional<Order> findOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getUserOrders(String customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        if (orders.isEmpty()) {
            throw new RuntimeException("Nenhum pedido encontrado para o cliente: " + customerId);
        }
        return orders;
    }

    public Order updateOrderStatus(String orderId, StatusUpdateRequest status) throws AccessDeniedException {

        String user = securityUtil.getAuthenticatedUsername();

        User findUser = userRepository.findByNameOrEmail(user, user);
        System.out.printf("Pega o STATUS: %s\n", status.getStatus().name());

        if (!VALID_STATUSES.contains(status.getStatus().name())) {
            throw new InvalidStatusException("Invalid status");
        }

        if (findUser == null) {
            throw new ObjectNotFoundException("User not found");
        }

        if (!findUser.getIsAdmin()) {
            throw new AccessDeniedException("Access Denied");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException("Order not found"));

        order.setDeliveryManId(findUser.getId());
        order.setStatus(status.getStatus());
        return orderRepository.save(order);

    }

}
