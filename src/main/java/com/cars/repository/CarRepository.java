package com.cars.repository;

import com.cars.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer> {
    Optional<Car> findByName(String name);
}
