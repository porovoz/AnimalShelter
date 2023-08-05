package com.coffeebreak.animalshelter.services;

import com.coffeebreak.animalshelter.exceptions.DogOwnerNotFoundException;
import com.coffeebreak.animalshelter.models.DogOwner;
import com.coffeebreak.animalshelter.repositories.DogOwnerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
/**
 * Класс-сервис, содержащий CRUD-методы объекта класса DogOwner
 * @see DogOwner
 * @see DogOwnerRepository
 */
@Service
public class DogOwnerService {
    private final DogOwnerRepository dogOwnerRepository;

    public DogOwnerService(DogOwnerRepository dogOwnerRepository) {
        this.dogOwnerRepository = dogOwnerRepository;
    }

    /**
     * Создание объекта класса DogOwner и сохранение его в БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param dogOwner объект класса DogOwner, не может быть null
     * @return созданный объект класса DogOwner
     */
    public DogOwner createDogOwner(DogOwner dogOwner) {
        return dogOwnerRepository.save(dogOwner);
    }

    /**
     * Поиск объекта класса DogOwner по его идентификатору
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#findById(Object)}
     * @param dogOwnerId идентификатор искомого объекта класса Dog, не может быть null
     * @return найденный объект класса DogOwner
     * @throws DogOwnerNotFoundException если объект класса DogOwner не был найден в БД
     */
    public DogOwner findDogOwnerById(Long dogOwnerId) {
        return dogOwnerRepository.findById(dogOwnerId).orElseThrow(DogOwnerNotFoundException::new);
    }

    /**
     * Получение коллекции объектов класса DogOwner из БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#findAll()}
     * @return коллекция объектов класса DogOwner
     */
    public Collection<DogOwner> findAllDogOwners() {
        return dogOwnerRepository.findAll();
    }

    /**
     * Изменение объекта класса DogOwner и сохранение его в БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param dogOwner объект класса DogOwner, не может быть null
     * @return изменённый объект класса DogOwner
     * @throws DogOwnerNotFoundException если объект класса DogOwner не был найден в БД
     */
    public DogOwner updateDogOwner(DogOwner dogOwner) {
        if (dogOwner.getId() != null) {
            if (findDogOwnerById(dogOwner.getId()) != null) {
                return dogOwnerRepository.save(dogOwner);
            }
        }
        throw new DogOwnerNotFoundException();
    }

    /**
     * Удаление объекта класса DogOwner по его идентификатору
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#deleteById(Object)}
     * @param dogOwnerId идентификатор искомого объекта класса DogOwner, не может быть null
     */
    public void deleteDogOwner(Long dogOwnerId) {
        dogOwnerRepository.deleteById(dogOwnerId);
    }
}