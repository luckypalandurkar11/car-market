package com.project.service.CarsService;

import com.project.repository.CarsRepository.ModelRepository;
import org.springframework.stereotype.Service;
import com.project.entity.Car.Model;

import java.util.List;

@Service
public class ModelService {

    private ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public String addModelName(Model model) {
        Model save = modelRepository.save(model);
        return "Model name is saved.";
    }

    public List<Model> getAllModelDetails() {
        List<Model> getDetails = modelRepository.findAll();
        return getDetails;
    }
}
