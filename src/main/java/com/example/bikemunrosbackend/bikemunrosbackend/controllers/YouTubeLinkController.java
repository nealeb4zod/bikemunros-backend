package com.example.bikemunrosbackend.bikemunrosbackend.controllers;

import com.example.bikemunrosbackend.bikemunrosbackend.models.Munro;
import com.example.bikemunrosbackend.bikemunrosbackend.models.YouTubeLink;
import com.example.bikemunrosbackend.bikemunrosbackend.repositories.YouTubeLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class YouTubeLinkController {

    @Autowired
    YouTubeLinkRepository youTubeLinkRepository;

    @GetMapping(value= "/youtubelinks")
    public ResponseEntity<List<YouTubeLink>> getAllYouTubeLinksAndFilters(
            @RequestParam(required = false, name = "name") String name
    ) {
        if (name != null){
            return new ResponseEntity<>(youTubeLinkRepository.findAllByUserName(name), HttpStatus.OK);
        }
        return new ResponseEntity<>(youTubeLinkRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/youtubelinks/{id}")
    public ResponseEntity getYouTubeLink(@PathVariable Long id) {
        return new ResponseEntity<>(youTubeLinkRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/youtubelinks")
    public ResponseEntity<YouTubeLink> createYouTubeLink(@RequestBody YouTubeLink youtubelink) {
        youTubeLinkRepository.save(youtubelink);
        return new ResponseEntity<> (youtubelink, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/youtubelinks/{id}")
    public ResponseEntity<YouTubeLink> updateYouTubeLink(@RequestBody YouTubeLink youtubelink) {
        youTubeLinkRepository.save(youtubelink);
        return new ResponseEntity<>(youtubelink, HttpStatus.OK);
    }

    @DeleteMapping(value = "/youtubelinks/delete/{id}")
    public ResponseEntity deleteYouTubeLink(@PathVariable Long id) {
        YouTubeLink deletedYouTubeLink = youTubeLinkRepository.getOne(id);
        youTubeLinkRepository.deleteById(id);
        return new ResponseEntity<>(deletedYouTubeLink, HttpStatus.OK);
    }
}
