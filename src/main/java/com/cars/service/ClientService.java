package com.cars.service;

import com.cars.model.Client;
import com.cars.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void create(String email) {
        clientRepository.save(Client.of(email));
    }
}
