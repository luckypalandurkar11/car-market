package com.project.repository.CarsRepository;

import com.project.entity.Car.Year;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Year, Long> {
}