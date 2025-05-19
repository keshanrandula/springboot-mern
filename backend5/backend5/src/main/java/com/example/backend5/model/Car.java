package com.example.backend5.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cars")
public class Car {
    @Id
    private String id;
    private String brand;
    private String model;
    private int year;
    private double price;
    private String imageUrl; // e.g., /uploads/filename.jpg
}
