package com.cars.controller;

import com.cars.dto.CreateOrderDto;
import com.cars.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/order/")
    public ResponseEntity<Void> create(@RequestBody CreateOrderDto createOrderDto) {
        Boolean isCreated = orderService.create(createOrderDto.email(), createOrderDto.carId(), createOrderDto.price());
        if (isCreated) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
