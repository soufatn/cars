package com.cars;

import com.cars.controller.ClientController;
import com.cars.dto.CreateClientDto;
import com.cars.dto.DuplicateClientDto;
import com.cars.repository.ClientRepository;
import com.cars.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Quand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientsCommandATest {
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;
    private ResultActions resultActions;

    @Quand("on crée un nouveau client avec un email {string}")
    public void onCréeUnNouveauClientAvecUnEmail(String email) throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new ClientController(clientRepository, clientService)).build();
        resultActions = mockMvc.perform(
                post("/api/client/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(new CreateClientDto(email)))
        );
    }

    @Quand("on duplique un {string} en {string}")
    public void on_duplique_un_en(String originalEmail, String newEmail) throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new ClientController(clientRepository, clientService)).build();

        resultActions = mockMvc.perform(
                post("/api/client/duplicate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(new DuplicateClientDto(originalEmail, newEmail)))
        );
    }

    @Alors("on reçoit un Created pour le client")
    public void onReçoitUnCreatedPourLeClient() throws Exception {
        resultActions.andExpect(status().isCreated());
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMobjectMapper = new ObjectMapper();
        objectMobjectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMobjectMapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

    @Alors("on reçoit un BadRequest pour le client")
    public void onReçoitUnBadRequestPourLeClient() throws Exception {
        resultActions.andExpect(status().isBadRequest());
    }
}
