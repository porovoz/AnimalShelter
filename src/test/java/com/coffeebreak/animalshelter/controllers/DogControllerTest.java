package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.exceptions.DogNotFoundException;
import com.coffeebreak.animalshelter.models.Dog;
import com.coffeebreak.animalshelter.services.DogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Класс для проверки методов класса DogController
 * @see DogService
 */
@WebMvcTest(DogController.class)
public class DogControllerTest {

      // класс MockMvc предназначен для тестирования контроллеров
      @Autowired
      private MockMvc mockMvc;

      // класс ObjectMapper является основным функционалом для работы с форматом JSON
      @Autowired
      ObjectMapper objectMapper;

      @MockBean
      private DogService dogService;

      private final Dog expectedDogOne = new Dog();
      private final Dog expectedDogTwo = new Dog();

      @BeforeEach
      public void setUp() {
            expectedDogOne.setNickName("Graf");
            expectedDogOne.setDogBreed("sheepdog");
            expectedDogOne.setAge(1);
            expectedDogOne.setDescription("Test");

            expectedDogTwo.setId(2L);
            expectedDogTwo.setNickName("Rich");
            expectedDogTwo.setDogBreed("doberman");
            expectedDogTwo.setAge(2);
            expectedDogTwo.setDescription("Test");
      }

      /**
       * Проверка метода <b>createDog()</b> в классе DogController
       * <br>
       * Когда вызывается метод <b>DogService::createDog()</b>, возвращается ожидаемый объект класса Dog
       * @throws Exception может возникнуть исключение
       */
      @Test
      @DisplayName("Проверка метода создания собаки, сохранения ее в базе данных")
      void createDogTest() throws Exception {
            when(dogService.createDog(expectedDogOne)).thenReturn(expectedDogOne);
            mockMvc.perform(post("/dog")
                                .content(objectMapper.writeValueAsString(expectedDogOne))
                                .contentType(MediaType.APPLICATION_JSON))
                      .andExpect(status().isOk())
                      .andExpect(content().json(objectMapper.writeValueAsString(expectedDogOne)));
      }

      /**
       * Проверка метода <b>getDogById()</b> в классе DogController
       * <br>
       * Когда вызывается метод <b>DogService::findDogById()</b>, возвращается ожидаемый объект класса Dog
       * @throws Exception может возникнуть исключение
       */
      @Test
      @DisplayName("Проверка метода поиска собаки по id")
      public void getDogByIdTest() throws Exception {
            when(dogService.findDogById(anyLong())).thenReturn(expectedDogTwo);
            mockMvc.perform(get("/dog/{dogId}", expectedDogTwo.getId()))
                      .andExpect(status().isOk())
                      .andExpect(jsonPath("$.id").value(expectedDogTwo.getId()));
      }

      /**
       * Проверка метода <b>getDogById()</b> в классе DogController
       * <br>
       * Когда вызывается метод <b>DogService::findDogById</b>,
       * @throws DogNotFoundException выбрасывается исключение если объект не найден
       * @throws Exception может возникнуть исключение
       */
      @Test
      @DisplayName("Проверка выбрасывания исключения при поиске собаки по id, если собака не найдена в БД")
      public void getDogByIdExceptionTest() throws Exception {
            when(dogService.findDogById(anyLong())).thenThrow(DogNotFoundException.class);
            mockMvc.perform(get("/dog/{dogId}", expectedDogOne.getId()))
                      .andExpect(status().isOk());
      }

      /**
       * Проверка метода <b>getAllDogs()</b> в классе DogController
       * <br>
       * Когда вызывается метод <b>DogService::findAllDogs()</b>, возвращается коллекция ожидаемых объектов класса Dog
       * @throws Exception может возникнуть исключение
       */
      @Test
      @DisplayName("Проверка метода поиска списка всех собак")
      void getAllDogsTest() throws Exception {
            when(dogService.findAllDogs()).thenReturn(Arrays.asList(expectedDogOne, expectedDogTwo));
            mockMvc.perform(get("/dog"))
                      .andExpect(status().isOk())
                      .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(expectedDogOne, expectedDogTwo))));
      }

      /**
       * Проверка метода <b>updateDog()</b> в классе DogController
       * <br>
       * Когда вызывается метод <b>DogService::updateDog()</b>, возвращается ожидаемый объект класса Dog
       * @throws Exception может возникнуть исключение
       */
      @Test
      @DisplayName("Проверка метода изменения (обновления) данных собаки")
      public void updateDogTest() throws Exception {
            when(dogService.updateDog(expectedDogOne)).thenReturn(expectedDogOne);
            mockMvc.perform(put("/dog", expectedDogOne.getId())
                                .content(objectMapper.writeValueAsString(expectedDogOne))
                                .contentType(MediaType.APPLICATION_JSON))
                      .andExpect(status().isOk())
                      .andExpect(jsonPath("$.id").value(expectedDogOne.getId()))
                      .andExpect(jsonPath("$.nickName").value(expectedDogOne.getNickName()))
                      .andExpect(jsonPath("$.dogBreed").value(expectedDogOne.getDogBreed()))
                      .andExpect(jsonPath("$.age").value(expectedDogOne.getAge()))
                      .andExpect(jsonPath("$.description").value(expectedDogOne.getDescription()));
      }

      /**
       * Проверка метода <b>deleteDogById()</b> в классе DogController
       * <br>
       * @throws Exception может возникнуть исключение
       */
      @Test
      @DisplayName("Проверка метода удаления собаки")
      public void deleteDogByIdTest() throws Exception {
            doNothing().when(dogService).deleteDogById(expectedDogTwo.getId());
            mockMvc.perform(delete("/dog/{dogId}", expectedDogTwo.getId()))
                      .andExpect(status().isOk());
      }

      /**
       * Проверка метода <b>deleteDogById()</b> в классе DogController
       * <br>
       * Когда вызывается метод <b>DogService::deleteDogById</b>,
       * @throws DogNotFoundException выбрасывается исключение если объект не найден
       * @throws Exception может возникнуть исключение
       */
      @Test
      @DisplayName("Проверка выбрасывания исключения при удалении собаки по id, если собака не найдена в БД")
      public void deleteDogByIdExceptionTest() throws Exception {
            Long id = 10L;
            when(dogService.findDogById(anyLong())).thenThrow(DogNotFoundException.class);
            mockMvc.perform(delete("/dog/{dogId}", id))
                      .andExpect(status().isOk());
      }
}