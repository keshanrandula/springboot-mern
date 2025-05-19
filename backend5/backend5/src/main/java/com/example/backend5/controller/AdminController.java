// src/main/java/com/example/demo/controller/AdminController.java
package com.example.backend5.controller;

import com.example.backend5.model.Admin;
import com.example.backend5.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "http://localhost:3000") // Allow React frontend
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/register")
    public String registerAdmin(@RequestBody Admin admin) {
        Optional<Admin> existingAdmin = adminRepository.findByEmail(admin.getEmail());
        if (existingAdmin.isPresent()) {
            return "Admin email already registered!";
        }
        adminRepository.save(admin);
        return "Admin registered successfully!";
    }

    @PostMapping("/login")
    public String loginAdmin(@RequestBody Admin admin) {
        Optional<Admin> existingAdmin = adminRepository.findByEmail(admin.getEmail());
        if (existingAdmin.isPresent()) {
            if (existingAdmin.get().getPassword().equals(admin.getPassword())) {
                return "Admin login successful!";
            } else {
                return "Incorrect admin password!";
            }
        } else {
            return "Admin not found!";
        }
    }
}
