package com.example.bikemunrosbackend.bikemunrosbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @JsonIgnoreProperties("usersHaveBiked")
    @ManyToMany
    @JoinTable(
            name = "users_biked",
            joinColumns = {
                    @JoinColumn(
                            name = "munro_id",
                            nullable = false,
                            updatable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "user_id",
                            nullable = false,
                            updatable = false
                    )
            }
    )
    private List<Munro> bikedMunros;

    @JsonIgnoreProperties("usersHaveSaved")
    @ManyToMany
    @JoinTable(
            name = "users_saved",
            joinColumns = {
                    @JoinColumn(
                            name = "munro_id",
                            nullable = false,
                            updatable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "user_id",
                            nullable = false,
                            updatable = false
                    )
            }
    )
    private List<Munro> savedMunros;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<YouTubeLink> youTubeLinksAdded;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.bikedMunros = new ArrayList<>();
        this.savedMunros = new ArrayList<>();
        this.youTubeLinksAdded = new ArrayList<>();
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Munro> getBikedMunros() {
        return bikedMunros;
    }

    public void setBikedMunros(List<Munro> bikedMunros) {
        this.bikedMunros = bikedMunros;
    }

    public List<Munro> getSavedMunros() {
        return savedMunros;
    }

    public void setSavedMunros(List<Munro> savedMunros) {
        this.savedMunros = savedMunros;
    }

    public boolean addBikedMunro(Munro munro) {
        return bikedMunros.add(munro);
    }

    public boolean addSavedMunro(Munro munro) {
        return savedMunros.add(munro);
    }

    public boolean addYouTubeLink(YouTubeLink youTubeLink) {
        return youTubeLinksAdded.add(youTubeLink);
    }

    public List<YouTubeLink> getYouTubeLinksAdded() {
        return youTubeLinksAdded;
    }

    public void setYouTubeLinksAdded(List<YouTubeLink> youTubeLinksAdded) {
        this.youTubeLinksAdded = youTubeLinksAdded;
    }
}
