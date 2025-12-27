package com.vsu.cats_2.dto;

import org.hibernate.validator.constraints.Length;

public class CatPersonDTO {
    private Long id;

    @Length(min = 6, message = "Login must be at least 6 symbols")
    private String CatPersonName;

    public CatPersonDTO(Long id, String CatPersonName) {
        this.id = id;
        this.CatPersonName = CatPersonName;
    }

    public CatPersonDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatPersonName() {
        return CatPersonName;
    }

    public void setCatPersonName(String CatPersonName) {
        this.CatPersonName = CatPersonName;
    }

    @Override
    public String toString() {
        return "CatPersonDTO{" +
                "id=" + id +
                ", CatPersonName='" + CatPersonName + '\'' +
                '}';
    }
}
