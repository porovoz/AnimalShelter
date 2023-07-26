package com.coffeebreak.animalshelter.services;

import com.coffeebreak.animalshelter.models.Cat;
import com.coffeebreak.animalshelter.repositories.CatRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CatService {
    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    /**
     * Получение списка кошек из БД
     * <br>
     * Используется метод репозитория {@link CatRepository#findAll()}
     *
     * @return список кошек
     */
    public Collection<Cat> findAllCats() {
        return catRepository.findAll();
    }
}