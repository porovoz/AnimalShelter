package com.coffeebreak.animalshelter.repositories;

import com.coffeebreak.animalshelter.models.AnimalReportPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalPhotoReportRepository extends JpaRepository<AnimalReportPhoto, Long> {
    AnimalReportPhoto findByAnimalReportDataId(Long id);
}
