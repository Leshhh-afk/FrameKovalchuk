package com.vsu.cats_2.service;


import com.vsu.cats_2.Entity.Cat;
import com.vsu.cats_2.Entity.CatPerson;
import com.vsu.cats_2.dto.CatDTO;
import com.vsu.cats_2.dto.CatPersonDTO;
import com.vsu.cats_2.exception.NotFoundException;
import com.vsu.cats_2.exception.RepositoryException;
import com.vsu.cats_2.exception.ValidationException;
import com.vsu.cats_2.mappers.CatPersonMapper;
import com.vsu.cats_2.mappers.CatMapper;
import com.vsu.cats_2.repository.CatPersonRepository;
import com.vsu.cats_2.repository.CatRepository;
import com.vsu.cats_2.request.CreateCatRequest;
import com.vsu.cats_2.request.CreateCatPersonRequest;
import com.vsu.cats_2.request.UpdateCatRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class CatPersonService {
    private final static Logger log = Logger.getLogger(CatPersonService.class.getName());
    private final CatPersonRepository catPersonRepository;
    private final CatRepository catRepository;
    private final CatPersonMapper catPersonMapper;
    private final CatMapper catMapper;


    public CatPersonService(CatPersonRepository catPersonRepository, CatRepository catRepository, CatPersonMapper catPersonMapper, CatMapper catMapper) {
        this.catPersonRepository = catPersonRepository;
        this.catRepository = catRepository;
        this.catPersonMapper = catPersonMapper;
        this.catMapper = catMapper;
    }


    public CatPersonDTO findByLogin(String login) {
        CatPerson catPerson = catPersonRepository.findByLogin(login).orElseThrow(() -> new NotFoundException("User not found"));
        return catPersonMapper.toCatPersonDTO(catPerson);
    }

    public CatPersonDTO createCatPerson(@Valid CreateCatPersonRequest createCatPersonRequest) {
        if (catPersonRepository.findByLogin(createCatPersonRequest.getCatPersonName()).isPresent()) {
            throw new ValidationException("User already exists");
        }
        if (catPersonRepository.create(createCatPersonRequest) == 1) {
            return catPersonMapper.toCatPersonDTO(catPersonRepository.findByLogin(createCatPersonRequest.getCatPersonName()).get());
        }
        throw new RepositoryException("Error creating profile");
    }

    public CatDTO createCat(@Valid CreateCatRequest createCatRequest, long catPersonId) {
        Optional<CatPerson> catPerson = catPersonRepository.findByID(catPersonId);
        if (catPerson.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        try {
            CatPerson f = catPerson.get();
            Cat aCat = new Cat(
                    createCatRequest.getCatName(),
                    createCatRequest.getAge(),
                    catPersonId);
            Long id = catRepository.create(aCat);
            if (id != 0) {
                aCat.setId(id);
                return catMapper.toCatDTO(aCat);
            }
            throw new ValidationException("Wrong data");
        } catch (DataAccessException e) {
            throw new RepositoryException("Error data access");
        }


    }

    public CatDTO updateCat(@Valid UpdateCatRequest updateCatRequest, long catPersonId) {
        Optional<CatPerson> catPerson = catPersonRepository.findByID(catPersonId);
        if (catPerson.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        try {
            CatPerson f = catPerson.get();
            Cat aCat = new Cat(
                    updateCatRequest.getId(),
                    updateCatRequest.getCatName(),
                    updateCatRequest.getAge()
            );
            int result = catRepository.update(aCat);
            if (result ==1) {
                return catMapper.toCatDTO(aCat);
            }
            throw new ValidationException("Wrong data");
        } catch (DataAccessException e) {
            throw new RepositoryException("Error data access");
        }


    }

    public CatDTO findCatById(long id, long catPersonId) {
        Optional<CatPerson> catPerson = catPersonRepository.findByID(catPersonId);
        if (catPerson.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        try {
            Cat aCat = catRepository.findById(id).orElseThrow(() -> new NotFoundException("Cat not found"));
            return catMapper.toCatDTO(aCat);
        } catch (DataAccessException e) {
            throw new RepositoryException("Error data access");
        }


    }

    public List<CatDTO> findCatByCatName(long profileId, String catName) {
        Optional<CatPerson> catPerson = catPersonRepository.findByID(profileId);
        if (catPerson.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        try {
            List<Cat> cats = catRepository.findByCatName(catPerson.get().getId(), catName).orElseThrow(() -> new NotFoundException("Records not found"));
            List<CatDTO> result = new ArrayList<>();
            for (Cat aCat : cats) {
                result.add(catMapper.toCatDTO(aCat));
            }
            return result;

        } catch (DataAccessException e) {
            throw new RepositoryException("Error data access");
        }

    }

    public void deleteCatById(long id, long profileId) {
        Optional<CatPerson> catPerson = catPersonRepository.findByID(profileId);
        if (catPerson.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        if (catRepository.deleteById(id) != 1) {
            throw new NotFoundException("Cat not found");
        }
    }
}
