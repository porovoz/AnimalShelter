package com.coffeebreak.animalshelter.services;

import com.coffeebreak.animalshelter.exceptions.CatNotFoundException;
import com.coffeebreak.animalshelter.models.Cat;
import com.coffeebreak.animalshelter.repositories.CatRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Класс-сервис, содержащий CRUD-методы объекта класса Cat
 * @see Cat
 * @see CatRepository
 */
@Service
public class CatService {

    private final CatRepository catRepository;

    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    /**
     * Создание объекта класса Cat и сохранение его в БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param cat объект класса Cat, не может быть null
     * @return созданный объект класса Cat
     */
    public Cat createCat(Cat cat) {
        return catRepository.save(cat);
    }

    /**
     * Поиск объекта класса Cat по его идентификатору
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#findById(Object)}
     * @param catId идентификатор искомого объекта класса Cat, не может быть null
     * @return найденный объект класса Cat
     * @throws CatNotFoundException если объект класса Cat не был найден в БД
     */
    public Cat findCatById(Long catId) {
        return catRepository.findById(catId).orElseThrow(CatNotFoundException::new);
    }

    /**
     * Получение коллекции объектов класса Cat из БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#findAll()}
     * @return коллекция объектов класса Cat
     */
    public Collection<Cat> findAllCats() {
        return catRepository.findAll();
    }

    /**
     * Изменение объекта класса Cat и сохранение его в БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param cat объект класса Cat, не может быть null
     * @return изменённый объект класса Cat
     * @throws CatNotFoundException если объект класса Cat не был найден в БД
     */
    public Cat updateCat(Cat cat) {
        if (cat.getId() != null) {
            if (findCatById(cat.getId()) != null) {
                return catRepository.save(cat);
            }
        }
        throw new CatNotFoundException();
    }

    /**
     * Удаление объекта класса Cat по его идентификатору
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#deleteById(Object)}
     * @param catId идентификатор искомого объекта класса Cat, не может быть null
     */
    public void deleteCatById(Long catId) {
        catRepository.deleteById(catId);
    }
}