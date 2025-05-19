package com.example.backend5.controller;

import com.example.backend5.model.Car;
import com.example.backend5.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    // Add a car (with image)
    @PostMapping
    public ResponseEntity<Car> addCar(
            @RequestPart("car") Car car,
            @RequestPart("file") MultipartFile file) {
        try {
            // Save uploaded file
            String uploadDir = "uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadDir + filename);
            file.transferTo(destinationFile);

            car.setImageUrl("/" + uploadDir + filename); // Set file path to car
            Car savedCar = carRepository.save(car);
            return ResponseEntity.ok(savedCar);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    // Get all cars
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.ok(cars);
    }

    // Get car by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable String id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            return ResponseEntity.ok(car.get());
        } else {
            return ResponseEntity.status(404).body("Car not found with ID: " + id);
        }
    }

    // Delete a car by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable String id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return ResponseEntity.ok("Car deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Car not found with ID: " + id);
        }
    }
}
