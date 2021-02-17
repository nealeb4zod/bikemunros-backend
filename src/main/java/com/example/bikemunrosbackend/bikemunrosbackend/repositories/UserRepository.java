package com.example.bikemunrosbackend.bikemunrosbackend.repositories;

import com.example.bikemunrosbackend.bikemunrosbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByName(String name);
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findByEmail(String username);
}
