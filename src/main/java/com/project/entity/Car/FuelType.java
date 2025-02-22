package com.project.entity.Car;

import jakarta.persistence.*;

@Entity
@Table(name = "fuel_type")
public class FuelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fuel_type", nullable = false, length = 50)
    private String fuel_type;

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}