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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Тест - класс для проверки API endpoints при обращении к маршрутам отдельными HTTP методами
 * тестирование с использованием JUnit, Mockito и MockMvc
 * для класса - сервиса собак
 * @see Dog
 * @see DogService
 * @see DogController
 * @see DogControllerTest
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

      private final Dog expected = new Dog();
      private final Dog expected1 = new Dog();
      private final Dog actual = new Dog();
      private final Dog exceptionDog = new Dog();
      Long dogId = expected.getId();
      @BeforeEach
      public void setUp() {
            expected.setId(1L);
            expected.setNickName("Graf");
            expected.setDogBreed("sheepdog");
            expected.setAge(2022);
            expected.setDescription("Test");

            expected.setId(2L);
            expected.setNickName("Rich");
            expected.setDogBreed("doberman");
            expected.setAge(2021);
            expected.setDescription("Test");

            actual.setId(3L);
            actual.setNickName("Alima");
            actual.setDogBreed("no breed");
            actual.setAge(2019);
            actual.setDescription("Test");

            expected.setId(0L);
            expected.setNickName(" ");
            expected.setDogBreed("null");
            expected.setAge(0);
            expected.setDescription("");
      }

      /**
       * Тестирование метода <b>createDog()</b> в DogController
       * <br>
       * Mockito: когда вызывается метод <b>DogService::createDog</b>,
       * возвращается статус 200 и собака <b>expected</b>
       * @throws Exception
       */
      @Test
      @DisplayName("Проверка статуса 200 и возвращение собаки при ее создании, сохранении в базе данных")
      void createDogTest200() throws Exception {
            when(dogService.createDog(expected)).thenReturn(expected);
            mockMvc.perform(post("/dog")
                                .content(objectMapper.writeValueAsString(expected))
                                .contentType(MediaType.APPLICATION_JSON))
                      .andExpect(status().isOk())
                      .andExpect(content().json(objectMapper.writeValueAsString(expected)));
      }

      /**
       * Тестирование метода <b>getDogById()</b> в DogController
       * <br>
       * Mockito: когда вызывается метод <b>DogService::get</b>,
       * возвращается статус 200 и собака <b>expected</b>
       * @throws Exception
       */
      @Test
      @DisplayName("Проверка статуса 200 при получении собаки по id")
      public void getDogByIdTest200() throws Exception {
            when(dogService.findDogById(anyLong())).thenReturn(expected);
            mockMvc.perform(get("/dog/{id}", dogId))
                      .andExpect(status().isOk())
                      .andExpect(jsonPath("$.id").value(expected.getId()));
      }

      /**
       * Тестирование метода <b>getDogById()</b> в DogController
       * <br>
       * Mockito: когда вызывается метод <b>DogService::findDogById</b>,
       * выбрасывается исключение <b>DogNotFoundException</b> и
       * возвращается статус 404 <b>exceptionDog</b>
       * @throws Exception
       * @throws DogNotFoundException
       */
      @Test
      @DisplayName("Проверка статуса 404 при поиске собаки по id, которой нет в базе данных")
      public void getDogByIdTest404() throws Exception {
            when(dogService.findDogById(anyLong())).thenThrow(DogNotFoundException.class);
            assertThrows(DogNotFoundException.class, () -> dogService.findDogById(dogId));
      }

      /**
       * Тестирование метода <b>getAllDogs()</b> в DogController
       * <br>
       * Mockito: когда вызывается метод <b>DogService::findAllDogs</b>,
       * возвращается статус 200 и коллекция собак <b>Arrays.asList(expected, expected1)</b>
       */
      @Test
      @DisplayName("Проверка статуса 200 при получении всех собак в базе данных")
      void getAllDogsTest200() throws Exception {
            when(dogService.findAllDogs()).thenReturn(Arrays.asList(expected, expected1));
            mockMvc.perform(get("/dog/"))
                      .andExpect(status().isOk())
                      .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(expected, expected1))));
      }

      /**
       * Тестирование метода <b>updateDog()</b> в DogController
       * <br>
       * Mockito: когда вызывается метод <b>DogService::updateDog</b>,
       * возвращается статус 200 и отредактированная собака <b>expected</b>
       * @throws Exception
       */
      @Test
      @DisplayName("Проверка статуса 200 при попытке обновить и сохранить собаку в базе данных")
      public void updateDogTest200() throws Exception {
            when(dogService.updateDog(expected)).thenReturn(expected);
            mockMvc.perform(put("/dog", dogId)
                                .content(objectMapper.writeValueAsString(actual))
                                .contentType(MediaType.APPLICATION_JSON))
                      .andExpect(status().isOk())
                      .andExpect(jsonPath("$.id").value(actual.getId()))
                      .andExpect(jsonPath("$.nickName").value(actual.getNickName()))
                      .andExpect(jsonPath("$.dogBreed").value(actual.getDogBreed()))
                      .andExpect(jsonPath("$.age").value(actual.getAge()))
                      .andExpect(jsonPath("$.description").value(actual.getDescription()));
      }

      /**
       * Тестирование метода <b>uupdateDog()</b> в DogController
       * <br>
       * Mockito: когда вызывается метод <b>DogService::updateDog</b>,
       * выбрасывается исключение <b>DogNotFoundException</b> и
       * возвращается статус 404 <b>exceptionDog</b>
       * @throws Exception
       * @throws DogNotFoundException
       */
      @Test
      @DisplayName("Проверка статуса 404 при попытке обновить и сохранить собаку, которой нет в базе данных")
      public void updateDogTest404() throws Exception {
            when(dogService.updateDog(exceptionDog)).thenThrow(DogNotFoundException.class);
            mockMvc.perform(put("/dog", dogId)
                                .content(objectMapper.writeValueAsString(exceptionDog))
                                .contentType(MediaType.APPLICATION_JSON))
                      .andExpect(status().isNotFound());
      }

      /**
       * Тестирование метода <b>deleteDogById()</b> в DogController
       * <br>
       * Mockito: когда вызывается метод <b>DogService::deleteDogById</b>,
       * возвращается статус 200 <b>expected</b>
       * @throws Exception
       */
      @Test
      @DisplayName("Проверка статуса 200 при удалении собаки из базы данных по id")
      public void deleteDogByIdTest200() throws Exception {
            doNothing().when(dogService).deleteDogById(anyLong());
            mockMvc.perform(delete("/dog/{id}", dogId))
                      .andExpect(status().isNoContent());
      }

      /**
       * Тестирование метода <b>deleteDogById()</b> в DogController
       * <br>
       * Mockito: когда вызывается метод <b>DogService::deleteDogById</b>,
       * выбрасывается исключение <b>DogNotFoundException</b> и
       * возвращается статус 404 <b>exceptionDog</b>
       * @throws Exception
       * @throws DogNotFoundException
       */
      @Test
      @DisplayName("Проверка статуса 404 при попытке удалить по id собаку, которой нет в базе данных ")
      public void deleteDogByIdTest404() throws Exception {
            when(dogService.findDogById(anyLong())).thenThrow(DogNotFoundException.class);
            mockMvc.perform(delete("/dog/{id}", dogId))
                      .andExpect(status().isNotFound());
      }


}