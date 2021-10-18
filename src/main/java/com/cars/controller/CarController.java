package com.cars.controller;

import com.cars.dto.CarDto;
import com.cars.repository.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/api/car/{name}")
    public CarDto findByName(@PathVariable("name") String name) {
        return carRepository.findByName(name)
                .map(car -> new CarDto(car.getName(), car.getCategory()))
                .orElse(null);
    }
}
