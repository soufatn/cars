package com.cars.service;

import com.cars.model.Car;
import com.cars.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> listAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    public void updatePrice(Integer carId, int newPrice) {
        final Optional<Car> optionalCar = carRepository.findById(carId);
        optionalCar.ifPresent(car -> {
            car.setPrice(newPrice);
            carRepository.save(car);
        });
    }
}
