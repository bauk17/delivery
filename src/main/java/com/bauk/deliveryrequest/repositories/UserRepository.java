package com.bauk.deliveryrequest.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements MongoRepository<Object, String> {
}
