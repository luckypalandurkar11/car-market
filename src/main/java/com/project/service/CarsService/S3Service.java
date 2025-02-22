package com.project.service.CarsService;

import com.project.entity.Car.Car;
import com.project.entity.CarImage;
import com.project.payload.S3Dto;
import com.project.repository.CarsRepository.CarImageRepository;
import com.project.repository.CarsRepository.CarRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final CarRepository carRepository;
    private final CarImageRepository carImageRepository;

    @Value("${aws.bucketName}")
    private String bucketName;

    public S3Service(S3Client s3Client, CarRepository carRepository, CarImageRepository carImageRepository) {
        this.s3Client = s3Client;
        this.carRepository = carRepository;
        this.carImageRepository = carImageRepository;
    }

    public S3Dto uploadFile(long carId, MultipartFile file) throws IOException {

//        //Check if carId exists    1st method
//        Optional<Car> opCar = carRepository.findById(carId);
//        if(opCar.isEmpty()){
//            throw new RuntimeException("Car with ID " + carId + " not found.");
//        }

        //Check if carId exists - 2nd method
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car with ID " + carId + " not found."));

        //Validate file before proceeding
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File must be provided and cannot be empty.");
        }
        // Generate a unique file name inside the carId folder
        String fileName = "cars/" + carId + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

        String fileUrl = "https://" + bucketName + ".s3.amazonaws.com/" + fileName;

        // Save image record in the database
        CarImage carImage = new CarImage();
        carImage.setCar(car);
        carImage.setUrl(fileUrl);
        carImage = carImageRepository.save(carImage);  // Save & get back the saved entity

        // Retrieve the saved CarImage from the database (ensuring persistence)
        Optional<CarImage> savedImage = carImageRepository.findById(carImage.getId());
        if (savedImage.isEmpty()) {
            throw new RuntimeException("Error saving car image.");
        }

        // Return structured response DTO
        return new S3Dto(savedImage.get().getId(), savedImage.get().getUrl(), savedImage.get().getCar());


    }
}
