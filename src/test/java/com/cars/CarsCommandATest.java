package com.cars;

import com.cars.controller.CarController;
import com.cars.dto.DuplicateCarDto;
import com.cars.dto.UpdatedCarDto;
import com.cars.repository.CarRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.cucumber.java.fr.Quand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CarsCommandATest {

    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Quand("on duplique une {string} en {string} à {int}€")
    public void onDupliqueUneEnÀ€(String originalName, String newName, int newPrice) throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new CarController(carRepository)).build();

        mockMvc.perform(
                post("/api/car/" + originalName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(new DuplicateCarDto(newName, newPrice)))
                )
                .andExpect(status().isCreated());
    }

    @Quand("on met à jour le prix à {int} de la voiture \\({int})")
    public void onMetÀJourLePrixÀDeLaVoiture(int newPrice, int id) throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(new CarController(carRepository)).build();

        mockMvc.perform(
                        patch("/api/car/" + id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(toJson(new UpdatedCarDto(newPrice)))
                )
                .andExpect(status().isNoContent());
    }

    private String toJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMobjectMapper = new ObjectMapper();
        objectMobjectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = objectMobjectMapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}
