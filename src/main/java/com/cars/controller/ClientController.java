package com.cars.controller;

import com.cars.repository.ClientRepository;
import com.cars.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    public ClientController(ClientRepository clientRepository, ClientService clientService) {
    }
}
