package com.bauk.deliveryrequest.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bauk.deliveryrequest.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByNameOrEmail(String name, String email);
}
