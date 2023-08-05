package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.models.Cat;
import com.coffeebreak.animalshelter.services.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatController.class)
public class CatControllerTest {
      @Autowired
      private MockMvc mockMvc;

      @MockBean
      private CatService catService;
      @Test
      void getById() throws Exception {
            Cat cat = new Cat();
            cat.setId(1L);

            when(catService.findCatById(anyLong())).thenReturn(cat);

            mockMvc.perform(
                                get("/cat/{id}", 1L))
                      .andExpect(status().isOk())
                      .andExpect(jsonPath("$.id").value(1));

            verify(catService).findCatById(1L);
      }
      @Test
      void save() throws Exception {
            Cat cat = new Cat();
            cat.setId(1L);
            cat.setNickName("cat");
            JSONObject userObject = new JSONObject();
            userObject.put("id", 1L);
            userObject.put("name", "cat");

            when(catService.createCat(cat)).thenReturn(cat);

            mockMvc.perform(
                                post("/cat")
                                          .content(userObject.toString())
                                          .contentType(MediaType.APPLICATION_JSON))
                      .andExpect(status().isOk())
                      .andExpect(content().json(userObject.toString()));

            verify(catService).createCat(cat);
      }
      @Test
      void update() throws Exception {
            Cat cat = new Cat();
            cat.setId(1L);
            cat.setNickName("cat new");
            JSONObject userObject = new JSONObject();
            userObject.put("id", 1L);
            userObject.put("name", "cat new");

            when(catService.updateCat(cat)).thenReturn(cat);

            mockMvc.perform(
                                put("/cat")
                                          .content(userObject.toString())
                                          .contentType(MediaType.APPLICATION_JSON))
                      .andExpect(status().isOk())
                      .andExpect(content().json(userObject.toString()));

            verify(catService).updateCat(cat);
      }
      @Test
      void remove() throws Exception {
            mockMvc.perform(
                                delete("/cat/{id}", 1))
                      .andExpect(status().isOk());
            verify(catService).deleteCatById(1L);
      }
      @Test
      void getAll() throws Exception {
            when(catService.findAllCats()).thenReturn(List.of(new Cat()));

            mockMvc.perform(
                                get("/cat/all"))
                      .andExpect(status().isOk());
      }

}