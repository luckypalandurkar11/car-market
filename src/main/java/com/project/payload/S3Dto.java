package com.project.payload;

import com.project.entity.Car.Car;

public class S3Dto {
    private Long id;
    private String imageUrl;
    private Car car;  // Include Car details

    // Constructor
    public S3Dto(Long id, String imageUrl, Car car) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.car = car;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

}
