package com.projects.test_msib_len_backend.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.projects.test_msib_len_backend.dto.DataResponse;
import com.projects.test_msib_len_backend.entities.Project;
import com.projects.test_msib_len_backend.services.ProjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<DataResponse<Iterable<Project>>> findAll() {
        DataResponse<Iterable<Project>> dataResponse = new DataResponse<>();
        dataResponse.setStatus(true);
        dataResponse.getMessage().add("SUCCESS");
        dataResponse.setData(projectService.findAll());
        ;
        return ResponseEntity.ok().body(dataResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Project>> findOne(@PathVariable("id") String projectId) {
        DataResponse<Project> dataResponse = new DataResponse<>();
        dataResponse.setStatus(true);
        dataResponse.getMessage().add("SUCCESS");
        dataResponse.setData(projectService.findOne(UUID.fromString(projectId)));

        return ResponseEntity.ok().body(dataResponse);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Project>> create(@Valid @RequestBody Project project, Errors errors) {
        DataResponse<Project> dataResponse = new DataResponse<>();
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
        dataResponse.setData(projectService.create(project));
        return ResponseEntity.ok().body(dataResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> delete(@PathVariable("id") UUID projectId) {
        projectService.remove(projectId);

        DataResponse<String> dataResponse = new DataResponse<>();
        dataResponse.setStatus(true);
        dataResponse.getMessage().add("SUCCESS");
        dataResponse.setData(null);
        return ResponseEntity.ok().body(dataResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Project>> update(@Valid @RequestBody Project project, Errors errors,
            @PathVariable("id") String projectId) {
        DataResponse<Project> dataResponse = new DataResponse<>();
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
        dataResponse.setData(projectService.update(project, UUID.fromString(projectId)));
        return ResponseEntity.ok().body(dataResponse);
    }

}
