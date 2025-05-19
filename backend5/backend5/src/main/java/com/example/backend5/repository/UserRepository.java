// src/main/java/com/example/demo/repository/UserRepository.java
package com.example.backend5.repository;

import com.example.backend5.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}

