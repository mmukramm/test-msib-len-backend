package com.projects.test_msib_len_backend.repos;

import java.util.UUID;

import com.projects.test_msib_len_backend.entities.Project;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepo extends JpaRepository<Project, UUID>{
}
