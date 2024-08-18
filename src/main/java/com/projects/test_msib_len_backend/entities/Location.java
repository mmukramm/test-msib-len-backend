package com.projects.test_msib_len_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.*;
import java.time.*;


import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty(message = "locationName is required")
    @Column(name = "location_name")
    private String locationName;
    
    @NotEmpty(message = "country is required")
    @Column(name = "country")
    private String country;
    
    @Column(name = "province")
    private String province;
    
    @Column(name = "city")
    private String city;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Location() {
    }

    public Location(UUID id, String locationName, String country, String province, String city,
            LocalDateTime createdAt, Set<Project> projects) {
        this.id = id;
        this.locationName = locationName;
        this.country = country;
        this.province = province;
        this.city = city;
        this.createdAt = createdAt;
        this.projects = projects;
    }

    @ManyToMany(mappedBy = "locations")
    @JsonIgnore
    private Set<Project> projects = new HashSet<>();

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    } 

    
}
