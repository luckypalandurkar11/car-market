package com.project.repository.CarsRepository;

import com.project.entity.Car.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}