package com.project.service.CarsService;

import com.project.entity.Car.Year;
import com.project.repository.CarsRepository.YearRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearService {

    private YearRepository yearRepository;

    public YearService(YearRepository yearRepository) {
        this.yearRepository = yearRepository;
    }

    public String addYear(Year year) {
        Year save = yearRepository.save(year);
        return "Year record is saved.";
    }

    public List<Year> getAllYearDetails() {
        List<Year> getDetails = yearRepository.findAll();
        return getDetails;
    }
}
