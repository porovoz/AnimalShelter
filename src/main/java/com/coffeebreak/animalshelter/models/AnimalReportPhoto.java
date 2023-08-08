package com.coffeebreak.animalshelter.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class AnimalReportPhoto {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath; // путь к файлу
    private Long fileSize; //размер файла
    private String mediaTypeFile;//тип файла
    @Lob
    private byte[] data;//поток данных
    AnimalReportData animalReportData;

    public AnimalReportPhoto() {
    }
}
