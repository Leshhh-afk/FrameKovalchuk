package com.vsu.cats_2.request;

public class UpdateCatRequest {
    private long id;
    private String catName;
    private float age;

    public UpdateCatRequest() {
    }

    public UpdateCatRequest(long id, String catName, float age) {
        this.id = id;
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
}
