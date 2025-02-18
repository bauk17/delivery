package com.bauk.deliveryrequest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bauk.deliveryrequest.dto.UserDto;
import com.bauk.deliveryrequest.dto.UserResponseDTO;
import com.bauk.deliveryrequest.models.User;
import com.bauk.deliveryrequest.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByUsernameOrEmail(String nameOrEmail) {
        return userRepository.findByNameOrEmail(nameOrEmail, nameOrEmail);
    }

    public UserResponseDTO createUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setId(userDto.getId());

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(savedUser);
    }

    public UserResponseDTO authUser(UserDto userDto) {
        Optional<User> user = userRepository.findByNameOrEmail(userDto.getName(), userDto.getEmail());
        if (!user.isPresent()) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(userDto.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return new UserResponseDTO(user.get());

    }

}
