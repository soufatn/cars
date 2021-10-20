package com.cars.controller;

import com.cars.dto.CarDto;
import com.cars.dto.DuplicateCarDto;
import com.cars.dto.UpdatedCarDto;
import com.cars.model.Car;
import com.cars.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController()
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/api/car/")
    public List<CarDto> findAll() {
        return StreamSupport.stream(carRepository.findAll().spliterator(), false)
                .map(car -> new CarDto(car.getName(), car.getCategory()))
                .collect(Collectors.toList());
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

    @PatchMapping("/api/car/{id}")
    public ResponseEntity<Void> duplicate(@PathVariable("id") int id, @RequestBody UpdatedCarDto updatedCarDto) {
        final Optional<Car> optionalCar = carRepository.findById(id);
        optionalCar.ifPresent(car -> {
            car.setPrice(updatedCarDto.newPrice());
            carRepository.save(car);
        });
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
