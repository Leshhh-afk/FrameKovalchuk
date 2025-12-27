package com.vsu.cats_2.request;

import org.hibernate.validator.constraints.Length;

public class CreateCatPersonRequest {
    @Length(min = 6, message = "login must be not less than 6 symbols")
    private String catPersonName;
    private String password;

    public CreateCatPersonRequest() {
    }

    public CreateCatPersonRequest(String catPersonName) {
        this.catPersonName = catPersonName;
    }

    public CreateCatPersonRequest(String catPersonName, String password) {
        this.catPersonName = catPersonName;
        this.password = password;
    }

    public String getCatPersonName() {
        return catPersonName;
    }

    public void setCatPersonName(String catPersonName) {
        this.catPersonName = catPersonName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                ", name='" + catPersonName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
