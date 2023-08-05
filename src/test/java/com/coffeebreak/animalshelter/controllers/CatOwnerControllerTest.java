package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.models.CatOwner;
import com.coffeebreak.animalshelter.services.CatOwnerService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Класс для проверки методов класса CatOwnerController
 * @see CatOwnerService
 */
@WebMvcTest(CatOwnerController.class)
public class CatOwnerControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CatOwnerService catOwnerService;

    /**
     * Проверка метода <b>createCatOwner()</b> в классе CatOwnerController
     * <br>
     * Когда вызывается метод <b>CatOwnerService::createCatOwner()</b>, возвращается ожидаемый объект класса CatOwner
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода создания владельца кошки")
    void createCatOwnerTest() throws Exception {
        CatOwner expected = new CatOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.when(catOwnerService.createCatOwner(expected)).thenReturn(expected);

        mvc.perform(MockMvcRequestBuilders
                        .post("/cat_owner")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("testFullName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("testAddress"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("testPhoneNumber"))
                .andExpect(status().isOk());

        Mockito.verify(catOwnerService, Mockito.times(1)).createCatOwner(expected);
    }

    /**
     * Проверка метода <b>getCatOwnerById()</b> в классе CatOwnerController
     * <br>
     * Когда вызывается метод <b>CatOwnerService::findCatOwnerById()</b>, возвращается ожидаемый объект класса CatOwner
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода поиска хозяина кошки по id")
    void getCatOwnerByIdTest() throws Exception {
        CatOwner expected = new CatOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.when(catOwnerService.findCatOwnerById(any(Long.class))).thenReturn(expected);

        mvc.perform(MockMvcRequestBuilders
                        .get("/cat_owner/{catOwnerId}", 1L)
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("testFullName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("testAddress"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("testPhoneNumber"))
                .andExpect(status().isOk());

        Mockito.verify(catOwnerService, Mockito.times(1)).findCatOwnerById(1L);
    }

    /**
     * Проверка метода <b>getAllCatOwners()</b> в классе CatOwnerController
     * <br>
     * Когда вызывается метод <b>CatOwnerService::findAllCatOwners()</b>, возвращается коллекция ожидаемых объектов класса CatOwner
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода поиска списка всех хозяев кошек")
    void getAllCatOwnersTest() throws Exception {
        List<CatOwner> expected = new ArrayList<>();

        CatOwner catOwnerTestOne = new CatOwner(1L, "testFullName1", 30, "testAddress1", "testPhoneNumber1");
        expected.add(catOwnerTestOne);
        CatOwner catOwnerTestTwo = new CatOwner(2L, "testFullName2", 30, "testAddress2", "testPhoneNumber2");
        expected.add(catOwnerTestTwo);
        CatOwner catOwnerTestThree = new CatOwner(3L, "testFullName3", 30, "testAddress3", "testPhoneNumber3");
        expected.add(catOwnerTestThree);

        Mockito.when(catOwnerService.findAllCatOwners()).thenReturn(expected);

        mvc.perform(MockMvcRequestBuilders
                        .get("/cat_owner")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThan(0))))
                .andExpect(status().isOk());
    }

    /**
     * Проверка метода <b>updateCatOwner()</b> в классе CatOwnerController
     * <br>
     * Когда вызывается метод <b>CatOwnerService::updateCatOwner()</b>, возвращается ожидаемый объект класса CatOwner
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода изменения (обновления) данных хозяина кошки")
    void updateCatOwnerTest() throws Exception {
        CatOwner expected = new CatOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.when(catOwnerService.updateCatOwner(expected)).thenReturn(expected);

        mvc.perform(MockMvcRequestBuilders
                        .put("/cat_owner")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName").value("testFullName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("testAddress"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("testPhoneNumber"))
                .andExpect(status().isOk());

        Mockito.verify(catOwnerService, Mockito.times(1)).updateCatOwner(expected);
    }

    /**
     * Проверка метода <b>deleteCatOwnerById()</b> в классе CatOwnerController
     * <br>
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода удаления хозяина кошки")
    void deleteCatOwnerByIdTest() throws Exception {
        CatOwner expected = new CatOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.doNothing().when(catOwnerService).deleteCatOwnerById(expected.getId());

        mvc.perform(MockMvcRequestBuilders
                        .delete("/cat_owner/{catOwnerId}", expected.getId()))
                .andExpect(status().isOk());
    }
}
