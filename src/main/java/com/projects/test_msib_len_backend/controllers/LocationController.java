package com.projects.test_msib_len_backend.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.test_msib_len_backend.dto.DataResponse;
import com.projects.test_msib_len_backend.entities.Location;
import com.projects.test_msib_len_backend.services.LocationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping()
    public ResponseEntity<DataResponse<Iterable<Location>>> findAll() {
        DataResponse<Iterable<Location>> dataResponse = new DataResponse<>();

        dataResponse.setStatus(true);
        dataResponse.getMessage().add("SUCCESS");
        dataResponse.setData(locationService.findAll());

        return ResponseEntity.ok().body(dataResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Location>> findOne(@PathVariable("id") String locationId) {
        DataResponse<Location> dataResponse = new DataResponse<>();

        dataResponse.setStatus(true);
        dataResponse.getMessage().add("SUCCESS");
        dataResponse.setData(locationService.findOne(UUID.fromString(locationId)));

        return ResponseEntity.ok().body(dataResponse);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Location>> create(@Valid @RequestBody Location location, Errors errors) {

        DataResponse<Location> dataResponse = new DataResponse<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                dataResponse.getMessage().add(error.getDefaultMessage());
            }
            dataResponse.setStatus(false);
            dataResponse.setData(null);
            return ResponseEntity.badRequest().body(dataResponse);
        }
        dataResponse.setStatus(true);
        dataResponse.getMessage().add("SUCCESS");
        dataResponse.setData(locationService.create(location));

        return ResponseEntity.ok(dataResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Location>> update(@Valid @RequestBody Location location, Errors errors,
            @PathVariable("id") String locationId) {
        DataResponse<Location> dataResponse = new DataResponse<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                dataResponse.getMessage().add(error.getDefaultMessage());
            }
            dataResponse.setStatus(false);
            dataResponse.setData(null);
            return ResponseEntity.badRequest().body(dataResponse);
        }

        dataResponse.setStatus(true);
        dataResponse.getMessage().add("SUCCESS");
        dataResponse.setData(locationService.update(location, UUID.fromString(locationId)));

        return ResponseEntity.ok(dataResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> delete(@PathVariable("id") UUID locationId) {
        locationService.remove(locationId);
        DataResponse<String> dataResponse = new DataResponse<>();
        dataResponse.setStatus(true);
        dataResponse.getMessage().add("SUCCESS");
        dataResponse.setData(null);
        return ResponseEntity.ok().body(dataResponse);
    }

}
