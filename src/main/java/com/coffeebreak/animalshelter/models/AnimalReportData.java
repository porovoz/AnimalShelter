package com.coffeebreak.animalshelter.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Arrays;
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
    private String filePath; // путь к файлу
    private Long fileSize; //размер файла
    @Lob
    private byte[] data;
    private String caption;
    private Date lastMessage;
    private Long lastMessageMs;

    public AnimalReportData() {
    }

    public AnimalReportData(Long id, Long chatId, String rationOfAnimal, String healthOfAnimal, String habitsOfAnimal, Long daysOfOwnership, String filePath, Long fileSize) {
        this.id = id;
        this.chatId = chatId;
        this.rationOfAnimal = rationOfAnimal;
        this.healthOfAnimal = healthOfAnimal;
        this.habitsOfAnimal = habitsOfAnimal;
        this.daysOfOwnership = daysOfOwnership;
        this.filePath = filePath;
        this.fileSize = fileSize;
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
        this.data = data;
    }

    public AnimalReportData(Long chatId , byte[] data) {
        this.chatId = chatId;
        this.data = data;
    }

    public AnimalReportData(Long id, Long chatId, String rationOfAnimal, String healthOfAnimal, String habitsOfAnimal, Long daysOfOwnership, String filePath, Long fileSize, byte[] data, String caption, Date lastMessage, Long lastMessageMs) {
        this.id = id;
        this.chatId = chatId;
        this.rationOfAnimal = rationOfAnimal;
        this.healthOfAnimal = healthOfAnimal;
        this.habitsOfAnimal = habitsOfAnimal;
        this.daysOfOwnership = daysOfOwnership;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.data = data;
        this.caption = caption;
        this.lastMessage = lastMessage;
        this.lastMessageMs = lastMessageMs;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Date getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Date lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Long getLastMessageMs() {
        return lastMessageMs;
    }

    public void setLastMessageMs(Long lastMessageMs) {
        this.lastMessageMs = lastMessageMs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        AnimalReportData that = (AnimalReportData) o;
        return fileSize.equals(that.fileSize) && Objects.equals ( id , that.id ) && Objects.equals ( chatId , that.chatId ) && Objects.equals ( rationOfAnimal , that.rationOfAnimal ) && Objects.equals ( healthOfAnimal , that.healthOfAnimal ) && Objects.equals ( habitsOfAnimal , that.habitsOfAnimal ) && Objects.equals ( daysOfOwnership , that.daysOfOwnership ) && Objects.equals ( filePath , that.filePath );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( id , chatId , rationOfAnimal , healthOfAnimal , habitsOfAnimal , daysOfOwnership , filePath , fileSize );
    }

    @Override
    public String toString() {
        return "AnimalReportData{" +
                  "id=" + id +
                  ", chatId=" + chatId +
                  ", rationOfAnimal='" + rationOfAnimal + '\'' +
                  ", healthOfAnimal='" + healthOfAnimal + '\'' +
                  ", habitsOfAnimal='" + habitsOfAnimal + '\'' +
                  ", daysOfOwnership=" + daysOfOwnership +
                  ", filePath='" + filePath + '\'' +
                  ", fileSize=" + fileSize +
                  ", data=" + Arrays.toString(data) +
                  ", caption='" + caption + '\'' +
                  ", lastMessage=" + lastMessage +
                  ", lastMessageMs=" + lastMessageMs +
                  '}';
    }
}