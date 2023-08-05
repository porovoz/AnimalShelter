package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.models.Cat;
import com.coffeebreak.animalshelter.services.CatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Класс-контроллер для объектов класса Cat
 * @see Cat
 * @see CatService
 */
@RestController
@RequestMapping("/cat")
@Tag(name = "Cats", description = "CRUD-операции для работы с кошками")
public class CatController {

      private final CatService catService;

      public CatController(CatService catService) {
            this.catService = catService;
      }

      @PostMapping
      @Operation(
                summary = "Создать новую кошку",
                description = "Создание новой кошки с ее уникальным идентификатором"
      )
      @ApiResponses(value = {
                @ApiResponse(
                          responseCode = "200",
                          description = "Кошка успешно создана",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = Cat.class))
                                    )
                          }
                )
      })
      public ResponseEntity<Cat> createCat(@RequestBody Cat cat) {
            Cat createdCat = catService.createCat(cat);
            return ResponseEntity.ok(createdCat);
      }

      @GetMapping("/{catId}")
      @Operation(
                summary = "Найти кошку по ее уникальному идентификатору ",
                description = "Поиск кошки по ее уникальному идентификатору"
      )
      @Parameters(value = {
                @Parameter(name = "Уникальный идентификатор кошки", example = "1")
      })
      @ApiResponses(value = {
                @ApiResponse(
                          responseCode = "200",
                          description = "Кошка успешно найдена",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = Cat.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "404",
                          description = "Кошка не найдена",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = Cat.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "500",
                          description = "Внутренняя ошибка сервера"
                )
      })
      public ResponseEntity<Cat> getCatById(@PathVariable("catId") Long catId) {
            Cat foundCat = catService.findCatById(catId);
            if (foundCat == null) {
                  return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(foundCat);
      }

      @GetMapping
      @Operation(
                summary = "Найти список всех кошек",
                description = "Показать список всех кошек"
      )
      @ApiResponses(value = {
                @ApiResponse(
                          responseCode = "200",
                          description = "Список кошек успешно найден",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = Cat.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "404",
                          description = "Список кошек не найден",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = Cat.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "500",
                          description = "Внутренняя ошибка сервера"
                )
      })
      public ResponseEntity<Collection<Cat>> getAllCats() {
            Collection<Cat> cats = catService.findAllCats();
            if (cats == null) {
                  return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(cats);
      }

      @PutMapping
      @Operation(
                summary = "Изменить (обновить) данные кошки",
                description = "Изменение (обновление) данных кошки"
      )
      @ApiResponses(value = {
                @ApiResponse(
                          responseCode = "200",
                          description = "Данные кошки успешно изменены (обновлены)",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = Cat.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "404",
                          description = "Кошка не найдена",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = Cat.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "500",
                          description = "Внутренняя ошибка сервера"
                )
      })
      public ResponseEntity<Cat> updateCat(@RequestBody Cat cat) {
            Cat updatedCat = catService.updateCat(cat);
            if (updatedCat == null) {
                  return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedCat);
      }

      @DeleteMapping("/{catId}")
      @Operation(
                summary = "Удаление кошки по ее уникальному идентификатору",
                description = "Поиск кошки для удаления по ее уникальному идентификатору"
      )
      @Parameters(value = {
                @Parameter(name = "Уникальный идентификатор кошки", example = "1")
      })
      @ApiResponses(value = {
                @ApiResponse(
                          responseCode = "200",
                          description = "Кошка успешно удалена"
                ),
                @ApiResponse(
                          responseCode = "404",
                          description = "Кошка не найдена"
                )
      })
      public ResponseEntity<Void> deleteCatById(@PathVariable("catId") Long catId) {
            catService.deleteCatById(catId);
            return ResponseEntity.ok().build();
      }
}