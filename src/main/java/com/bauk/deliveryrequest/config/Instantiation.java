package com.bauk.deliveryrequest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.bauk.deliveryrequest.repositories.OrderRepository;
import com.bauk.deliveryrequest.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }

}
