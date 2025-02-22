package com.project.service.CarsService;

import com.project.entity.Car.Transmission;
import com.project.repository.CarsRepository.TransmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransmissionService {

    private TransmissionRepository transmissionRepository;

    public TransmissionService(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    public String addTransmissionDetails(Transmission transmission) {
        Transmission save = transmissionRepository.save(transmission);
        return "Transmission is saved.";
    }

    public List<Transmission> getAllTransmissionDetails() {
        List<Transmission> getDetails = transmissionRepository.findAll();
        return getDetails;
    }
}
