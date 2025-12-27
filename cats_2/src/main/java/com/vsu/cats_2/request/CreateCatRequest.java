package com.vsu.cats_2.request;

public class CreateCatRequest {
    private String catName;
    private float age;

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
