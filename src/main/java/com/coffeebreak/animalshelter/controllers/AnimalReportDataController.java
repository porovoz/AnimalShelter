package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.listener.TelegramBotUpdatesListener;
import com.coffeebreak.animalshelter.models.AnimalReportData;
import com.coffeebreak.animalshelter.models.AnimalReportPhoto;
import com.coffeebreak.animalshelter.repositories.AnimalPhotoReportRepository;
import com.coffeebreak.animalshelter.services.AnimalReportDataService;
import com.coffeebreak.animalshelter.services.AnimalReportPhotoService;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

/**
 * Класс-контроллер для объектов класса AnimalReportData
 *
 * @see AnimalReportData
 * @see AnimalReportDataService
 */
@RestController
@RequestMapping("/report")
@Tag(name = "AnimalReportData", description = "CRUD-операции для работы с отчетами.")
public class AnimalReportDataController {
    @Autowired
    private TelegramBotUpdatesListener telegramBotUpdatesListener;
    private final AnimalReportDataService animalReportDataService;

    private final AnimalReportPhotoService animalReportPhotoService;

    private final AnimalPhotoReportRepository animalPhotoReportRepository;

    public AnimalReportDataController(AnimalReportDataService animalReportDataService, AnimalReportPhotoService animalReportPhotoService, AnimalPhotoReportRepository animalPhotoReportRepository) {
        this.animalReportDataService = animalReportDataService;
        this.animalReportPhotoService = animalReportPhotoService;
        this.animalPhotoReportRepository = animalPhotoReportRepository;
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
        Collection<AnimalReportData> animalReportDataAll = animalReportDataService.findAllAnimalReport();
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
            return ResponseEntity.notFound().build();
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

    //работа с файлами
    @PostMapping(value = "/{id}/photo_report", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadPhotoReport(@PathVariable Long id, @RequestParam MultipartFile file) throws IOException {
        if (file.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        animalReportPhotoService.uploadAnimalPhotoReportFile(id, file);
        return ResponseEntity.ok().build();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @GetMapping("/{id}/photo_report/data")
    public ResponseEntity<byte[]> downloadPhotoReport(@PathVariable Long id) {
        AnimalReportPhoto animalReportPhoto = animalPhotoReportRepository.findByAnimalReportDataId(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(animalReportPhoto.getMediaTypeFile()));
        headers.setContentLength(animalReportPhoto.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(animalReportPhoto.getData());
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @GetMapping(value = "/{id}/photo_report")
    public void downloadPhotoReport(@PathVariable Long id, HttpServletResponse response) throws IOException {
        AnimalReportPhoto animalReportPhoto = animalPhotoReportRepository.findByAnimalReportDataId(id);
        Path path = Path.of(animalReportPhoto.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(animalReportPhoto.getMediaTypeFile());
            response.setContentLength(Math.toIntExact(animalReportPhoto.getFileSize()));
            is.transferTo(os);
        }
    }
}
