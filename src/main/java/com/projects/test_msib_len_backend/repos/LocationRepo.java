package com.projects.test_msib_len_backend.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

import com.projects.test_msib_len_backend.entities.Location;

public interface LocationRepo extends JpaRepository<Location, UUID> {
}
