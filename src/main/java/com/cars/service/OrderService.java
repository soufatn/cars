package com.cars.service;

import com.cars.model.Car;
import com.cars.model.Order;
import com.cars.repository.CarRepository;
import com.cars.repository.ClientRepository;
import com.cars.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;
    private final CarRepository carRepository;

    public OrderService(ClientRepository clientRepository, OrderRepository orderRepository, CarRepository carRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
        this.carRepository = carRepository;
    }

    public boolean create(String email, int carId, int price) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        optionalCar.ifPresent(car -> {
            orderRepository.save(Order.of(email, car, price));
        });

        return true;
    }
}
