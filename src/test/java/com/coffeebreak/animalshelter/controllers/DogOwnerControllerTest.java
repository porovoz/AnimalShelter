package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.models.DogOwner;
import com.coffeebreak.animalshelter.services.DogOwnerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Класс для проверки методов класса DogOwnerController
 * @see DogOwnerService
 */
@WebMvcTest(DogOwnerController.class)
public class DogOwnerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private DogOwnerService dogOwnerService;

    /**
     * Проверка метода <b>createDogOwner()</b> в классе DogOwnerController
     * <br>
     * Когда вызывается метод <b>DogOwnerService::createDogOwner()</b>, возвращается ожидаемый объект класса DogOwner
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода создания владельца собаки")
    void createDogOwnerTest() throws Exception {
        DogOwner expected = new DogOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.when(dogOwnerService.createDogOwner(expected)).thenReturn(expected);

        mvc.perform(MockMvcRequestBuilders
                            .post("/dog_owner")
                            .content(objectMapper.writeValueAsString(expected))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("testFullName"))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(30))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("testAddress"))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("testPhoneNumber"))
                  .andExpect(status().isOk());

        Mockito.verify(dogOwnerService, Mockito.times(1)).createDogOwner(expected);
    }

    /**
     * Проверка метода <b>getDogOwnerById()</b> в классе DogOwnerController
     * <br>
     * Когда вызывается метод <b>DogOwnerService::findDogOwnerById()</b>, возвращается ожидаемый объект класса DogOwner
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода поиска хозяина собаки по id")
    void getDogOwnerByIdTest() throws Exception {
        DogOwner expected = new DogOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.when(dogOwnerService.findDogOwnerById(any(Long.class))).thenReturn(expected);

        mvc.perform(MockMvcRequestBuilders
                            .get("/dog_owner/{dogOwnerId}", 1L)
                            .content(objectMapper.writeValueAsString(expected))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("testFullName"))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(30))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("testAddress"))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("testPhoneNumber"))
                  .andExpect(status().isOk());

        Mockito.verify(dogOwnerService, Mockito.times(1)).findDogOwnerById(1L);
    }

    /**
     * Проверка метода <b>getAllDogOwners()</b> в классе DogOwnerController
     * <br>
     * Когда вызывается метод <b>DogOwnerService::findAllDogOwners()</b>, возвращается коллекция ожидаемых объектов класса DogOwner
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода поиска списка всех хозяев собак")
    void getAllDogOwnersTest() throws Exception {
        List<DogOwner> expected = new ArrayList<>();

        DogOwner dogOwnerTestOne = new DogOwner(1L, "testFullName1", 30, "testAddress1", "testPhoneNumber1");
        expected.add(dogOwnerTestOne);
        DogOwner dogOwnerTestTwo = new DogOwner(2L, "testFullName2", 35, "testAddress2", "testPhoneNumber2");
        expected.add(dogOwnerTestTwo);
        DogOwner dogOwnerTestThree = new DogOwner(3L, "testFullName3", 40, "testAddress3", "testPhoneNumber3");
        expected.add(dogOwnerTestThree);

        Mockito.when(dogOwnerService.findAllDogOwners()).thenReturn(expected);

        mvc.perform(MockMvcRequestBuilders
                            .get("/dog_owner")
                            .content(objectMapper.writeValueAsString(expected))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                  .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThan(0))))
                  .andExpect(status().isOk());
    }

    /**
     * Проверка метода <b>updateDogOwner()</b> в классе DogOwnerController
     * <br>
     * Когда вызывается метод <b>DogOwnerService::updateDogOwner()</b>, возвращается ожидаемый объект класса DogOwner
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода изменения (обновления) данных хозяина собаки")
    void updateDogOwnerTest() throws Exception {
        DogOwner expected = new DogOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.when(dogOwnerService.updateDogOwner(expected)).thenReturn(expected);

        mvc.perform(MockMvcRequestBuilders
                            .put("/dog_owner")
                            .content(objectMapper.writeValueAsString(expected))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("testFullName"))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(30))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("testAddress"))
                  .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("testPhoneNumber"))
                  .andExpect(status().isOk());

        Mockito.verify(dogOwnerService, Mockito.times(1)).updateDogOwner(expected);
    }

    /**
     * Проверка метода <b>deleteDogOwnerById()</b> в классе DogOwnerController
     * <br>
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода удаления хозяина собаки")
    void deleteDogOwnerByIdTest() throws Exception {
        DogOwner expected = new DogOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.doNothing().when(dogOwnerService).deleteDogOwnerById(expected.getId());

        mvc.perform(MockMvcRequestBuilders
                            .delete("/dog_owner/{dogOwnerId}", expected.getId()))
                  .andExpect(status().isOk());
    }
}