package com.cars;

import com.cars.model.Car;
import com.cars.service.CarService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonné;
import io.cucumber.java.fr.Quand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import io.cucumber.spring.CucumberContextConfiguration;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@Transactional
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
@SpringBootTest
@DirtiesContext
@CucumberContextConfiguration
public class CarsATest {

    @Autowired
    protected TestEntityManager entityManager;

    private List<Car> cars;

    @Etantdonné("Les voitures suivantes")
    public void lesVoituresSuivantes(DataTable dataTable) {
        List<Car> cars = dataTableTransformEntries(dataTable, this::buildCar);

        for (Car car : cars) {
            entityManager.persist(car);
        }
    }

    private Car buildCar(Map<String, String> entry) {
        return new Car(entry.get("name"), Integer.parseInt(entry.get("price")));
    }

    @Quand("on liste les voitures")
    public void onListeLesVoitures() {
        final CarService carService = new CarService();
        cars = carService.listAllCars();
    }

    @Alors("on récupère les informations suivantes")
    public void onRécupèreLesInformationsSuivantes(DataTable dataTable) {
        List<Car> expectedCars = dataTableTransformEntries(dataTable, this::buildCarInfo);

        assertThat(cars).isEqualTo(expectedCars);
    }

    private Car buildCarInfo(Map<String, String> entry) {
        return Car.of(entry.get("name"), entry.get("category"));
    }

    private <T> List<T> dataTableTransformEntries(DataTable dataTable, Function<Map<String, String>, T> transformFunction) {
        final List<T> transformResults = new ArrayList<>();
        final List<Map<String, String>> dataTableEntries = dataTable.asMaps(String.class, String.class);
        dataTableEntries.forEach(mapEntry -> {
            transformResults.add(transformFunction.apply(mapEntry));
        });
        return transformResults;
    }
}
