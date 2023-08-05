package com.coffeebreak.animalshelter.services;

import com.coffeebreak.animalshelter.exceptions.DogNotFoundException;
import com.coffeebreak.animalshelter.models.Dog;
import com.coffeebreak.animalshelter.repositories.DogRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Класс-сервис, содержащий CRUD-методы объекта класса Dog
 * @see Dog
 * @see DogRepository
 */
@Service
public class DogService {

    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    /**
     * Создание объекта класса Dog и сохранение его в БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param dog объект класса Dog, не может быть null
     * @return созданный объект класса Dog
     */
    public Dog createDog(Dog dog) {
        return dogRepository.save(dog);
    }

    /**
     * Поиск объекта класса Dog по его идентификатору
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#findById(Object)}
     * @param dogId идентификатор искомого объекта класса Dog, не может быть null
     * @return найденный объект класса Dog
     * @throws DogNotFoundException если объект класса Dog не был найден в БД
     */
    public Dog findDogById(Long dogId) {
        return dogRepository.findById(dogId).orElseThrow(DogNotFoundException::new);
    }

    /**
     * Получение коллекции объектов класса Dog из БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#findAll()}
     * @return коллекция объектов класса Dog
     */
    public Collection<Dog> findAllDogs() {
        return dogRepository.findAll();
    }

    /**
     * Изменение объекта класса Dog и сохранение его в БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param dog объект класса Dog, не может быть null
     * @return изменённый объект класса Dog
     * @throws DogNotFoundException если объект класса Dog не был найден в БД
     */
    public Dog updateDog(Dog dog) {
        if (dog.getId() != null) {
            if (findDogById(dog.getId()) != null) {
                return dogRepository.save(dog);
            }
        }
        throw new DogNotFoundException();
    }

    /**
     * Удаление объекта класса Dog по его идентификатору
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#deleteById(Object)}
     * @param dogId идентификатор искомого объекта класса Dog, не может быть null
     */
    public void deleteDogById(Long dogId) {
        dogRepository.deleteById(dogId);
    }
}