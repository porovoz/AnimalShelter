package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.models.DogOwner;
import com.coffeebreak.animalshelter.services.DogOwnerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Класс для проверки методов класса DogOwnerController
 *
 * @see DogOwnerService
 */
@WebMvcTest(DogOwnerController.class)
public class DogOwnerControllerTest {

    // класс MockMvc предназначен для тестирования контроллеров
    @Autowired
    private MockMvc mockMvc;

    // класс ObjectMapper является основным функционалом для работы с форматом JSON
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private DogOwnerService dogOwnerService;

    private final DogOwner expected = new DogOwner();
    private final DogOwner expected1 = new DogOwner();
    private final DogOwner actual = new DogOwner();
    private final DogOwner exceptionDogOwner = new DogOwner();
    Long dogOwnerId = expected.getId();

    @BeforeEach
    public void setUp() {
        expected.setId(1L);
        expected.setFullName("Иван Иванович");
        expected.setAge(1990);
        expected.setAddress("Москва");
        expected.setPhoneNumber("12223334455");

        expected.setId(2L);
        expected.setFullName("Василий Васильевич");
        expected.setAge(1980);
        expected.setAddress("Рязань");
        expected.setPhoneNumber("98887776655");

        actual.setId(3L);
        actual.setFullName("Сергей Сергеевич");
        actual.setAge(2000);
        actual.setAddress("Казань");
        actual.setPhoneNumber("0987654321");

        expected.setId(0L);
        expected.setFullName(" ");
        expected.setAge(0);
        expected.setAddress("");
        expected.setPhoneNumber("");
    }

    /**
     * Тестирование метода <b>createDogOwner()</b> в DogOwnerController
     * <br>
     * Mockito: когда вызывается метод <b>DogOwnerService::createDogOwner</b>,
     * возвращается статус 200 и владелец собаки <b>expected</b>
     *
     * @throws Exception
     */
    @Test
    @DisplayName("Проверка статуса 200 и возвращение владельца собаки при его создании, сохранении в базе данных")
    void createDogOwnerTest200() throws Exception {
        when(dogOwnerService.createDogOwner(expected)).thenReturn(expected);
        mockMvc.perform(post("/dog_owner")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }

    /**
     * Тестирование метода <b>getDogOwnerById()</b> в DogOwnerController
     * <br>
     * Mockito: когда вызывается метод <b>DogOwnerService::get</b>,
     * возвращается статус 200 и владелец собаки <b>expected</b>
     *
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода поиска владельца собаки по id")
    void getDogOwnerByIdTest() throws Exception {
        DogOwner expected = new DogOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.when(dogOwnerService.findDogOwnerById(any(Long.class))).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders
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
     *
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода поиска списка всех владельцев собак")
    void getAllDogOwnersTest() throws Exception {
        List<DogOwner> expected = new ArrayList<>();

        DogOwner dogOwnerTestOne = new DogOwner(1L, "testFullName1", 30, "testAddress1", "testPhoneNumber1");
        expected.add(dogOwnerTestOne);
        DogOwner dogOwnerTestTwo = new DogOwner(2L, "testFullName2", 30, "testAddress2", "testPhoneNumber2");
        expected.add(dogOwnerTestTwo);
        DogOwner dogOwnerTestThree = new DogOwner(3L, "testFullName3", 30, "testAddress3", "testPhoneNumber3");
        expected.add(dogOwnerTestThree);

        Mockito.when(dogOwnerService.findAllDogOwners()).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders
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
     *
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода изменения (обновления) данных владельца собаки")
    void updateDogOwnerTest() throws Exception {
        DogOwner expected = new DogOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.when(dogOwnerService.updateDogOwner(expected)).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders
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
     *
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода удаления владельца собаки")
    void deleteDogOwnerByIdTest() throws Exception {
        DogOwner expected = new DogOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.doNothing().when(dogOwnerService).deleteDogOwner(expected.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/dog_owner/{dogOwnerId}", expected.getId()))
                .andExpect(status().isOk());
    }

}

