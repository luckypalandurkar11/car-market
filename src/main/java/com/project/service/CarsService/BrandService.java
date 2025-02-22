package com.project.service.CarsService;

import com.project.repository.CarsRepository.BrandRepository;
import org.springframework.stereotype.Service;
import com.project.entity.Car.Brand;

import java.util.List;

@Service
public class BrandService {

    private BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public String addBrandName(Brand brand) {
        Brand save = brandRepository.save(brand);
        return "Brand name is saved.";
    }

    public List<Brand> getAllBranddetails() {
        List<Brand> getDetails = brandRepository.findAll();
        return getDetails;
    }
}
