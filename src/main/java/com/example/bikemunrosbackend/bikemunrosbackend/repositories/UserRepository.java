package com.example.bikemunrosbackend.bikemunrosbackend.repositories;

import com.example.bikemunrosbackend.bikemunrosbackend.models.Munro;
import com.example.bikemunrosbackend.bikemunrosbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByName(String name);
}
