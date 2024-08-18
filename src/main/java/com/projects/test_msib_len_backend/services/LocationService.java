package com.projects.test_msib_len_backend.services;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projects.test_msib_len_backend.entities.Location;
import com.projects.test_msib_len_backend.entities.Project;
import com.projects.test_msib_len_backend.repos.LocationRepo;
import com.projects.test_msib_len_backend.repos.ProjectRepo;

@Service
@Transactional
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;
    
    @Autowired
    private ProjectRepo projectRepo;

    public Iterable<Location> findAll() {
        return locationRepo.findAll();
    }

    public Location findOne(UUID locaId) {
        return locationRepo.findById(locaId).get();
    }

    public Location create(Location location) {
        return locationRepo.save(location);
    }

    public void remove(UUID id) {
        Location location = locationRepo.findById(id).get();
        for (Project project : location.getProjects()) {
            project.getLocations().remove(location);
            projectRepo.save(project);
        }
        locationRepo.deleteById(id);
    }

    public Location update(Location location, UUID id) {
        Location selectedLocation = locationRepo.findById(id).get();

        if (Objects.nonNull(location.getLocationName()) && !"".equalsIgnoreCase(location.getLocationName())) {
            selectedLocation.setLocationName(location.getLocationName());
        }
        if (Objects.nonNull(location.getCountry()) && !"".equalsIgnoreCase(location.getCountry())) {
            selectedLocation.setCountry(location.getCountry());
        }
        if (Objects.nonNull(location.getCity()) && !"".equalsIgnoreCase(location.getCity())) {
            selectedLocation.setCity(location.getCity());
        }
        if (Objects.nonNull(location.getProvince()) && !"".equalsIgnoreCase(location.getProvince())) {
            selectedLocation.setProvince(location.getProvince());
        }

        return locationRepo.save(selectedLocation);
    }
}
