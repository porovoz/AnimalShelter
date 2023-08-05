package com.coffeebreak.animalshelter.controllers;

import com.coffeebreak.animalshelter.listener.TelegramBotUpdatesListener;
import com.coffeebreak.animalshelter.models.AnimalReportData;
import com.coffeebreak.animalshelter.services.AnimalReportDataService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<AnimalReportData> createAnimalReport(@RequestBody AnimalReportData animalReportData) {
        AnimalReportData createdAnimalReportData = animalReportDataService.createAnimalReportData ( animalReportData );
        return ResponseEntity.ok(createdAnimalReportData);
    }

    @GetMapping("/all_report")
    public ResponseEntity<Collection<AnimalReportData>> getAllDAnimalReportData() {
        Collection<AnimalReportData> animalReportDataAll = animalReportDataService.findAllAnimalReport (  );
        if (animalReportDataAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animalReportDataAll);
    }

    @PutMapping
    public ResponseEntity<AnimalReportData> updateAnimalReportData(@RequestBody AnimalReportData animalReportData) {
        AnimalReportData updatedAnimalReportData = animalReportDataService.updateAnimalReportData ( animalReportData );
        if (updatedAnimalReportData == null) {
            return ResponseEntity.notFound().build ();
        }
        return ResponseEntity.ok(updatedAnimalReportData);
    }

    @DeleteMapping("/{animalReportDataId}")
    public ResponseEntity<Void> deleteAnimalReportDataById(@PathVariable("animalReportDataId") Long animalReportData) {
        animalReportDataService.deleteAnimalReportData (animalReportData);
        return ResponseEntity.ok().build();
    }

    //работа с файлами
    @GetMapping("/{id}/photo-from-db")
    public ResponseEntity<byte[]> downloadPhotoFromDB(@Parameter(description = "report id") @PathVariable Long id) {
        AnimalReportData reportData = this.animalReportDataService.findById(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(fileType));
        headers.setContentLength(reportData.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(reportData.getData());
    }

    @GetMapping("/message-to-person")
    public void sendMessageToPerson(@Parameter(description = "id чата с пользователем", example = "3984892310")
                                    @RequestParam Long chat_Id,
                                    @Parameter(description = "Ваше сообщение")
                                    @RequestParam String message) {
        this.telegramBotUpdatesListener.sendMessage(chat_Id, message);
    }
}