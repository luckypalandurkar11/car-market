package com.project.controller.CarsController;

import com.project.entity.Car.Transmission;
import com.project.service.CarsService.TransmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/z1/transmission")
public class TransmissionController {

    private TransmissionService transmissionService;

    public TransmissionController(TransmissionService transmissionService) {
        this.transmissionService = transmissionService;
    }

    //http://localhost:8080/api/z1/transmission/add
    @PostMapping("/add")
    public ResponseEntity<?> addTransmissionDetails(
            @RequestBody Transmission transmission
    ){
        String response = transmissionService.addTransmissionDetails(transmission);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/z1/transmission/allTransmission
    @GetMapping("/allTransmission")
    public List<Transmission> getAllTransmissionDetails(){
        List<Transmission> response = transmissionService.getAllTransmissionDetails();
        return response;
    }
}
