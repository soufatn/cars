package com.cars.controller;

import com.cars.dto.CarDto;
import com.cars.dto.DuplicateCarDto;
import com.cars.model.Car;
import com.cars.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
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

    @PostMapping("/api/car/{name}")
    public ResponseEntity<Void> duplicate(@PathVariable("name") String name, @RequestBody DuplicateCarDto duplicateCarDto) {
        final Optional<Car> optionalCar = carRepository.findByName(name);
        optionalCar.ifPresent(car -> duplicateCar(car, duplicateCarDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void duplicateCar(Car originalCar, DuplicateCarDto duplicateCarDto) {
        String category = originalCar.getCategory();
        if (duplicateCarDto.newPrice() > originalCar.getPrice() && originalCar.getCategory().equals("Small")) {
            category = "Medium";
        }
        final Car newCar = Car.of(duplicateCarDto.newName(), duplicateCarDto.newPrice(), category);
        carRepository.save(newCar);
    }
}
