package com.example.bikemunrosbackend.bikemunrosbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column
    @Size(max = 120)
    private String name;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

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

    public User(String name, String email, String password, String username) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
