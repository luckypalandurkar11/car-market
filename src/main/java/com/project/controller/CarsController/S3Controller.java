package com.project.controller.CarsController;

import com.project.payload.S3Dto;
import com.project.service.CarsService.S3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/z1/images")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    //http://localhost:8080/api/z1/images/upload/1
    @PostMapping("/upload/{carId}")
    public ResponseEntity<?> uploadCarPhotos(
            @PathVariable long carId,
            @RequestParam("file") MultipartFile file) {
        try {
            S3Dto s3Dto = s3Service.uploadFile(carId, file);
            return new ResponseEntity<>(s3Dto, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
