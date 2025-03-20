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

import com.bauk.deliveryrequest.dto.AuthResponseDTO;
import com.bauk.deliveryrequest.dto.UserDto;
import com.bauk.deliveryrequest.dto.UserResponseDTO;
import com.bauk.deliveryrequest.models.Order;
import com.bauk.deliveryrequest.models.User;
import com.bauk.deliveryrequest.services.OrderService;
import com.bauk.deliveryrequest.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDto userDto) {
        UserResponseDTO newUser = userService.createUser(userDto);

        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDTO> authUser(@RequestBody UserDto userDto) {
        AuthResponseDTO response = userService.authUser(userDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        Optional<User> user = userService.findUserById(userId);

        if (user.isPresent()) {
            return ResponseEntity.ok().body(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getUserOrders() {
        List<Order> order = orderService.getUserOrders();

        return ResponseEntity.ok().body(order);
    }

}
