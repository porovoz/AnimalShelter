package com.coffeebreak.animalshelter.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dog")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nick_name", nullable = false, length = 25)
    private String nickName;  // прозвище

    @Column(name = "age")
    private Integer age;  // возраст

    @Column(name = "dog_breed", nullable = false, length = 25)
    private String dogBreed;  // порода собак

    @Column(name = "description")
    private String description;  // описание питомца

    public Dog() {
    }

    public Dog(String nickName , Integer age , String dogBreed , String description) {
        this.nickName = nickName;
        this.age = age;
        this.dogBreed = dogBreed;
        this.description = description;
    }

    public Dog(Long id , String nickName , Integer age , String dogBreed , String description) {
        this.id = id;
        this.nickName = nickName;
        this.age = age;
        this.dogBreed = dogBreed;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return Objects.equals(id, dog.id) && Objects.equals(nickName, dog.nickName) && Objects.equals(age, dog.age) && Objects.equals(dogBreed, dog.dogBreed) && Objects.equals(description, dog.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, age, dogBreed, description);
    }

    @Override
    public String toString() {
        return "Dog{" +
                  "id=" + id +
                  ", nickName='" + nickName + '\'' +
                  ", age=" + age +
                  ", dogBreed='" + dogBreed + '\'' +
                  ", description='" + description + '\'' +
                  '}';
    }
}