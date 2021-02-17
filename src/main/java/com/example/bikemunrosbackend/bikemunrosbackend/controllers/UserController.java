package com.example.bikemunrosbackend.bikemunrosbackend.controllers;

import com.example.bikemunrosbackend.bikemunrosbackend.models.User;
import com.example.bikemunrosbackend.bikemunrosbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value= "")
    public ResponseEntity<List<User>> getAllUsersAndFilters(
            @RequestParam(required = false, name = "name") String name
    ) {
        if (name != null){
            return new ResponseEntity<>(userRepository.findAllByName(name), HttpStatus.OK);
        }
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<> (user, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    @PreAuthorize("authentication.principal.id == #id || hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User user
    ) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        User deletedUser = userRepository.getOne(id);
        userRepository.deleteById(id);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }
}
