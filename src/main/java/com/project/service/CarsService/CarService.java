package com.project.service.CarsService;

import com.project.entity.Car.Car;
import com.project.repository.CarsRepository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public String  addCarDetails(Car car) {
        Car saveDetails = carRepository.save(car);
        return "Data is saved.";
    }

    public Car getCarDetailsById(long id) {
        Optional<Car> opCar = carRepository.findById(id);
        if(opCar.isPresent()){
            Car getDetails = opCar.get();
            return getDetails;
        }
        return null;
    }

    public List<Car> searchCarDetails(String details, Integer year) {
        List<Car> getDetails = carRepository.searchCar(details, year);
        if(getDetails.isEmpty()){
            throw new RuntimeException("No records found for the given search criteria." + getDetails);
        }
        return getDetails;
    }

    public List<Car> getAllCars(int pageNo, int pageSize, String sortBy, String sortDir) {

        if ("brand".equalsIgnoreCase(sortBy)) {
            sortBy = "brand.name";
        }  else if ("model".equalsIgnoreCase(sortBy)) {
            sortBy = "model.name";
        }

        // Create Sort object based on direction
        Sort sort = Sort.by(sortBy);
        if ("asc".equalsIgnoreCase(sortDir)) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }

        Pageable page = PageRequest.of(pageNo, pageSize, sort);
        Page<Car> records = carRepository.findAll(page);
        List<Car> getDetails = records.getContent();
        return getDetails;
    }
}
