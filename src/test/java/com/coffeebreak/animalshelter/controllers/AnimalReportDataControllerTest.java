package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.listener.TelegramBotUpdatesListener;
import com.coffeebreak.animalshelter.models.AnimalReportData;
import com.coffeebreak.animalshelter.repositories.AnimalPhotoReportRepository;
import com.coffeebreak.animalshelter.services.AnimalReportDataService;
import com.coffeebreak.animalshelter.services.AnimalReportPhotoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
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
import java.util.Collection;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Класс для проверки методов класса AnimalReportDataController
 * @see AnimalReportDataService
 */
@WebMvcTest(AnimalReportDataController.class)
class AnimalReportDataControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private AnimalReportDataService reportDataService;

    @MockBean
    private AnimalReportPhotoService animalReportPhotoService;

    @MockBean
    private AnimalPhotoReportRepository animalPhotoReportRepository;

    @MockBean
    private TelegramBotUpdatesListener telegramBotUpdatesListener;

    AnimalReportData reportTestOne = new AnimalReportData();
    AnimalReportData reportTestTwo = new AnimalReportData();

    Collection<AnimalReportData> reportListTest=new ArrayList<>();

    @BeforeEach
    void setUp() {
        reportTestOne.setId(1L);
        reportTestOne.setChatId(1L);
        reportTestOne.setRationOfAnimal("Еда для животного");
        reportTestOne.setHealthOfAnimal("Состояние животного");
        reportTestOne.setHabitsOfAnimal("Привычки животного");
        reportTestOne.setDaysOfOwnership(7L);

        reportTestTwo.setId(2L);
        reportTestTwo.setChatId(2L);
        reportTestTwo.setRationOfAnimal("Еда для животного 2");
        reportTestTwo.setHealthOfAnimal("Состояние животного 2");
        reportTestTwo.setHabitsOfAnimal("Привычки животного 2");
        reportTestTwo.setDaysOfOwnership(8L);

        reportListTest.add(reportTestOne);
        reportListTest.add(reportTestTwo);
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Проверка метода <b>createAnimalReportData()</b> в классе AnimalReportDataController
     * <br>
     * Когда вызывается метод <b>AnimalReportDataService::createAnimalReportData()</b>, возвращается ожидаемый объект класса AnimalReportData
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода создания отчета")
    void createAnimalReportDataTest() throws Exception {
        when(reportDataService.createAnimalReportData(reportTestOne)).thenReturn(reportTestOne);

        mvc.perform(MockMvcRequestBuilders.post("/report")
                        .content(objectMapper.writeValueAsString(reportTestOne))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.chatId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rationOfAnimal").value("Еда для животного"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.healthOfAnimal").value("Состояние животного"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.habitsOfAnimal").value("Привычки животного"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysOfOwnership").value(7))
                .andExpect(status().isOk());

        Mockito.verify(reportDataService, Mockito.times(1)).createAnimalReportData(reportTestOne);
    }

    /**
     * Проверка метода <b>getAllAnimalReportData()</b> в классе AnimalReportDataController
     * <br>
     * Когда вызывается метод <b>AnimalReportDataService::findAllAnimalReport()</b>, возвращается коллекция ожидаемых объектов класса AnimalReportData
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода поиска списка всех отчетов")
    void getAllAnimalReportTest() throws Exception {
        when(reportDataService.findAllAnimalReport()).thenReturn(reportListTest);
        mvc.perform(MockMvcRequestBuilders.get("/report/all_report")
                .content(objectMapper.writeValueAsString(reportListTest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThan(0))))
                .andExpect(status().isOk());
    }

    /**
     * Проверка метода <b>updateAnimalReportData()</b> в классе AnimalReportDataController
     * <br>
     * Когда вызывается метод <b>AnimalReportDataService::updateAnimalReportData()</b>, возвращается ожидаемый объект класса AnimalReportData
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода изменения (обновления) данных отчета")
    void updateAnimalReportDataTest() throws Exception {
        when(reportDataService.updateAnimalReportData(reportTestOne)).thenReturn(reportTestOne);

        mvc.perform(MockMvcRequestBuilders.put("/report")
                        .content(objectMapper.writeValueAsString(reportTestOne))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.chatId").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rationOfAnimal").value("Еда для животного"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.healthOfAnimal").value("Состояние животного"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.habitsOfAnimal").value("Привычки животного"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysOfOwnership").value(7))
                .andExpect(status().isOk());

        Mockito.verify(reportDataService, Mockito.times(1)).updateAnimalReportData(reportTestOne);
    }

    /**
     * Проверка метода <b>deleteAnimalReportDataById()</b> в классе AnimalReportDataController
     * <br>
     * @throws Exception может возникнуть исключение
     */
    @Test
    @DisplayName("Проверка метода удаления отчета")
    void deleteAnimalReportDataByIdTest()throws Exception  {
            doNothing().when(reportDataService).deleteAnimalReportData(reportTestOne.getId());
            mvc.perform(MockMvcRequestBuilders.delete("/report/{animalReportDataId}", reportTestOne.getId()))
                    .andExpect(status().isOk());
    }
}