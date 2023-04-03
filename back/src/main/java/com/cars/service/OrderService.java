package com.cars.service;

import com.cars.model.Car;
import com.cars.model.Order;
import com.cars.repository.CarRepository;
import com.cars.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CarRepository carRepository;
    private final Clock clock;

    public OrderService(OrderRepository orderRepository, CarRepository carRepository, Clock clock) {
        this.orderRepository = orderRepository;
        this.carRepository = carRepository;
        this.clock = clock;
    }

    public boolean create(String email, int carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        optionalCar.ifPresent(car -> {
            int priceToSave = calculatePriceToSave(email, car);
            orderRepository.save(Order.of(email, car, priceToSave, LocalDate.now(clock)));
        });

        return true;
    }

    private int calculatePriceToSave(String email, Car car) {
        int priceToSave = car.getPrice();
        if (hasPreviousOrder(email)) {
            priceToSave = applyPercent(email, priceToSave);
        }
        return priceToSave;
    }

    private int applyPercent(String email, int priceToSave) {
        return hasRecentOrders(email) ? applyTwentyPercent(priceToSave) : applyTenPercent(priceToSave);
    }

    private int applyTenPercent(int priceToSave) {
        return (int) Math.round(0.9 * priceToSave);
    }

    private int applyTwentyPercent(int priceToSave) {
        return (int) Math.round(0.8 * priceToSave);
    }

    private boolean hasPreviousOrder(String email) {
        return orderRepository.findAll().stream()
                .anyMatch(filterByEmail(email));
    }

    private boolean hasManyOrders(String email) {
        return orderRepository.findAll().stream()
                .filter(filterByEmail(email)).count() > 1;
    }

    private boolean hasRecentOrders(String email) {
        return orderRepository.findAll().stream()
                .anyMatch(filterByEmailAndDate(email));
    }

    private Predicate<Order> filterByEmailAndDate(String email) {
        return order -> email.equals(order.getEmail()) && orderDateLessOneYear(order.getOrderDate());
    }

    private Boolean orderDateLessOneYear(LocalDate orderDate) {
        LocalDate todayLessOneYear = LocalDate.now(clock).minusYears(1);
        return todayLessOneYear.isBefore(orderDate);
    }

    private Predicate<Order> filterByEmail(String email) {
        return order -> email.equals(order.getEmail());
    }
}
