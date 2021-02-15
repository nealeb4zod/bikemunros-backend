package com.example.bikemunrosbackend.bikemunrosbackend.repositories;

import com.example.bikemunrosbackend.bikemunrosbackend.models.Munro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunroRepository extends JpaRepository<Munro, Long> {

    List<Munro> findAllByName(String name);
    List<Munro> findAllByCounty(String county);


    Page<Munro> findAll(Pageable pageable);
}
