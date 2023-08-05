package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.listener.TelegramBotUpdatesListener;
import com.coffeebreak.animalshelter.models.AnimalReportData;
import com.coffeebreak.animalshelter.repositories.AnimalReportDataRepository;
import com.coffeebreak.animalshelter.services.AnimalReportDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import java.util.Date;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimalReportDataController.class)
class AnimalReportDataControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Mock
    private AnimalReportDataRepository animalReportDataRepository;

    @MockBean
    private AnimalReportDataService reportDataService;

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
        reportTestOne.setFilePath("/path/to/file1");
        reportTestOne.setFileSize(1024L);
        reportTestOne.setData(new byte[]{1, 2, 3});
        reportTestOne.setCaption("Описание отчета");
        reportTestOne.setLastMessage(new Date());
        reportTestOne.setLastMessageMs(1590879578000L);

        reportTestTwo.setId(2L);
        reportTestTwo.setChatId(2L);
        reportTestTwo.setRationOfAnimal("Еда для животного 2");
        reportTestTwo.setHealthOfAnimal("Состояние животного 2");
        reportTestTwo.setHabitsOfAnimal("Привычки животного 2");
        reportTestTwo.setDaysOfOwnership(8L);
        reportTestTwo.setFilePath("/path/to/file2");
        reportTestTwo.setFileSize(1024L);
        reportTestTwo.setData(new byte[]{2, 3, 4});
        reportTestTwo.setCaption("Описание отчета 2");
        reportTestTwo.setLastMessage(new Date());
        reportTestTwo.setLastMessageMs(1690879578000L);

        reportListTest.add(reportTestOne);
        reportListTest.add(reportTestTwo);
    }

    @AfterEach
    void tearDown() { animalReportDataRepository.deleteAll();
    }

    @Test
    void createAnimalReportTest() throws Exception {
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.filePath").value("/path/to/file1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileSize").value(1024))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(reportTestOne.getData()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.caption").value("Описание отчета"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastMessage", Matchers.is("Fri Aug 04 14:52:59 MSK 2023")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastMessageMs").value(1590879578000L))
                .andExpect(status().isOk());

        Mockito.verify(reportDataService, Mockito.times(1)).createAnimalReportData(reportTestOne);
    }

    @Test
    void getAllDAnimalReportDataTest() throws Exception {
        when(reportDataService.findAllAnimalReport()).thenReturn(reportListTest);
        mvc.perform(MockMvcRequestBuilders.get("/report/all_report")
                .content(objectMapper.writeValueAsString(reportListTest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThan(0))))
                .andExpect(status().isOk());
    }

    @Test
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.filePath").value("/path/to/file1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileSize").value(1024))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(reportTestOne.getData()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.caption").value("Описание отчета"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastMessage", Matchers.is("Fri Aug 04 14:52:59 MSK 2023")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastMessageMs").value(1590879578000L))
                .andExpect(status().isOk());

        Mockito.verify(reportDataService, Mockito.times(1)).updateAnimalReportData(reportTestOne);
    }

    @Test
    void deleteAnimalReportDataByIdTest()throws Exception  {
            doNothing().when(reportDataService).deleteAnimalReportData(reportTestOne.getId());
            mvc.perform(MockMvcRequestBuilders.delete("/report/{animalReportDataId}",reportTestOne.getId()))
                    .andExpect(status().isOk());
    }
}