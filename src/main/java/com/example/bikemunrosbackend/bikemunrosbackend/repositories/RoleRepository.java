package com.example.bikemunrosbackend.bikemunrosbackend.repositories;

import com.example.bikemunrosbackend.bikemunrosbackend.models.ERole;
import com.example.bikemunrosbackend.bikemunrosbackend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
