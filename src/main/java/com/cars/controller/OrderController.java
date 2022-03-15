package com.cars.controller;

import com.cars.service.OrderService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    public OrderController(OrderService orderService) {
    }
}
