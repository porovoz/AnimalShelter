package com.coffeebreak.animalshelter.models;



import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cat")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nick_name", nullable = false, length = 25)
    private String nickName;  // прозвище

    @Column(name = "age")
    private Integer age;  // возраст

    @Column(name = "cat_breed", nullable = false, length = 25)
    private String catBreed;  // порода кошек

    @Column(name = "description")
    private String description;  // описание питомца

    public Cat() {
    }

    public Cat(String nickName , Integer age , String catBreed , String description) {
        this.nickName = nickName;
        this.age = age;
        this.catBreed = catBreed;
        this.description = description;
    }


    public Long getId() {
        return id;
    }
    public Cat(Long id , String nickName , Integer age , String catBreed , String description) {
        this.id = id;
        this.nickName = nickName;
        this.age = age;
        this.catBreed = catBreed;
        this.description = description;
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

    public String getCatBreed() {
        return catBreed;
    }

    public void setCatBreed(String catBreed) {
        this.catBreed = catBreed;
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
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id) && Objects.equals(nickName, cat.nickName) && Objects.equals(age, cat.age) && Objects.equals(catBreed, cat.catBreed) && Objects.equals(description, cat.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName, age, catBreed, description);
    }

    @Override
    public String toString() {
        return "Cat{" +
                  "id=" + id +
                  ", nickName='" + nickName + '\'' +
                  ", age=" + age +
                  ", catBreed='" + catBreed + '\'' +
                  ", description='" + description + '\'' +
                  '}';
    }
}