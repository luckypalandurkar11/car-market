package com.project.repository.CarsRepository;

import com.project.entity.Car.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
}