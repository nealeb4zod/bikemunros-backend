package com.example.bikemunrosbackend.bikemunrosbackend.repositories;

import com.example.bikemunrosbackend.bikemunrosbackend.models.Munro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunroRepository extends JpaRepository<Munro, Long> {

    List<Munro> findAllByName(String name);
}
