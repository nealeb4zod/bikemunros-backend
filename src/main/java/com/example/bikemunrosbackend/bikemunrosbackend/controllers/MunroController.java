package com.example.bikemunrosbackend.bikemunrosbackend.controllers;

import com.example.bikemunrosbackend.bikemunrosbackend.models.Munro;
import com.example.bikemunrosbackend.bikemunrosbackend.repositories.MunroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MunroController {

    @Autowired
    MunroRepository munroRepository;

    @GetMapping(value= "/munros")
    public ResponseEntity<List<Munro>>getAllMunrosAndFilters(
            @RequestParam(required = false, name = "name") String name
    ) {
        if (name != null){
            return new ResponseEntity<>(munroRepository.findAllByName(name), HttpStatus.OK);
        }
        return new ResponseEntity<>(munroRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/munros/{id}")
    public ResponseEntity getMunro(@PathVariable Long id) {
        return new ResponseEntity<>(munroRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/munros")
    public ResponseEntity<Munro> createMunro(@RequestBody Munro munro) {
        munroRepository.save(munro);
        return new ResponseEntity<> (munro, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/munros/{id}")
    public ResponseEntity<Munro> updateMunro(@RequestBody Munro munro) {
        munroRepository.save(munro);
        return new ResponseEntity<>(munro, HttpStatus.OK);
    }

    @DeleteMapping(value = "/munros/delete/{id}")
    public ResponseEntity deleteMunro(@PathVariable Long id) {
        Munro deletedMunro = munroRepository.getOne(id);
        munroRepository.deleteById(id);
        return new ResponseEntity<>(deletedMunro, HttpStatus.OK);
    }
}

