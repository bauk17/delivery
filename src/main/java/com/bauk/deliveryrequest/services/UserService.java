package com.bauk.deliveryrequest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bauk.deliveryrequest.dto.AuthResponseDTO;
import com.bauk.deliveryrequest.dto.UserDto;
import com.bauk.deliveryrequest.dto.UserResponseDTO;
import com.bauk.deliveryrequest.models.User;
import com.bauk.deliveryrequest.repositories.UserRepository;
import com.bauk.deliveryrequest.security.JwtTokenProvider;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public User findUserByUsernameOrEmail(String nameOrEmail) {
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

    public AuthResponseDTO authUser(UserDto userDto) {

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getName(), userDto.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getName());
        String token = jwtTokenProvider.generateToken(userDetails);

        User user = userRepository.findByNameOrEmail(userDetails.getUsername(), userDto.getEmail());

        UserResponseDTO userResponse = new UserResponseDTO(user);

        return new AuthResponseDTO(token, userResponse);
    }

}
