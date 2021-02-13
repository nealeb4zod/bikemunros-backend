package com.example.bikemunrosbackend.bikemunrosbackend.repositories;

import com.example.bikemunrosbackend.bikemunrosbackend.models.YouTubeLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YouTubeLinkRepository extends JpaRepository<YouTubeLink, Long> {
    List<YouTubeLink> findAllByUserName(String name);
}
