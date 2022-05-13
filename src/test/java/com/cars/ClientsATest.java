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

public class ClientsATest {
    private final List<Client> savedClients = new ArrayList<>();
    @Autowired
    protected TestEntityManager entityManager;
    @Autowired
    private ClientRepository clientRepository;

    @Etantdonné("Les clients suivants")
    public void lesClientsSuivants(DataTable dataTable) {
        List<Client> clients = dataTableTransformEntries(dataTable, ClientsATest::buildClient);

        for (Client client : clients) {
            var savedClient = entityManager.persist(client);
            savedClients.add(savedClient);
        }
    }

    @Et("on récupère les informations suivantes de la base client")
    public void onRécupèreLesInformationsSuivantesDeLaBaseClient(DataTable dataTable) {
        List<Client> expectedClients = dataTableTransformEntries(dataTable, ClientsATest::buildClient);

        assertThat(expectedClients).usingFieldByFieldElementComparator().containsExactly(clientRepository.findAll().stream().toArray(Client[]::new));
    }

    static Client buildClient(Map<String, String> entry) {
        if (entry.get("id") == null) {
            return Client.of(entry.get("email"));
        }
        return Client.of(Integer.parseInt(entry.get("id")), entry.get("email"));
    }
}
