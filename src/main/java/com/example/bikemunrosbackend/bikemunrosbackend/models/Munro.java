package com.example.bikemunrosbackend.bikemunrosbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "munros")
public class Munro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String county;

    @Column
    private String gridRef;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @Column
    private int heightFeet;

    @Column
    private double heightMetres;

    @Column
    private String description;

    @JsonIgnoreProperties({"bikedMunros", "youTubeLinksAdded", "savedMunros"})
    @ManyToMany
    @JoinTable(
            name = "users_biked",
            joinColumns = {
                    @JoinColumn(
                            name = "user_id",
                            nullable = false,
                            updatable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "munro_id",
                            nullable = false,
                            updatable = false
                    )
            }
    )
    private List<User> usersHaveBiked;

    @JsonIgnoreProperties("savedMunros")
    @ManyToMany
    @JoinTable(
            name = "users_saved",
            joinColumns = {
                    @JoinColumn(
                            name = "user_id",
                            nullable = false,
                            updatable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "munro_id",
                            nullable = false,
                            updatable = false
                    )
            }
    )
    private List<User> usersHaveSaved;

    @JsonManagedReference
    @OneToMany(mappedBy = "munro", fetch = FetchType.LAZY)
    private List<YouTubeLink> youTubeLinks;


    public Munro(String name, String county, String gridRef, double longitude, double latitude, int heightFeet, double heightMetres) {
        this.name = name;
        this.county = county;
        this.gridRef = gridRef;
        this.longitude = longitude;
        this.latitude = latitude;
        this.heightFeet = heightFeet;
        this.heightMetres = heightMetres;
        this.usersHaveBiked = new ArrayList<>();
        this.usersHaveSaved = new ArrayList<>();
        this.youTubeLinks = new ArrayList<>();
        this.description = "";
    }

    public Munro() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getGridRef() {
        return gridRef;
    }

    public void setGridRef(String gridRef) {
        this.gridRef = gridRef;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(int heightFeet) {
        this.heightFeet = heightFeet;
    }

    public double getHeightMetres() {
        return heightMetres;
    }

    public void setHeightMetres(double heightMetres) {
        this.heightMetres = heightMetres;
    }

    public List<User> getUsersHaveSaved() {
        return usersHaveSaved;
    }

    public void setUsersHaveSaved(List<User> usersHaveSaved) {
        this.usersHaveSaved = usersHaveSaved;
    }

    public boolean addSavedUser(User user) {
        return getUsersHaveSaved().add(user);
    }

    public List<User> getUsersHaveBiked() {
        return usersHaveBiked;
    }

    public void setUsersHaveBiked(List<User> usersHaveBiked) {
        this.usersHaveBiked = usersHaveBiked;
    }

    public List<YouTubeLink> getYouTubeLinks() {
        return youTubeLinks;
    }

    public void setYouTubeLinks(List<YouTubeLink> youTubeLinks) {
        this.youTubeLinks = youTubeLinks;
    }

    public boolean addBikedUser(User user) {
        return usersHaveBiked.add(user);
    }

    public boolean addYouTubeLink(YouTubeLink youTubeLink) {
        return youTubeLinks.add(youTubeLink);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
