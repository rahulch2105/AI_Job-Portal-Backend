package com.rahul.jobportal.controller;

import com.rahul.jobportal.entity.Application;
import com.rahul.jobportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/upload")
    public String applyWithResume(
            @RequestParam Long userId,
            @RequestParam Long jobId,
            @RequestParam("file") MultipartFile file) {

        return applicationService.applyWithResume(userId, jobId, file);
    }
    @GetMapping("/job/{jobId}/paged")
    public Page<Application> getPaginated(
            @PathVariable Long jobId,
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size);

        return applicationService.getApplicationsPaginated(jobId, pageable);
    }
    @GetMapping("/job/{jobId}/filter")
    public List<Application> getFilteredCandidates(
            @PathVariable Long jobId,
            @RequestParam Double minScore) {

        return applicationService.getTopCandidatesWithFilter(jobId, minScore);
    }
    @GetMapping("/job/{jobId}")
    public List<Application> getTopCandidates(@PathVariable Long jobId) {
        return applicationService.getTopCandidates(jobId);
    }
}