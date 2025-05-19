// src/main/java/com/example/demo/repository/AdminRepository.java
package com.example.backend5.repository;

import com.example.backend5.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Optional<Admin> findByEmail(String email);
}
