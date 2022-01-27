package com.cars.controller;

import com.cars.dto.CreateClientDto;
import com.cars.dto.DuplicateClientDto;
import com.cars.model.Client;
import com.cars.repository.ClientRepository;
import com.cars.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
        clientService.create(createClientDto.email());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/api/client/{email}")
    public ResponseEntity<Void> duplicate(@PathVariable("email") String email, @RequestBody DuplicateClientDto duplicateClientDto) {
        final Optional<Client> optionalClient = clientRepository.findByEmail(email);
        optionalClient.ifPresent(car -> {
            final Client newClient = Client.of(duplicateClientDto.newEmail());
            clientRepository.save(newClient);
        });
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
