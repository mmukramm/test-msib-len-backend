package com.projects.test_msib_len_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.*;
import java.time.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty(message = "project_name is required")
    @Column(name = "project_name")
    private String projectName;
    
    @NotEmpty(message = "client_name is required")
    @Column(name = "client_name")
    private String clientName;
    
    @NotEmpty(message = "project_leader is required")
    @Column(name = "project_leader")
    private String projectLeader;
    
    @NotEmpty(message = "project_detail is required")
    @Column(name = "project_detail", columnDefinition = "TEXT")
    private String projectDetail;
    
    @NotNull(message = "start_date is required")
    @Column(name = "start_date")
    private LocalDateTime startDate;
    
    @NotNull(message = "end_date is required")
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Project() {
    }

    public Project(UUID id, String projectName, String clientName, String projectLeader, String projectDetail,
            LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt, Set<Location> locations) {
        this.id = id;
        this.projectName = projectName;
        this.clientName = clientName;
        this.projectLeader = projectLeader;
        this.projectDetail = projectDetail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.locations = locations;
    }

    @ManyToMany
    @JoinTable(name = "project_location", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "location_id"))
    private Set<Location> locations = new HashSet<>();

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    public String getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(String projectDetail) {
        this.projectDetail = projectDetail;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
