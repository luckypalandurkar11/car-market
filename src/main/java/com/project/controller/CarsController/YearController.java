package com.project.controller.CarsController;

import com.project.entity.Car.Year;
import com.project.service.CarsService.YearService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/z1/year")
public class YearController {

    private YearService yearService;

    public YearController(YearService yearService) {
        this.yearService = yearService;
    }

    //http://localhost:8080/api/z1/year/add
    @PostMapping("/add")
    public ResponseEntity<?> addYear(
            @RequestBody Year year
    ){
        String response = yearService.addYear(year);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/z1/year/allYear
    @GetMapping("/allYear")
    public List<Year> getAllYearDetails(){
        List<Year> response = yearService.getAllYearDetails();
        return response;
    }
}
