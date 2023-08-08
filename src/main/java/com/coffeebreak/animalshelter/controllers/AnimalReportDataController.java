package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.listener.TelegramBotUpdatesListener;
import com.coffeebreak.animalshelter.models.AnimalReportData;
import com.coffeebreak.animalshelter.services.AnimalReportDataService;
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
 * Класс-контроллер для объектов класса AnimalReportData
 * @see AnimalReportData
 * @see AnimalReportDataService
 */
@RestController
@RequestMapping("/report")
@Tag(name = "AnimalReportData", description = "CRUD-операции для работы с отчетами.")
public class AnimalReportDataController {
    @Autowired
    private TelegramBotUpdatesListener telegramBotUpdatesListener;
    private final String fileType = "image/jpeg";

    private  final AnimalReportDataService animalReportDataService;

    public AnimalReportDataController(AnimalReportDataService animalReportDataService) {
        this.animalReportDataService = animalReportDataService;
    }

    @PostMapping
    @Operation(
              summary = "Создать нового отчет о животном",
              description = "Создание нового отчета о животном с его уникальным идентификатором"
    )
    @ApiResponses(value = {
              @ApiResponse(
                        responseCode = "200",
                        description = "Отчет о животном успешно создан",
                        content = {
                                  @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = AnimalReportData.class))
                                  )
                        }
              )
    })
    public ResponseEntity<AnimalReportData> createAnimalReport(@RequestBody AnimalReportData animalReportData) {
        AnimalReportData createdAnimalReportData = animalReportDataService.createAnimalReportData(animalReportData);
        return ResponseEntity.ok(createdAnimalReportData);
    }

    @GetMapping("/all_report")
    @Operation(
              summary = "Найти список всех отчетов о животных",
              description = "Показать список всех отчетов о животных"
    )
    @ApiResponses(value = {
              @ApiResponse(
                        responseCode = "200",
                        description = "Список отчетов о животных успешно найден",
                        content = {
                                  @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = AnimalReportData.class))
                                  )
                        }
              ),
              @ApiResponse(
                        responseCode = "404",
                        description = "Список отчетов о животных не найден",
                        content = {
                                  @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = AnimalReportData.class))
                                  )
                        }
              ),
              @ApiResponse(
                        responseCode = "500",
                        description = "Внутренняя ошибка сервера"
              )
    })
    public ResponseEntity<Collection<AnimalReportData>> getAllAnimalReportData() {
        Collection<AnimalReportData> animalReportDataAll = animalReportDataService.findAllAnimalReport (  );
        if (animalReportDataAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animalReportDataAll);
    }

    @PutMapping
    @Operation(
              summary = "Изменить (обновить) данные отчета о животном",
              description = "Изменение (обновление) данных отчета о животном"
    )
    @ApiResponses(value = {
              @ApiResponse(
                        responseCode = "200",
                        description = "Данные отчета о животном успешно изменены (обновлены)",
                        content = {
                                  @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = AnimalReportData.class))
                                  )
                        }
              ),
              @ApiResponse(
                        responseCode = "404",
                        description = "Отчет о животном не найден",
                        content = {
                                  @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(schema =
                                            @Schema(implementation = AnimalReportData.class))
                                  )
                        }
              ),
              @ApiResponse(
                        responseCode = "500",
                        description = "Внутренняя ошибка сервера"
              )
    })
    public ResponseEntity<AnimalReportData> updateAnimalReportData(@RequestBody AnimalReportData animalReportData) {
        AnimalReportData updatedAnimalReportData = animalReportDataService.updateAnimalReportData(animalReportData);
        if (updatedAnimalReportData == null) {
            return ResponseEntity.notFound().build ();
        }
        return ResponseEntity.ok(updatedAnimalReportData);
    }

    @DeleteMapping("/{animalReportDataId}")
    @Operation(
              summary = "Удаление отчета о животном по его уникальному идентификатору",
              description = "Поиск отчета о животном для удаления по его уникальному идентификатору"
    )
    @Parameters(value = {
              @Parameter(name = "Уникальный идентификатор отчета о животном", example = "1")
    })
    @ApiResponses(value = {
              @ApiResponse(
                        responseCode = "200",
                        description = "Отчет о животном успешно удален"
              ),
              @ApiResponse(
                        responseCode = "404",
                        description = "Отчет о животном не найден"
              )
    })
    public ResponseEntity<Void> deleteAnimalReportDataById(@PathVariable("animalReportDataId") Long animalReportData) {
        animalReportDataService.deleteAnimalReportData(animalReportData);
        return ResponseEntity.ok().build();
    }

//    //работа с файлами
//    @GetMapping("/{id}/photo-from-db")
//    public ResponseEntity<byte[]> downloadPhotoFromDB(@Parameter(description = "report id") @PathVariable Long id) {
//        AnimalReportData reportData = this.animalReportDataService.findById(id);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(fileType));
//        headers.setContentLength(reportData.getData().length);
//
//        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(reportData.getData());
//    }
//
//    @GetMapping("/message-to-person")
//    public void sendMessageToPerson(@Parameter(description = "id чата с пользователем", example = "3984892310")
//                                    @RequestParam Long chat_Id,
//                                    @Parameter(description = "Ваше сообщение")
//                                    @RequestParam String message) {
//        this.telegramBotUpdatesListener.sendMessage(chat_Id, message);
//    }
}