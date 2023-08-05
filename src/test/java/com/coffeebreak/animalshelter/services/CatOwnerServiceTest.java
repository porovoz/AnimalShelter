package com.coffeebreak.animalshelter.services;

import com.coffeebreak.animalshelter.exceptions.CatNotFoundException;
import com.coffeebreak.animalshelter.exceptions.CatOwnerNotFoundException;
import com.coffeebreak.animalshelter.models.CatOwner;
import com.coffeebreak.animalshelter.repositories.CatOwnerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

/**
 * Класс для проверки CRUD-операций класса CatOwnerService
 * @see CatOwnerService
 * @see CatOwnerRepository
 */
@ExtendWith(MockitoExtension.class)
public class CatOwnerServiceTest {

    @Mock
    CatOwnerRepository catOwnerRepositoryMock;

    @InjectMocks
    private CatOwnerService catOwnerService;

    /**
     * Проверка метода <b>createCatOwner()</b> класса CatOwnerService
     * <br>
     * Когда вызывается метод <b>CatOwnerRepository::save()</b>, возвращается ожидаемый объект класса CatOwner
     */
    @Test
    @DisplayName("Проверка создания владельца кошки и добавления его в БД")
    void createCatOwnerTest() {
        CatOwner expected = new CatOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.when(catOwnerRepositoryMock.save(any(CatOwner.class))).thenReturn(expected);

        CatOwner actual = catOwnerService.createCatOwner(expected);

        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getFullName()).isEqualTo(expected.getFullName());
        Assertions.assertThat(actual.getAge()).isEqualTo(expected.getAge());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getPhoneNumber()).isEqualTo(expected.getPhoneNumber());

        Mockito.verify(catOwnerRepositoryMock, Mockito.times(1)).save(expected);
    }

    /**
     * Проверка метода <b>findCatOwnerById()</b> класса CatOwnerService
     * <br>
     * Когда вызывается метод <b>CatOwnerRepository::findById()</b>, возвращается ожидаемый объект класса CatOwner
     */
    @Test
    @DisplayName("Проверка поиска хозяина кошки по id")
    void findCatOwnerByIdTest() {
        CatOwner expected = new CatOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.when(catOwnerRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(expected));

        CatOwner actual = catOwnerService.findCatOwnerById(1L);

        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getFullName()).isEqualTo(expected.getFullName());
        Assertions.assertThat(actual.getAge()).isEqualTo(expected.getAge());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getPhoneNumber()).isEqualTo(expected.getPhoneNumber());

        // проверяем, что метод findById() был вызван один раз с нужным аргументом
        Mockito.verify(catOwnerRepositoryMock, Mockito.times(1)).findById(1L);

        // проверяем, что метод findById() был вызван только один раз
        Mockito.verify(catOwnerRepositoryMock, Mockito.times(1)).findById(Mockito.anyLong());
    }

    /**
     * Проверка выбрасывания исключения в методе <b>findCatOwnerById()</b> класса CatOwnerService
     * <br>
     * Когда вызывается метод <b>CatOwnerRepository::findById()</b>, выбрасывается исключение <b>CatOwnerNotFoundException</b>
     */
    @Test
    @DisplayName("Проверка выбрасывания исключения при поиске хозяина кошки по id")
    void findCatOwnerByIdExceptionTest() {
        Mockito.when(catOwnerRepositoryMock.findById(any(Long.class))).thenThrow(CatNotFoundException.class);

        org.junit.jupiter.api.Assertions.assertThrows(CatNotFoundException.class, () -> catOwnerService.findCatOwnerById(1L));
    }

    /**
     * Проверка метода <b>findAllCatOwners()</b> класса CatOwnerService
     * <br>
     * Когда вызывается метод <b>CatOwnerRepository::findAll()</b>, возвращается коллекция ожидаемых объектов класса CatOwner
     */
    @Test
    @DisplayName("Проверка поиска списка всех хозяев кошек")
    void findAllCatOwnersTest() {
        List<CatOwner> expected = new ArrayList<>();

        CatOwner catOwnerTestOne = new CatOwner(1L, "testFullName1", 30, "testAddress1", "testPhoneNumber1");
        expected.add(catOwnerTestOne);
        CatOwner catOwnerTestTwo = new CatOwner(2L, "testFullName2", 30, "testAddress2", "testPhoneNumber2");
        expected.add(catOwnerTestTwo);
        CatOwner catOwnerTestThree = new CatOwner(3L, "testFullName3", 30, "testAddress3", "testPhoneNumber3");
        expected.add(catOwnerTestThree);

        Mockito.when(catOwnerRepositoryMock.findAll()).thenReturn(expected);

        Collection<CatOwner> actual = catOwnerService.findAllCatOwners();

        Assertions.assertThat(actual.size()).isEqualTo(expected.size());
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    /**
     * Проверка метода <b>updateCatOwner()</b> класса CatOwnerService
     * <br>
     * Когда вызывается метод <b>CatOwnerRepository::save()</b>, возвращается ожидаемый объект класса CatOwner
     */
    @Test
    @DisplayName("Проверка изменения (обновления) данных владельца кошки и добавления его в БД")
    void updateCatOwnerTest() {
        CatOwner expected = new CatOwner(1L, "testFullName", 30, "testAddress", "testPhoneNumber");

        Mockito.when(catOwnerRepositoryMock.findById(any(Long.class))).thenReturn(Optional.of(expected));
        Mockito.when(catOwnerRepositoryMock.save(any(CatOwner.class))).thenReturn(expected);

        CatOwner actual = catOwnerService.updateCatOwner(expected);

        Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
        Assertions.assertThat(actual.getFullName()).isEqualTo(expected.getFullName());
        Assertions.assertThat(actual.getAge()).isEqualTo(expected.getAge());
        Assertions.assertThat(actual.getAddress()).isEqualTo(expected.getAddress());
        Assertions.assertThat(actual.getPhoneNumber()).isEqualTo(expected.getPhoneNumber());

        // проверяем, что метод findById() был вызван один раз с нужным аргументом
        Mockito.verify(catOwnerRepositoryMock, Mockito.times(1)).findById(1L);

        // проверяем, что метод save() был вызван один раз с нужным аргументом
        Mockito.verify(catOwnerRepositoryMock, Mockito.times(1)).save(expected);

        // проверяем, что метод findById() был вызван только один раз
        Mockito.verify(catOwnerRepositoryMock, Mockito.times(1)).findById(Mockito.anyLong());

        // проверяем, что метод save() был вызван только один раз
        Mockito.verify(catOwnerRepositoryMock, Mockito.times(1)).save(Mockito.any(CatOwner.class));
    }

    /**
     * Проверка выбрасывания исключения в методе <b>updateCatOwner()</b> класса CatOwnerService
     * <br>
     * Создание объекта <b>CatOwner</b> с id равным null
     */
    @Test
    @DisplayName("Проверка выбрасывания исключения при изменении (обновлении) данных владельца кошки и добавления его в БД")
    void updateCatOwnerExceptionTest() {
        CatOwner expected = new CatOwner();

        org.junit.jupiter.api.Assertions.assertThrows(CatOwnerNotFoundException.class, () -> catOwnerService.updateCatOwner(expected));
    }
}
