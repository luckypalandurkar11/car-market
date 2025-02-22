package com.project.controller.CarsController;

import com.project.entity.Car.Car;
import com.project.service.CarsService.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/z1/car")
public class CarController {

    @Autowired
    private CarService carService;

    //http://localhost:8080/api/z1/car/add
    @PostMapping("/add")
    public ResponseEntity<?> addCarDetails(
            @RequestBody Car car
    ){
        String response = carService.addCarDetails(car);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/z1/car/getDetails/{id}
    @GetMapping("/getDetails/{id}")
    public ResponseEntity<?> getCarDetails(
            @PathVariable long id
    ){
        Car response = carService.getCarDetailsById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //http://localhost:8080/api/z1/car/searchCar
    @GetMapping("/searchCar")
    public ResponseEntity<?> searchCarDetails(
            @RequestParam(required = false) String details,
            @RequestParam(required = false) Integer year
    ){
        List<Car> response = carService.searchCarDetails(details, year);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //http://localhost:8080/api/z1/car?pageNo=0&pageSize=3&sortBy=name&sortDir=asc
    @GetMapping()                   //Pagination concept
    public ResponseEntity<?> getAllCars(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "3", required = false) int pageSize,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "asc", required = false) String sortDir
    ){
        List<Car> response = carService.getAllCars(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
