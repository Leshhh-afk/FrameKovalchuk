package com.vsu.cats_2.mappers;

import com.vsu.cats_2.Entity.Cat;
import com.vsu.cats_2.dto.CatDTO;
import org.springframework.stereotype.Component;

@Component
public class CatMapper {
    public CatDTO toCatDTO(Cat acat) {
        return new CatDTO(acat.getId(),acat.getCatName(), acat.getAge());
    }
}
