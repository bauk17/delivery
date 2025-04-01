package com.bauk.deliveryrequest.controllers;

import java.util.List;

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
import com.bauk.deliveryrequest.exceptions.ObjectNotFoundException;
import com.bauk.deliveryrequest.models.Order;
import com.bauk.deliveryrequest.services.OrderService;
import com.bauk.deliveryrequest.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "User", description = "User API")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Create a new user")
    @ApiResponse(responseCode = "200", description = "Returns a UserResponseDTO")
    @ApiResponse(responseCode = "409", description = "User already exists")
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDto userDto) {
        UserResponseDTO newUser = userService.createUser(userDto);

        return ResponseEntity.ok(newUser);
    }

    @Operation(summary = "Authenticate a user")
    @ApiResponse(responseCode = "200", description = "Returns an AuthResponseDTO")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @SecurityRequirement(name = "BearerAuth")
    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDTO> authUser(@RequestBody UserDto userDto) {
        AuthResponseDTO response = userService.authUser(userDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get user by id")
    @ApiResponse(responseCode = "200", description = "Returns a UserDTO")
    @ApiResponse(responseCode = "404", description = "Not found")
    @SecurityRequirement(name = "BearerAuth")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String userId) {
        UserResponseDTO user = userService.findUserById(userId);

        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }
        return ResponseEntity.ok(user);

    }

    @Operation(summary = "Get user orders")
    @ApiResponse(responseCode = "200", description = "Returns a list of orders")
    @ApiResponse(responseCode = "409", description = "User not found")
    @SecurityRequirement(name = "BearerAuth")
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getUserOrders() {
        List<Order> order = orderService.getUserOrders();

        return ResponseEntity.ok().body(order);
    }

}
