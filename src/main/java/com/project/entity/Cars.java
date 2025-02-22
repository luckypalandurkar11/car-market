package com.project.entity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/c2/cars")
public class Cars {

    @PostMapping
    public String addCars(){
        return "Added!";
    }
}
