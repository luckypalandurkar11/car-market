package com.project.controller.CarsController;

import com.project.entity.Car.Model;
import com.project.service.CarsService.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/z1/model")
public class ModelController {

    private ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    //http://localhost:8080/api/z1/model/add
    @PostMapping("/add")
    public ResponseEntity<?> addModelName(
            @RequestBody Model model
    ){
        String response = modelService.addModelName(model);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/z1/model/allModel
    @GetMapping("/allModel")
    public List<Model> gelAllModelDetails(){
        List<Model> response = modelService.getAllModelDetails();
        return response;
    }
}
