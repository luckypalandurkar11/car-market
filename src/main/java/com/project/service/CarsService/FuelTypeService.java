package com.project.service.CarsService;

import com.project.repository.CarsRepository.FuelTypeRepository;
import org.springframework.stereotype.Service;
import com.project.entity.Car.FuelType;

import java.util.List;

@Service
public class FuelTypeService {

    private FuelTypeRepository fuelTypeRepository;

    public FuelTypeService(FuelTypeRepository fuelTypeRepository) {
        this.fuelTypeRepository = fuelTypeRepository;
    }

    public String addFuelType(FuelType fuelType) {
        FuelType save = fuelTypeRepository.save(fuelType);
        return "FuelType is saved.";
    }

    public List<FuelType> getAllFuelDetails() {
        List<FuelType> getDetails = fuelTypeRepository.findAll();
        return getDetails;
    }
}
