package com.cars;

import com.cars.controller.ClientController;
import com.cars.dto.CreateClientDto;
import com.cars.model.Client;
import com.cars.repository.ClientRepository;
import com.cars.service.ClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Quand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Map;

import static com.cars.CarsATest.dataTableTransformEntries;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientsATest {
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientService clientService;
    private ResultActions resultActions;

    @Quand("on crée un nouveau client avec un email {string}")
    public void onCréeUnNouveauClientAvecUnEmail(String email) throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new ClientController(clientService)).build();
        resultActions = mockMvc.perform(
                post("/api/client/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(new CreateClientDto(email)))
        );
    }

    @Alors("on reçoit un Created pour le client")
    public void onReçoitUnCreatedPourLeClient() throws Exception {
        resultActions.andExpect(status().isCreated());
    }

    @Et("on récupère les informations suivantes de la base client")
    public void onRécupèreLesInformationsSuivantesDeLaBaseClient(DataTable dataTable) {
        List<Client> expectedClients = dataTableTransformEntries(dataTable, this::buildClient);

        final Integer clientId = clientRepository.findAll().iterator().next().getId();
        final Client client = clientRepository.findById(clientId).get();
        assertThat(client.getEmail()).isEqualTo(expectedClients.get(0).getEmail());
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMobjectMapper = new ObjectMapper();
        objectMobjectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMobjectMapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

    private Client buildClient(Map<String, String> entry) {
        return Client.of(entry.get("email"));
    }
}
