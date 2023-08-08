package com.coffeebreak.animalshelter.services;

import com.coffeebreak.animalshelter.models.AnimalReportData;
import com.coffeebreak.animalshelter.models.AnimalReportPhoto;
import com.coffeebreak.animalshelter.repositories.AnimalPhotoReportRepository;
import com.coffeebreak.animalshelter.repositories.AnimalReportDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AnimalReportPhotoService {

    @Value("${animal.reportPhotos.dir.path}")
    private String reportPhotosDir;

    private final AnimalReportDataService reportDataService;

    private final AnimalPhotoReportRepository photoReportRepository;


    public AnimalReportPhotoService(AnimalReportDataService reportDataService, AnimalPhotoReportRepository photoReportRepository, AnimalReportDataRepository reportDataRepository) {
        this.reportDataService = reportDataService;
        this.photoReportRepository = photoReportRepository;
    }

    public void uploadAnimalPhotoReportFile(Long id, MultipartFile file) throws IOException {
        AnimalReportData reportData = reportDataService.findById(id);
        Path filePath = Path.of(reportPhotosDir, id + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        AnimalReportPhoto animalReportPhoto = photoReportRepository.findByAnimalReportDataId(id);
        animalReportPhoto.setAnimalReportData(reportData);
        animalReportPhoto.setFilePath(filePath.toString());
        animalReportPhoto.setFileSize(file.getSize());
        animalReportPhoto.setMediaTypeFile(file.getContentType());
        animalReportPhoto.setData(generateImageData(filePath));
        photoReportRepository.save(animalReportPhoto);
    }


    private byte[] generateImageData(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            BufferedImage image = ImageIO.read(bis);
            int height = image.getHeight() / (image.getWidth() / 100);
            BufferedImage data = new BufferedImage(100, height, image.getType());
            Graphics2D graphics = data.createGraphics();
            graphics.drawImage(image, 0, 0, 100, height, null);
            graphics.dispose();
            ImageIO.write(data, getExtension(filePath.getFileName().toString()), baos);
            return baos.toByteArray();
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
