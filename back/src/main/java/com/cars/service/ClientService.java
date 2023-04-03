package com.cars.service;

import com.cars.model.Client;
import com.cars.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean create(String email) {
        Optional<Client> optionalClient = clientRepository.findByEmail(email);
        if (optionalClient.isPresent()) return false;
        clientRepository.save(Client.of(email));
        return true;
    }

    public void duplicate(String originalEmail, String newEmail) {
        final Optional<Client> optionalClient = clientRepository.findByEmail(originalEmail);
        optionalClient.ifPresent(car -> {
            final Client newClient = Client.of(newEmail);
            clientRepository.save(newClient);
        });
    }

}
