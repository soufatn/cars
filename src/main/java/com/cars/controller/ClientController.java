package com.cars.controller;

import com.cars.dto.CreateClientDto;
import com.cars.dto.DuplicateClientDto;
import com.cars.repository.ClientRepository;
import com.cars.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    private final ClientService clientService;
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository, ClientService clientService) {
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @PostMapping("/api/client/")
    public ResponseEntity<Void> create(@RequestBody CreateClientDto createClientDto) {
        Boolean isCreated = clientService.create(createClientDto.email());
        if (isCreated) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/client/duplicate")
    public ResponseEntity<Void> duplicate(@RequestBody DuplicateClientDto duplicateClientDto) {
        clientService.duplicate(duplicateClientDto.originalEmail(), duplicateClientDto.newEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
