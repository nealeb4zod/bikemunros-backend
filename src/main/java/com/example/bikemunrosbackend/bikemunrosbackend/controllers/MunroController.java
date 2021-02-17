package com.example.bikemunrosbackend.bikemunrosbackend.controllers;

import com.example.bikemunrosbackend.bikemunrosbackend.models.Munro;
import com.example.bikemunrosbackend.bikemunrosbackend.repositories.MunroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/munros")
public class MunroController {

    final
    MunroRepository munroRepository;

    public MunroController(MunroRepository munroRepository) {
        this.munroRepository = munroRepository;
    }

    @GetMapping(value = "/page")
    Page<Munro> loadMunrosPage(
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "name", direction = Sort.Direction.ASC),
            })
                    Pageable pageable) {
        return munroRepository.findAll(pageable);
    }

    @GetMapping(value= "")
    public ResponseEntity<List<Munro>>getAllMunrosAndFilters(
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "county") String county
    ) {
        if (name != null){
            return new ResponseEntity<>(munroRepository.findAllByName(name), HttpStatus.OK);
        }
        if (county != null){
            return new ResponseEntity<>(munroRepository.findAllByCounty(county), HttpStatus.OK);
        }
        return new ResponseEntity<>(munroRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getMunro(@PathVariable Long id) {
        return new ResponseEntity<>(munroRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Munro> createMunro(@RequestBody Munro munro) {
        munroRepository.save(munro);
        return new ResponseEntity<> (munro, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Munro> updateMunro(@RequestBody Munro munro) {
        munroRepository.save(munro);
        return new ResponseEntity<>(munro, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteMunro(@PathVariable Long id) {
        Munro deletedMunro = munroRepository.getOne(id);
        munroRepository.deleteById(id);
        return new ResponseEntity<>(deletedMunro, HttpStatus.OK);
    }
}

