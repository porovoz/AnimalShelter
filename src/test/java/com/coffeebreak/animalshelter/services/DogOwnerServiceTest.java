package com.coffeebreak.animalshelter.services;

import com.coffeebreak.animalshelter.exceptions.DogOwnerNotFoundException;
import com.coffeebreak.animalshelter.models.DogOwner;
import com.coffeebreak.animalshelter.repositories.DogOwnerRepository;
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
 *
 * @see DogOwner
 * @see DogOwnerRepository
 * @see DogOwnerService
 * @see DogOwnerServiceTest
 */

@ExtendWith(MockitoExtension.class)
public class DogOwnerServiceTest {

    @Mock
    private DogOwnerRepository dogOwnerRepository;

    @InjectMocks
    private DogOwnerService dogOwnerService;

    private final DogOwner expected = new DogOwner();
    private final DogOwner expected1 = new DogOwner();

    @BeforeEach
    public void setUp() {
        expected.setId(1L);
        expected.setFullName("Василий Васильевич");
        expected.setAge(1990);
        expected.setAddress("Москва");
        expected.setPhoneNumber("98887776655");

        expected1.setId(1L);
        expected1.setFullName("Иван Иванович");
        expected1.setAge(1991);
        expected1.setAddress("Рязань");
        expected1.setPhoneNumber("12223334455");
    }


    /**
     * Тестирование метода <b>add()</b> в DogOwnerService
     * <br>
     * Mockito: когда вызывается метод <b>DogOwnerRepository::save</b>,
     * возвращается владелец собаки <b>expected</b>
     */
    @Test
    @DisplayName("Проверка добавления нового владельца собаки и сохранения его в базе данных")
    public void createDogOwnerTest() {
        when(dogOwnerRepository.save(any(DogOwner.class))).thenReturn(expected);
        DogOwner actual = dogOwnerService.createDogOwner(expected);

        Assertions.assertThat(actual.getFullName()).isEqualTo(expected.getFullName());
        Assertions.assertThat(actual.getAge()).isEqualTo(expected.getAge());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getPhoneNumber()).isEqualTo(expected.getPhoneNumber());
    }


    /**
     * Тест на создание исключения в методе <b>get()</b> в DogOwnerService
     * <br>
     * Mockito: когда вызывается метод <b>DogOwnerRepository::findById</b>,
     * выбрасывается исключение <b>DogOwnerNotFoundException</b>
     *
     * @throws DogOwnerNotFoundException n
     */

    @Test
    @DisplayName("Проверка исключения в методе поиска владельца собаки по id")
    public void findDogOwnerByIdExceptionTest() {
        when(dogOwnerRepository.findById(any(Long.class))).thenThrow(DogOwnerNotFoundException.class);
        org.junit.jupiter.api.Assertions.assertThrows(DogOwnerNotFoundException.class, () -> dogOwnerService.findDogOwnerById(0L));
    }

    /**
     * Тестирование метода <b>findAllDogOwners()</b> в DogService
     * <br>
     * Mockito: когда вызывается метод <b>DogOwnerRepository::findAll</b>,
     * возвращается коллекция владельцев собак <b>DogOwner</b>
     */
    @Test
    @DisplayName("Проверка поиска всех владельцев собак и возвращения их из базы данных")
    public void findAllDogOwnersTest() {
        List<DogOwner> dogOwners = new ArrayList<>();
        dogOwners.add(expected);
        dogOwners.add(expected1);
        when(dogOwnerRepository.findAll()).thenReturn(dogOwners);
        Collection<DogOwner> actual = dogOwnerService.findAllDogOwners();

        Assertions.assertThat(actual.size()).isEqualTo(dogOwners.size());
        Assertions.assertThat(actual).isEqualTo(dogOwners);
    }

    /**
     * Тестирование метода <b>findAllDogOwners()</b> в DogOwnerService
     * <br>
     * Mockito: когда вызывается метод <b>DogOwnerRepository::findAll</b>,
     * возвращается пустая коллекция владельцев собак <b>DogOwner</b>
     */
    @Test
    @DisplayName("Проверка поиска всех собак и возвращения из базы данных пустого списка")
    public void findAllDogOwnersTestReturnsEmpty() {
        List<DogOwner> dogOwners = new ArrayList<>();
        when(dogOwnerRepository.findAll()).thenReturn(dogOwners);
        assertThat(dogOwnerService.findAllDogOwners()).isEqualTo(dogOwners);
    }


    /**
     * Тестирование метода <b>updateDogOwner()</b> в DogOwnerService
     * <br>
     * Mockito: когда вызывается метод <b>DogOwnerRepository::findById</b> и <b>DogOwnerRepository::save</b>,
     * возвращается отредактированный владелец собаки <b>expected</b>
     */
    @Test
    @DisplayName("Проверка обновления данных владельца собаки, сохранения и возвращения ее из базы данных")
    public void updateDogOwnerTest() {
        when(dogOwnerRepository.findById(any(Long.class))).thenReturn(Optional.of(expected));
        when(dogOwnerRepository.save(any(DogOwner.class))).thenReturn(expected);
        DogOwner actual = dogOwnerService.updateDogOwner(expected);

        Assertions.assertThat(actual.getFullName()).isEqualTo(expected.getFullName());
        Assertions.assertThat(actual.getAge()).isEqualTo(expected.getAge());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getPhoneNumber()).isEqualTo(expected.getPhoneNumber());
    }

    /**
     * Тест на создание исключения в методе <b>updateDogOwner()</b> в DogOwnerService
     * <br>
     * Mockito: когда вызывается метод <b>DogOwnerRepository::findById</b>,
     * выбрасывается исключение <b>DogOwnerNotFoundException</b>
     */
    @Test
    @DisplayName("Проверка исключения в методе редактирования владельца собаки")
    public void updateDogOwnerExceptionTest() {
        when(dogOwnerRepository.findById(any(Long.class))).thenThrow(DogOwnerNotFoundException.class);
        org.junit.jupiter.api.Assertions.assertThrows(DogOwnerNotFoundException.class,
                () -> dogOwnerService.updateDogOwner(expected));
    }
}