package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.models.Dog;
import com.coffeebreak.animalshelter.services.DogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Класс-контроллер для объектов класса Dog
 * @see Dog
 * @see DogService
 */
@RestController
@RequestMapping("/dog")
@Tag(name = "Dogs", description = "CRUD-операции для работы с собаками")
public class DogController {
    @Autowired
    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @PostMapping
    @Operation(
            summary = "Создать новую собаку",
            description = "Создание новой собаки с ее уникальным идентификатором"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Собака успешно создана",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Dog.class))
                            )
                    }
            )
    })
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        Dog createdDog = dogService.createDog(dog);
        return ResponseEntity.ok(createdDog);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Найти собаку по ее уникальному идентификатору ",
            description = "Поиск собаки по ее уникальному идентификатору"
    )
    @Parameters(value = {
            @Parameter(name = "Уникальный идентификатор собаки", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Собака успешно найдена",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Dog.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Собака не найдена",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Dog.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера"
            )
    })
    public ResponseEntity<Dog> getDogById(@PathVariable("dogId") Long dogId) {
        Dog foundDog = dogService.findDogById(dogId);
        if (foundDog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundDog);
    }

    @GetMapping
    @Operation(
            summary = "Найти список всех собак",
            description = "Показать список всех собак"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список собак успешно найден",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Dog.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Список собак не найден",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Dog.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера"
            )
    })
    public ResponseEntity<Collection<Dog>> getAllDogs() {
        Collection<Dog> dogs = dogService.findAllDogs();
        if (dogs == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dogs);
    }

    @PutMapping
    @Operation(
            summary = "Изменить (обновить) данные собаки",
            description = "Изменение (обновление) данных собаки"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные собаки успешно изменены (обновлены)",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Dog.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Собака не найдена",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema =
                                    @Schema(implementation = Dog.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Внутренняя ошибка сервера"
            )
    })
    public ResponseEntity<Dog> updateDog(@RequestBody Dog dog) {
        Dog updatedDog = dogService.updateDog(dog);
        if (updatedDog == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedDog);
    }

    @DeleteMapping("/{dogId}")
    @Operation(
            summary = "Удаление собаки по ее уникальному идентификатору",
            description = "Поиск собаки для удаления по ее уникальному идентификатору"
    )
    @Parameters(value = {
            @Parameter(name = "Уникальный идентификатор собаки", example = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Собака успешно удалена"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Собака не найдена"
            )
    })
    public ResponseEntity<Void> deleteDogById(@PathVariable("dogId") Long dogId) {
        dogService.deleteDogById(dogId);
        return ResponseEntity.ok().build();
    }
}
