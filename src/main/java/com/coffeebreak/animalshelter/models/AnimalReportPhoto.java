package com.coffeebreak.animalshelter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class AnimalReportPhoto {

    @Id
    @GeneratedValue
    private Long id;
    private String filePath; // путь к файлу
    private Long fileSize; //размер файла
    private String mediaTypeFile;//тип файла
    @Lob
    private byte[] data;//поток данных
    @OneToOne
    private AnimalReportData animalReportData;

    public AnimalReportPhoto() {
    }
    public AnimalReportPhoto(Long id, String filePath, Long fileSize, String mediaTypeFile) {
        this.id = id;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.mediaTypeFile = mediaTypeFile;
    }

}
