package com.cars.controller;

import com.cars.dto.CreateCarDto;
import com.cars.dto.CreateClientDto;
import com.cars.repository.ClientRepository;
import com.cars.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/api/client/")
    public ResponseEntity<Void> create(@RequestBody CreateClientDto createClientDto) {
        clientService.create(createClientDto.email());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
