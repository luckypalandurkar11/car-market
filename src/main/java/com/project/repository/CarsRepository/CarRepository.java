package com.project.repository.CarsRepository;

import com.project.entity.Car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

//    @Query(
//            "SELECT c FROM Car c " +
//                    "JOIN c.brand b " +
//                    "JOIN c.model m " +
//                    "JOIN c.fuelType f " +
//                    "JOIN c.transmission t " +
//                    "JOIN c.year y " +
//                    "WHERE (:details IS NULL OR b.name = :details " +
//                    "OR m.name = :details OR f.fuel_type = :details " +
//                    "OR t.type = :details OR y.year = :year)"
//    )
//    List<Car> searchCar(
//            @Param("details") String details,
//            @Param("year") Integer year
//    );

    @Query(
            "SELECT c FROM Car c " +
                    "JOIN c.brand b " +
                    "JOIN c.model m " +
                    "JOIN c.fuelType f " +
                    "JOIN c.transmission t " +
                    "JOIN c.year y " +
                    "WHERE (:details IS NULL OR " +
                    "(b.name = :details OR m.name = :details OR f.fuel_type = :details OR t.type = :details OR CAST(y.year AS string) = :details)) " +
                    "AND (:year IS NULL OR y.year = :year)"
    )
    List<Car> searchCar(
            @Param("details") String details,
            @Param("year") Integer year
    );



}