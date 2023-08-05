package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.models.Cat;
import com.coffeebreak.animalshelter.services.CatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CatController.class)
public class CatControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CatService catService;

    @Autowired
    ObjectMapper objectMapper;

    Cat cat = new Cat(1L,"Cat", 3, "Persian", "description");

    List<Cat> catList= new ArrayList<>(List.of(cat));

    @Test
    void getCatByIdTest() throws Exception {
        when(catService.findCatById(anyLong())).thenReturn(cat);
        mvc.perform(
                        get("/cat/{id}", cat.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(catService).findCatById(1L);
    }
    
    @Test
    void saveCatTest() throws Exception {
        when(catService.createCat(cat)).thenReturn(cat);
        mvc.perform(MockMvcRequestBuilders.post("/cat")
                        .content(objectMapper.writeValueAsString(cat))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.catBreed").value("Persian"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description"))
                .andExpect(status().isOk());
        Mockito.verify(catService, Mockito.times(1)).createCat(cat);
    }
    
    @Test
    void updateCatTest() throws Exception {
        when(catService.updateCat(cat)).thenReturn(cat);
        mvc.perform(MockMvcRequestBuilders.put("/cat")
                        .content(objectMapper.writeValueAsString(cat))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.catBreed").value("Persian"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description"))
                .andExpect(status().isOk());
        Mockito.verify(catService, Mockito.times(1)).updateCat(cat);
    }
    
    @Test
    void removeCatTest() throws Exception {
        mvc.perform(
                        delete("/cat/{id}", 1))
                .andExpect(status().isOk());
        verify(catService).deleteCatById(1L);
    }
    
    @Test
    void getAllCatsTest() throws Exception {
        when(catService.findAllCats()).thenReturn(catList);
        mvc.perform(MockMvcRequestBuilders.get("/cat")
                        .content(objectMapper.writeValueAsString(catList))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(Matchers.greaterThan(0))))
                .andExpect(status().isOk());
    }

}

