package com.cars.controller;

import com.cars.dto.CreateClientDto;
import com.cars.repository.ClientRepository;
import com.cars.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ClientControllerUTest {

    private ClientController clientController;
    private ClientService clientService = mock(ClientService.class);
    private ClientRepository clientRepository = mock(ClientRepository.class);

    @BeforeEach
    void setUp() {
        clientController = new ClientController(clientRepository, clientService);
    }

    @Nested
    class CreateShould {
        @Test
        void call_clientService_create() {
            // given
            CreateClientDto createClientDto = new CreateClientDto("test@mail.com");

            // when
            clientController.create(createClientDto);

            // then
            verify(clientService).create("test@mail.com");
        }

        @Test
        void return_created_as_http_status() {
            // given
            CreateClientDto createClientDto = new CreateClientDto("test@mail.com");

            // when
            doReturn(true).when(clientService).create(createClientDto.email());
            var response = clientController.create(createClientDto);

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        }
    }
}
