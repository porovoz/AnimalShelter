package com.coffeebreak.animalshelter.services;

import com.coffeebreak.animalshelter.exceptions.DogNotFoundException;
import com.coffeebreak.animalshelter.models.Dog;
import com.coffeebreak.animalshelter.repositories.DogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

/**
 * Тест - класс для проверки CRUD операций в классе - сервисе собаки
 * @see Dog
 * @see DogRepository
 * @see DogService
 * @see DogServiceTest
 */
@ExtendWith(MockitoExtension.class)
public class DogServiceTest {
    @Mock
    private DogRepository dogRepository;
    @InjectMocks
    private DogService dogService;

    private final Dog expected = new Dog();
    private final Dog expected1 = new Dog();
    @BeforeEach
    public void setUp() {
        expected.setId(1L);
        expected.setNickName("Graf");
        expected.setDogBreed("sheepdog");
        expected.setAge(2022);
        expected.setDescription("Test");

        expected1.setId(2L);
        expected1.setNickName("Rich");
        expected1.setDogBreed("doberman");
        expected1.setAge(2021);
        expected1.setDescription("Test");
    }

    /**
     * Тестирование метода <b>add()</b> в DogService
     * <br>
     * Mockito: когда вызывается метод <b>DogRepository::save</b>,
     * возвращается собака <b>expected</b>
     */
    @Test
    @DisplayName("Проверка добавления новой собаки и сохранения ее в базе данных")
    public void createDogTest() {
        when(dogRepository.save(any(Dog.class))).thenReturn(expected);
        Dog actual = dogService.createDog(expected);

        Assertions.assertThat(actual.getNickName()).isEqualTo(expected.getNickName());
        Assertions.assertThat(actual.getDogBreed()).isEqualTo(expected.getDogBreed());
        Assertions.assertThat(actual.getAge()).isEqualTo(expected.getAge());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
    }

    /**
     * Тест на создание исключения в методе <b>get()</b> в DogService
     * <br>
     * Mockito: когда вызывается метод <b>DogRepository::findById</b>,
     * выбрасывается исключение <b>DogNotFoundException</b>
     * @throws DogNotFoundException
     */
    @Test
    @DisplayName("Проверка исключения в методе поиска собаки")
    public void findDogByIdExceptionTest() {
        when(dogRepository.findById(any(Long.class))).thenThrow(DogNotFoundException.class);
        org.junit.jupiter.api.Assertions.assertThrows(DogNotFoundException.class, () -> dogService.findDogById(0L));
    }

    /**
     * Тестирование метода <b>findAllDogs()</b> в DogService
     * <br>
     * Mockito: когда вызывается метод <b>DogRepository::findAll</b>,
     * возвращается коллекция собак <b>adopterDogs</b>
     */
    @Test
    @DisplayName("Проверка поиска всех собак и возвращения их из базы данных")
    public void findAllDogsTest() {
        List<Dog> dogs = new ArrayList<>();
        dogs.add(expected);
        dogs.add(expected1);
        when(dogRepository.findAll()).thenReturn(dogs);
        Collection<Dog> actual = dogService.findAllDogs();

        Assertions.assertThat(actual.size()).isEqualTo(dogs.size());
        Assertions.assertThat(actual).isEqualTo(dogs);
    }

    /**
     * Тестирование метода <b>findAllDogs()</b> в DogService
     * <br>
     * Mockito: когда вызывается метод <b>DogRepository::findAll</b>,
     * возвращается пустая коллекция собак <b>adopterDogs</b>
     */
    @Test
    @DisplayName("Проверка поиска всех собак и возвращения из базы данных пустого списка")
    public void findAllDogsTestReturnsEmpty() {
        List<Dog> dogs = new ArrayList<>();
        when(dogRepository.findAll()).thenReturn(dogs);
        assertThat(dogService.findAllDogs()).isEqualTo(dogs);
    }

    /**
     * Тестирование метода <b>updateDog()</b> в DogService
     * <br>
     * Mockito: когда вызывается метод <b>DogRepository::findById</b> и <b>DogRepository::save</b>,
     * возвращается отредактированная собака <b>expected</b>
     */
    @Test
    @DisplayName("Проверка обновления данных собаки, сохранения и возвращения ее из базы данных")
    public void updateDogTest() {
        when(dogRepository.findById(any(Long.class))).thenReturn(Optional.of(expected));
        when(dogRepository.save(any(Dog.class))).thenReturn(expected);
        Dog actual = dogService.updateDog(expected);

        Assertions.assertThat(actual.getNickName()).isEqualTo(expected.getNickName());
        Assertions.assertThat(actual.getDogBreed()).isEqualTo(expected.getDogBreed());
        Assertions.assertThat(actual.getAge()).isEqualTo(expected.getAge());
        Assertions.assertThat(actual.getDescription()).isEqualTo(expected.getDescription());
    }

    /**
     * Тест на создание исключения в методе <b>updateDog()</b> в DogService
     * <br>
     * Mockito: когда вызывается метод <b>DogRepository::findById</b>,
     * выбрасывается исключение <b>DogNotFoundException</b>
     * @throws DogNotFoundException
     */
    @Test
    @DisplayName("Проверка исключения в методе редактирования собаки")
    public void updateDogExceptionTest() {
        when(dogRepository.findById(any(Long.class))).thenThrow(DogNotFoundException.class);
        org.junit.jupiter.api.Assertions.assertThrows(DogNotFoundException.class,
                  () -> dogService.updateDog(expected));
    }

}