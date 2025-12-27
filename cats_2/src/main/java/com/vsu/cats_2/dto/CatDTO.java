package com.vsu.cats_2.dto;

public class CatDTO {
    private long id;
    private String catName;
    private float age;

    public CatDTO() {
    }

    public CatDTO(long id, String catName, float age) {
        this.id = id;
        this.catName = catName;
        this.age = age;
    }

    public CatDTO(String catName, float age) {
        this.catName = catName;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "CatsDTO{" +
                "id=" + id +
                ", catName='" + catName + '\'' +
                ", age=" + age +
                '}';
    }
}
