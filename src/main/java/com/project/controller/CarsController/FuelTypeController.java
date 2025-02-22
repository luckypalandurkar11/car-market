package com.project.controller.CarsController;

import com.project.entity.Car.FuelType;
import com.project.service.CarsService.FuelTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/z1/fuel")
public class FuelTypeController {

    private FuelTypeService fuelTypeService;

    public FuelTypeController(FuelTypeService fuelTypeService) {
        this.fuelTypeService = fuelTypeService;
    }

    //http://localhost:8080/api/z1/fuel/add
    @PostMapping("/add")
    public ResponseEntity<?> addFuelType(
            @RequestBody FuelType fuelType
    ){
        String response = fuelTypeService.addFuelType(fuelType);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/z1/fuel/allFuel
    @GetMapping("/allFuel")
    public List<FuelType> getAllFuelDetails(){
        List<FuelType> response = fuelTypeService.getAllFuelDetails();
        return response;
    }
}
