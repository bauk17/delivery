package com.bauk.deliveryrequest.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bauk.deliveryrequest.models.Order;
import com.bauk.deliveryrequest.models.User;
import com.bauk.deliveryrequest.repositories.OrderRepository;
import com.bauk.deliveryrequest.repositories.UserRepository;
import com.bauk.deliveryrequest.security.SecurityUtil;

@Component
public class UserOwnershipUtil {
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    public boolean isUserOwner(String orderId) {
        String authenticatedUser = securityUtil.getAuthenticatedUsername();
        User user = userRepository.findByNameOrEmail(authenticatedUser, authenticatedUser);

        if (authenticatedUser == null) {
            return false;
        }

        Optional<Order> order = orderRepository.findById(orderId);

        if (order == null) {
            return false;
        }

        return order.get().getCustomerId().equals(user.getId());
    }
}
