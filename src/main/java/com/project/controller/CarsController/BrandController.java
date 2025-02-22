package com.project.controller.CarsController;

import com.project.entity.Car.Brand;
import com.project.service.CarsService.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/z1/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    //http://localhost:8080/api/z1/brand/add
    @PostMapping("/add")
    public ResponseEntity<?> addBrandName(
            @RequestBody Brand brand
    ){
        String response = brandService.addBrandName(brand);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/z1/brand/allBrand
    @GetMapping("/allBrand")
    public List<Brand> getAllBrandDetails(){
        List<Brand> response = brandService.getAllBranddetails();
        return response;
    }
}
