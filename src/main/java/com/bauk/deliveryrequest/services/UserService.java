package com.bauk.deliveryrequest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bauk.deliveryrequest.models.User;
import com.bauk.deliveryrequest.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User obj) {
        return userRepository.insert(obj);
    }
}
