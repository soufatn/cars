package com.cars;

import com.cars.controller.OrderController;
import com.cars.dto.CreateOrderDto;
import com.cars.model.Car;
import com.cars.model.Client;
import com.cars.model.Order;
import com.cars.repository.CarRepository;
import com.cars.repository.OrderRepository;
import com.cars.service.OrderService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.cars.CarsATest.dataTableTransformEntries;
import static com.cars.CarsCommandATest.toJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@Transactional
@AutoConfigureTestEntityManager
public class OrderCommandATest {
    @Autowired
    protected TestEntityManager entityManager;
    private MockMvc mockMvc;
    private ResultActions resultActions;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Clock clock;

    @Etantdonné("Les clients suivants:")
    public void lesClientsSuivants(DataTable dataTable) {
        List<Client> clients = dataTableTransformEntries(dataTable, ClientsATest::buildClient);

        for (Client client : clients) {
            var savedClient = entityManager.persist(client);
        }
    }

    @Etantdonné("Les voitures suivantes:")
    public void lesVoituresSuivantes(DataTable dataTable) {
        List<Car> cars = dataTableTransformEntries(dataTable, CarsATest::buildCar);

        for (Car car : cars) {
            var savedCar = entityManager.persist(car);
        }
    }

    @Quand("on crée une nouvelle commande avec email {string} et idCar {int}")
    public void onCréeUneNouvelleCommande(String email, int carId) throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new OrderController(orderService)).build();
        resultActions = mockMvc.perform(
                post("/api/order/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(new CreateOrderDto(email, carId)))
        );
    }

    @Alors("on reçoit un Created pour la commande")
    public void onReçoitUnCreatedPourLaCommande() throws Exception {
        resultActions.andExpect(status().isCreated());
    }

    @Et("on récupère les informations suivantes de la base commande")
    public void onRécupèreLesInformationsSuivantesDeLaBaseCommande(DataTable dataTable) {
        List<Order> expectedOrders = dataTableTransformEntries(dataTable, this::buildOrder);
        for (Order expectedOrder: expectedOrders) {
            var isFound = orderRepository.findAll().stream().anyMatch(order -> order.isSame(expectedOrder));
            assertThat(isFound).isTrue();
        }
    }

    private Order buildOrder(Map<String, String> entry) {
        Car car = carRepository.findById(Integer.parseInt(entry.get("idVoiture"))).orElse(null);
        if (entry.get("id") == null) {
            try {
                return Order.of(entry.get("email"), car, Integer.parseInt(entry.get("price")), LocalDate.now(clock));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Order.of(Integer.parseInt(entry.get("id")), entry.get("email"), car, Integer.parseInt(entry.get("price")), LocalDate.now(clock));
    }

    @Et("les commandes suivantes:")
    public void lesCommandesSuivantes(DataTable dataTable) {
        List<Order> existingOrders = dataTableTransformEntries(dataTable, this::buildOrder);

        for (Order order : existingOrders) {
            entityManager.persist(order);
        }
    }
}
