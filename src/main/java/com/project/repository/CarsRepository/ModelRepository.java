package com.project.repository.CarsRepository;

import com.project.entity.Car.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}