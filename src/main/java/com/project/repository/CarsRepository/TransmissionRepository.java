package com.project.repository.CarsRepository;

import com.project.entity.Car.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
}