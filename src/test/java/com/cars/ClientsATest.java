package com.cars;

import com.cars.model.Client;
import com.cars.repository.ClientRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.cars.CarsATest.dataTableTransformEntries;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@AutoConfigureTestEntityManager
public class ClientsATest {
    private final List<Client> savedClients = new ArrayList<>();
    @Autowired
    protected TestEntityManager entityManager;
    @Autowired
    private ClientRepository clientRepository;

    @Etantdonné("Les clients suivants")
    public void lesClientsSuivants(DataTable dataTable) {
        List<Client> clients = dataTableTransformEntries(dataTable, this::buildClient);

        for (Client client : clients) {
            var savedClient = entityManager.persist(client);
            savedClients.add(savedClient);
        }
    }

    @Et("on récupère les informations suivantes de la base client")
    public void onRécupèreLesInformationsSuivantesDeLaBaseClient(DataTable dataTable) {
        List<Client> expectedClients = dataTableTransformEntries(dataTable, this::buildClient);

        final Integer clientId = clientRepository.findAll().iterator().next().getId();
        final Client client = clientRepository.findById(clientId).get();
        assertThat(client.getEmail()).isEqualTo(expectedClients.get(0).getEmail());
    }

    private Client buildClient(Map<String, String> entry) {
        return Client.of(entry.get("email"));
    }
}
