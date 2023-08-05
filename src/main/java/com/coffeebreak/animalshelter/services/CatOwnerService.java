package com.coffeebreak.animalshelter.services;

import com.coffeebreak.animalshelter.exceptions.CatOwnerNotFoundException;
import com.coffeebreak.animalshelter.models.CatOwner;
import com.coffeebreak.animalshelter.repositories.CatOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Класс-сервис, содержащий CRUD-методы объекта класса CatOwner
 * @see CatOwner
 * @see CatOwnerRepository
 */
@Service
public class CatOwnerService {

    private final CatOwnerRepository catOwnerRepository;

    public CatOwnerService(CatOwnerRepository catOwnerRepository) {
        this.catOwnerRepository = catOwnerRepository;
    }

    /**
     * Создание объекта класса CatOwner и сохранение его в БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param catOwner объект класса CatOwner, не может быть null
     * @return созданный объект класса CatOwner
     */
    public CatOwner createCatOwner(CatOwner catOwner) {
        return catOwnerRepository.save(catOwner);
    }

    /**
     * Поиск объекта класса CatOwner по его идентификатору
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#findById(Object)}
     * @param catOwnerId идентификатор искомого объекта класса CatOwner, не может быть null
     * @return найденный объект класса CatOwner
     * @throws CatOwnerNotFoundException если объект класса CatOwner не был найден в БД
     */
    public CatOwner findCatOwnerById(Long catOwnerId) {
        return catOwnerRepository.findById(catOwnerId).orElseThrow(CatOwnerNotFoundException::new);
    }

    /**
     * Получение коллекции объектов класса CatOwner из БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#findAll()}
     * @return коллекция объектов класса CatOwner
     */
    public Collection<CatOwner> findAllCatOwners() {
        return catOwnerRepository.findAll();
    }

    /**
     * Изменение объекта класса CatOwner и сохранение его в БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param catOwner объект класса CatOwner, не может быть null
     * @return изменённый объект класса CatOwner
     * @throws CatOwnerNotFoundException если объект класса CatOwner не был найден в БД
     */
    public CatOwner updateCatOwner(CatOwner catOwner) {
        if (catOwner.getId() != null) {
            if (findCatOwnerById(catOwner.getId()) != null) {
                return catOwnerRepository.save(catOwner);
            }
        }
        throw new CatOwnerNotFoundException();
    }

    /**
     * Удаление объекта класса CatOwner по его идентификатору
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#deleteById(Object)}
     * @param catOwnerId идентификатор искомого объекта класса CatOwner, не может быть null
     */
    public void deleteCatOwnerById(Long catOwnerId) {
        catOwnerRepository.deleteById(catOwnerId);
    }
}