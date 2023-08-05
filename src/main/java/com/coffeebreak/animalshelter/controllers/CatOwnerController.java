package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.models.CatOwner;

import com.coffeebreak.animalshelter.services.CatOwnerService;
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
 * Класс-контроллер для объектов класса CatOwner
 * @see CatOwner
 * @see CatOwnerService
 */
@RestController
@RequestMapping("/cat_owner")
@Tag(name = "CatOwners", description = "CRUD-операции для работы с владельцами кошек")

public class CatOwnerController {

      private final CatOwnerService catOwnerService;

      public CatOwnerController(CatOwnerService catOwnerService) {
            this.catOwnerService = catOwnerService;
      }

      @PostMapping
      @Operation(
                summary = "Создать нового владельца кошки",
                description = "Создание нового владельца кошки с его уникальным идентификатором"
      )
      @ApiResponses(value = {
                @ApiResponse(
                          responseCode = "200",
                          description = "Владелец кошки успешно создан",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = CatOwner.class))
                                    )
                          }
                )
      })
      public ResponseEntity<CatOwner> createCatOwner(@RequestBody CatOwner catOwner) {
            CatOwner createdCatOwner = catOwnerService.createCatOwner(catOwner);
            return ResponseEntity.ok(createdCatOwner);
      }

      @GetMapping("/{catOwnerId}")
      @Operation(
                summary = "Найти владельца кошки по ее уникальному идентификатору ",
                description = "Поиск владельца кошки по ее уникальному идентификатору"
      )
      @Parameters(value = {
                @Parameter(name = "Уникальный идентификатор владельца кошки", example = "1")
      })
      @ApiResponses(value = {
                @ApiResponse(
                          responseCode = "200",
                          description = "Владелец кошки успешно найден",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = CatOwner.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "404",
                          description = "Владелец кошки не найден",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = CatOwner.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "500",
                          description = "Внутренняя ошибка сервера"
                )
      })
      public ResponseEntity<CatOwner> getCatOwnerById(@PathVariable("catOwnerId") Long catOwnerId) {
            CatOwner foundCatOwner = catOwnerService.findCatOwnerById(catOwnerId);
            if (foundCatOwner == null) {
                  return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(foundCatOwner);
      }

      @GetMapping
      @Operation(
                summary = "Найти список всех владельцев кошек",
                description = "Показать список всех владельцев кошек"
      )
      @ApiResponses(value = {
                @ApiResponse(
                          responseCode = "200",
                          description = "Список владельцев кошек успешно найден",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = CatOwner.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "404",
                          description = "Список владельцев кошек не найден",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = CatOwner.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "500",
                          description = "Внутренняя ошибка сервера"
                )
      })
      public ResponseEntity<Collection<CatOwner>> getAllCatOwners() {
            Collection<CatOwner> catOwners = catOwnerService.findAllCatOwners();
            if (catOwners == null) {
                  return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(catOwners);
      }

      @PutMapping
      @Operation(
                summary = "Изменить (обновить) данные владельца кошки",
                description = "Изменение (обновление) данных владельца кошки"
      )
      @ApiResponses(value = {
                @ApiResponse(
                          responseCode = "200",
                          description = "Данные владельца кошки успешно изменены (обновлены)",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = CatOwner.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "404",
                          description = "Владелец кошки не найден",
                          content = {
                                    @Content(
                                              mediaType = MediaType.APPLICATION_JSON_VALUE,
                                              array = @ArraySchema(schema =
                                              @Schema(implementation = CatOwner.class))
                                    )
                          }
                ),
                @ApiResponse(
                          responseCode = "500",
                          description = "Внутренняя ошибка сервера"
                )
      })
      public ResponseEntity<CatOwner> updateCatOwner(@RequestBody CatOwner catOwner) {
            CatOwner updatedCatOwner = catOwnerService.updateCatOwner(catOwner);
            if (updatedCatOwner == null) {
                  return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedCatOwner);
      }

      @DeleteMapping("/{catOwnerId}")
      @Operation(
                summary = "Удаление владельца кошки по его уникальному идентификатору",
                description = "Поиск владельца кошки для удаления по его уникальному идентификатору"
      )
      @Parameters(value = {
                @Parameter(name = "Уникальный идентификатор владельца кошки", example = "1")
      })
      @ApiResponses(value = {
                @ApiResponse(
                          responseCode = "200",
                          description = "Владелец кошки успешно удален"
                ),
                @ApiResponse(
                          responseCode = "404",
                          description = "Владелец кошки не найден"
                )
      })
      public ResponseEntity<Void> deleteCatOwnerById(@PathVariable("catOwnerId") Long catOwnerId) {
            catOwnerService.deleteCatOwnerById(catOwnerId);
            return ResponseEntity.ok().build();
      }
}