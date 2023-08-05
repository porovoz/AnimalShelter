package com.coffeebreak.animalshelter.services;


import com.coffeebreak.animalshelter.exceptions.AnimalReportDataNotFoundException;
import com.coffeebreak.animalshelter.models.AnimalReportData;
import com.coffeebreak.animalshelter.repositories.AnimalReportDataRepository;
import com.pengrad.telegrambot.model.File;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@Service
public class AnimalReportDataService {
    private final AnimalReportDataRepository animalReportDataRepository;

    public AnimalReportDataService(AnimalReportDataRepository animalReportDataRepository) {
        this.animalReportDataRepository = animalReportDataRepository;
    }


    /**
     * Создание объекта класса AnimalReportData и сохранение его в БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param animalReportData объект класса AnimalReportData, не может быть null
     * @return созданный объект класса AnimalReportData
     */
    public AnimalReportData createAnimalReportData(AnimalReportData animalReportData) {
        return animalReportDataRepository.save ( animalReportData );
    }

    /**
     * Поиск объекта класса AnimalReportData по его идентификатору
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#findById(Object)}
     * @param id идентификатор искомого объекта класса AnimalReportData, не может быть null
     * @return найденный объект класса AnimalReportData
     * @throws AnimalReportDataNotFoundException если объект класса AnimalReportData не был найден в БД
     */
    public AnimalReportData findById(Long id) {
        return animalReportDataRepository.findById ( id ).orElseThrow ( AnimalReportDataNotFoundException::new );
    }

    /**
     * Изменение объекта класса AnimalReportData и сохранение его в БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#save(Object)}
     * @param animalReportData объект класса AnimalReportData, не может быть null
     * @return изменённый объект класса AnimalReportData
     * @throws AnimalReportDataNotFoundException если объект класса AnimalReportData не был найден в БД
     */
    public AnimalReportData updateAnimalReportData(AnimalReportData animalReportData) {
        if (animalReportData.getId () != null) {
            if (findById ( animalReportData.getId () ) != null) {
                return animalReportDataRepository.save ( animalReportData );
            }
        }
        throw new AnimalReportDataNotFoundException();

    }

    /**
     * Удаление объекта класса AnimalReportData по его идентификатору
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#deleteById(Object)}
     * @param id идентификатор искомого объекта класса AnimalReportData, не может быть null
     */
    public void deleteAnimalReportData(Long id) {
        animalReportDataRepository.deleteById ( id );
    }


    /**
     * Получение коллекции объектов класса AnimalReportData из БД
     * <br>
     * Используется метод репозитория {@link org.springframework.data.jpa.repository.JpaRepository#findAll()}
     * @return коллекция объектов класса AnimalReportData
     */
    public Collection<AnimalReportData> findAllAnimalReport() {
        return animalReportDataRepository.findAll ();
    }
    
    /*
    пока в работе
    public AnimalReportData findByChatId(Long chatId) {
        return animalReportDataRepository.findByChatId ( chatId );
    }

    public Collection<AnimalReportData> findListAnimalReport(Long chatId) {
        return animalReportDataRepository.findAllByChatId ( chatId );
    }*/

    //работа с файлами пока тестовый вариант
    public void uploadReportData(Long personId, byte[] pictureFile, File file, String ration, String health,
                                 String habits, String filePath, Date dateSendMessage, Long timeDate, long daysOfReports) throws IOException {
        AnimalReportData report = new AnimalReportData();
        report.setLastMessage(dateSendMessage);
        report.setDaysOfOwnership(daysOfReports);
        report.setFilePath(filePath);
        report.setFileSize(file.fileSize());
        report.setLastMessageMs(timeDate);
        report.setChatId(personId);
        report.setData(pictureFile);
        report.setRationOfAnimal(ration);
        report.setHealthOfAnimal(health);
        report.setHabitsOfAnimal(habits);
        this.animalReportDataRepository.save(report);
    }

    public void uploadReportData(Long personId, byte[] pictureFile, File file,
                                 String caption, String filePath, Date dateSendMessage, Long timeDate, long daysOfReports) throws IOException {
        AnimalReportData report = new AnimalReportData();//findById(ownerId);
        report.setLastMessage(dateSendMessage);
        report.setDaysOfOwnership(daysOfReports);
        report.setFilePath(filePath);
        report.setChatId(personId);
        report.setFileSize(file.fileSize());
        report.setData(pictureFile);
        report.setCaption(caption);
        report.setLastMessageMs(timeDate);
        this.animalReportDataRepository.save(report);
    }
}
