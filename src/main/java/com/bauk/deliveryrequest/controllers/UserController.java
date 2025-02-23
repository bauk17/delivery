package com.bauk.deliveryrequest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bauk.deliveryrequest.dto.AuthResponseDTO;
import com.bauk.deliveryrequest.dto.UserDto;
import com.bauk.deliveryrequest.dto.UserResponseDTO;
import com.bauk.deliveryrequest.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

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
}