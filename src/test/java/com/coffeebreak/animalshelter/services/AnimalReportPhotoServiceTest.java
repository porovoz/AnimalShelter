package com.coffeebreak.animalshelter.services;

import com.coffeebreak.animalshelter.models.AnimalReportData;
import com.coffeebreak.animalshelter.models.AnimalReportPhoto;
import com.coffeebreak.animalshelter.repositories.AnimalReportPhotoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalReportPhotoServiceTest {

//    @Value("${animal.reportPhotos.dir.path}")
//    private String reportPhotosDir;
//    @Mock
//    private AnimalReportDataService reportDataService;
//
//    @Mock
//    private AnimalReportPhotoRepository photoRepository;
//
//    @InjectMocks
//    private AnimalReportPhotoService reportPhotoService;
//
//    private final AnimalReportData testReport = new AnimalReportData(1L, 1L, "Test", "Test", "Test", 1L);
//
//    private final AnimalReportPhoto testPhoto = new AnimalReportPhoto(1L,"Test",1L,"Test");

    @Test
    void uploadAnimalPhotoReportFile() throws IOException {
    }

    @Test
    void findAnimalReportPhotoByAnimalReportDataId() {
    }
}