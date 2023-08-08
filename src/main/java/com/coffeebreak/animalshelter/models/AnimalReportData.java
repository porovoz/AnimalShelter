package com.coffeebreak.animalshelter.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.Objects;

@Entity
public class AnimalReportData {

    @Id
    @GeneratedValue
    private Long id;
    private Long chatId;
    private String rationOfAnimal; //рацион питания животного
    private String healthOfAnimal; //самочувствие животного и привыкание животного к новому месту
    private String habitsOfAnimal; //привычки животного
    private Long daysOfOwnership; //время владения
    AnimalReportPhoto animalReportPhoto;

    public AnimalReportData(Long id, Long chatId, String rationOfAnimal, String healthOfAnimal, String habitsOfAnimal, Long daysOfOwnership, AnimalReportPhoto animalReportPhoto) {
        this.id = id;
        this.chatId = chatId;
        this.rationOfAnimal = rationOfAnimal;
        this.healthOfAnimal = healthOfAnimal;
        this.habitsOfAnimal = habitsOfAnimal;
        this.daysOfOwnership = daysOfOwnership;
        this.animalReportPhoto = animalReportPhoto;
    }

    public AnimalReportData() {
    }

    public AnimalReportData(Long id, Long chatId, String rationOfAnimal, String healthOfAnimal, String habitsOfAnimal, Long daysOfOwnership, String filePath, Long fileSize) {
        this.id = id;
        this.chatId = chatId;
        this.rationOfAnimal = rationOfAnimal;
        this.healthOfAnimal = healthOfAnimal;
        this.habitsOfAnimal = habitsOfAnimal;
        this.daysOfOwnership = daysOfOwnership;
    }

    public AnimalReportData(String rationOfAnimal , String healthOfAnimal , String habitsOfAnimal) {
        this.rationOfAnimal = rationOfAnimal;
        this.healthOfAnimal = healthOfAnimal;
        this.habitsOfAnimal = habitsOfAnimal;
    }

    public AnimalReportData(Long chatId , String rationOfAnimal , String healthOfAnimal , String habitsOfAnimal , byte[] data) {
        this.chatId = chatId;
        this.rationOfAnimal = rationOfAnimal;
        this.healthOfAnimal = healthOfAnimal;
        this.habitsOfAnimal = habitsOfAnimal;
    }

    public AnimalReportData(Long chatId , byte[] data) {
        this.chatId = chatId;
    }

    public AnimalReportData(Long id, Long chatId, String rationOfAnimal, String healthOfAnimal, String habitsOfAnimal, Long daysOfOwnership, String filePath, Long fileSize, byte[] data, String caption, Date lastMessage, Long lastMessageMs) {
        this.id = id;
        this.chatId = chatId;
        this.rationOfAnimal = rationOfAnimal;
        this.healthOfAnimal = healthOfAnimal;
        this.habitsOfAnimal = habitsOfAnimal;
        this.daysOfOwnership = daysOfOwnership;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getRationOfAnimal() {
        return rationOfAnimal;
    }

    public void setRationOfAnimal(String rationOfAnimal) {
        this.rationOfAnimal = rationOfAnimal;
    }

    public String getHealthOfAnimal() {
        return healthOfAnimal;
    }

    public void setHealthOfAnimal(String healthOfAnimal) {
        this.healthOfAnimal = healthOfAnimal;
    }

    public String getHabitsOfAnimal() {
        return habitsOfAnimal;
    }

    public void setHabitsOfAnimal(String habitsOfAnimal) {
        this.habitsOfAnimal = habitsOfAnimal;
    }

    public Long getDaysOfOwnership() {
        return daysOfOwnership;
    }

    public void setDaysOfOwnership(Long daysOfOwnership) {
        this.daysOfOwnership = daysOfOwnership;
    }

    public AnimalReportPhoto getAnimalReportPhoto() {
        return animalReportPhoto;
    }

    public void setAnimalReportPhoto(AnimalReportPhoto animalReportPhoto) {
        this.animalReportPhoto = animalReportPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalReportData that = (AnimalReportData) o;
        return Objects.equals(id, that.id) && Objects.equals(chatId, that.chatId) && Objects.equals(rationOfAnimal, that.rationOfAnimal) && Objects.equals(healthOfAnimal, that.healthOfAnimal) && Objects.equals(habitsOfAnimal, that.habitsOfAnimal) && Objects.equals(daysOfOwnership, that.daysOfOwnership) && Objects.equals(animalReportPhoto, that.animalReportPhoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, rationOfAnimal, healthOfAnimal, habitsOfAnimal, daysOfOwnership, animalReportPhoto);
    }

}
