package com.projects.test_msib_len_backend.services;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.test_msib_len_backend.entities.Location;
import com.projects.test_msib_len_backend.entities.Project;
import com.projects.test_msib_len_backend.repos.LocationRepo;
import com.projects.test_msib_len_backend.repos.ProjectRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProjectService {
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private LocationRepo locationRepo;

    public Iterable<Project> findAll() {
        return projectRepo.findAll();
    }

    public Project findOne(UUID id) {
        return projectRepo.findById(id).get();
    }

    public Project create(Project project) {
        Set<UUID> locationIds = project.getLocations().stream()
                .map(Location::getId)
                .collect(Collectors.toSet());

        Set<Location> locations = new HashSet<>(locationRepo.findAllById(locationIds));
        project.setLocations(locations);
        return projectRepo.save(project);
    }

    public void remove(UUID id) {
        projectRepo.deleteById(id);
        
    }

    public Project update(Project project, UUID id) {
        Project selectedProject = projectRepo.findById(id).get();

        if (Objects.nonNull(project.getProjectName()) && !"".equals(project.getProjectName())) {
            selectedProject.setProjectName(project.getProjectName());
        }
        if (Objects.nonNull(project.getProjectLeader()) && !"".equals(project.getProjectLeader())) {
            selectedProject.setProjectLeader(project.getProjectLeader());
        }
        if (Objects.nonNull(project.getClientName()) && !"".equals(project.getClientName())) {
            selectedProject.setClientName(project.getClientName());
        }
        if (Objects.nonNull(project.getProjectDetail()) && !"".equals(project.getProjectDetail())) {
            selectedProject.setProjectDetail(project.getProjectDetail());
        }
        if (Objects.nonNull(project.getStartDate())) {
            selectedProject.setStartDate(project.getStartDate());
        }
        if (Objects.nonNull(project.getEndDate())) {
            selectedProject.setEndDate(project.getEndDate());
        }
        if (Objects.nonNull(project.getLocations())) {
            Set<UUID> locationIds = project.getLocations().stream()
                    .map(Location::getId)
                    .collect(Collectors.toSet());
            Set<Location> locations = new HashSet<>(locationRepo.findAllById(locationIds));
            selectedProject.setLocations(locations);
        }

        return projectRepo.save(selectedProject);
    }
}
