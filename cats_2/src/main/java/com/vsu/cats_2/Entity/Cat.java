package com.vsu.cats_2.Entity;

public class Cat {
    private long id;
    private String catName;
    private float age;
    private long CatPersonId;

    public Cat() {
    }

    public Cat(String catName, float age) {
        this.catName = catName;
        this.age = age;
    }

    public Cat(long id, String catName, float age, long CatPersonId) {
        this.id = id;
        this.catName = catName;
        this.age = age;
        this.CatPersonId = CatPersonId;
    }

    public Cat(long id, String catName, float age) {
        this.id = id;
        this.catName = catName;
        this.age = age;
    }

    public Cat(String catName, float age, long CatPersonId) {
        this.catName = catName;
        this.age = age;
        this.CatPersonId = CatPersonId;
    }

    public long getCatPersonId() {
        return CatPersonId;
    }

    public void setCatPersonId(long idCatPerson) {
        this.CatPersonId = CatPersonId;
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

}