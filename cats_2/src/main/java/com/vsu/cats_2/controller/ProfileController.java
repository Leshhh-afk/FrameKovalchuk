package com.vsu.cats_2.controller;

import com.vsu.cats_2.dto.CatDTO;
import com.vsu.cats_2.dto.CatPersonDTO;
import com.vsu.cats_2.request.CreateCatRequest;
import com.vsu.cats_2.request.CreateCatPersonRequest;
import com.vsu.cats_2.request.UpdateCatRequest;
import com.vsu.cats_2.service.CatPersonService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final CatPersonService catPersonService;

    public ProfileController(CatPersonService catPersonService) {
        this.catPersonService = catPersonService;
    }

    @GetMapping("/by-catPersonName")
    public CatPersonDTO getById(@RequestParam String name) {
        return catPersonService.findByLogin(name);
    }

    @PostMapping
    public CatPersonDTO create(@Valid @RequestBody CreateCatPersonRequest createProfileRequest) {
        return catPersonService.createCatPerson(createProfileRequest);
    }
    @PostMapping("/{CatPersonId}/cat")
    public CatDTO create(@PathVariable long CatPersonId, @Valid @RequestBody CreateCatRequest createRecordRequest) {
        return catPersonService.createCat(createRecordRequest, CatPersonId);

    }
    @PutMapping("/{CatPersonId}/cat/edit")
    public CatDTO update(@PathVariable long CatPersonId, @Valid @RequestBody UpdateCatRequest updateRecordRequest) {
        return catPersonService.updateCat(updateRecordRequest,CatPersonId);
    }
    @GetMapping("/{CatPersonId}/cat/{id}")
    public CatDTO getCat(@PathVariable long CatPersonId, @PathVariable Long id) {
        return catPersonService.findCatById(id, CatPersonId);
    }
    @GetMapping("/{CatPersonId}/cat/catName/{catName}")
    public List<CatDTO> getCatByCatName(@PathVariable long CatPersonId, @PathVariable String catName) {
        return catPersonService.findCatByCatName(CatPersonId, catName);
    }
    @DeleteMapping("/{CatPersonId}/cat/delete/{id}")
    public void deleteCat(@PathVariable long CatPersonId, @PathVariable Long id) {
        catPersonService.deleteCatById(id, CatPersonId);
    }
}
