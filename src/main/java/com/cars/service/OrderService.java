package com.cars.service;

import com.cars.model.Car;
import com.cars.model.Order;
import com.cars.repository.CarRepository;
import com.cars.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Predicate;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CarRepository carRepository;

    public OrderService(OrderRepository orderRepository, CarRepository carRepository) {
        this.orderRepository = orderRepository;
        this.carRepository = carRepository;
    }

    public boolean create(String email, int carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        optionalCar.ifPresent(car -> {
            int priceToSave = calculatePriceToSave(email, car);
            orderRepository.save(Order.of(email, car, priceToSave));
        });

        return true;
    }

    private int calculatePriceToSave(String email, Car car) {
        int priceToSave = car.getPrice();
        if (hasPreviousOrder(email)) {
            priceToSave = applyTenPercent(priceToSave);
        }
        return priceToSave;
    }

    private int applyTenPercent(int priceToSave) {
        return (int) Math.round(0.9 * priceToSave);
    }

    private boolean hasPreviousOrder(String email) {
        return orderRepository.findAll().stream()
                .anyMatch(filterByEmail(email));
    }

    private Predicate<Order> filterByEmail(String email) {
        return order -> email.equals(order.getEmail());
    }
}
