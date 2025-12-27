package com.vsu.cats_2.mappers;

import com.vsu.cats_2.Entity.CatPerson;
import com.vsu.cats_2.dto.CatPersonDTO;
import org.springframework.stereotype.Component;

@Component
public class CatPersonMapper {
    public CatPersonDTO toCatPersonDTO(CatPerson catPerson) {
        return new CatPersonDTO(catPerson.getId(), catPerson.getLogin());
    }
}
