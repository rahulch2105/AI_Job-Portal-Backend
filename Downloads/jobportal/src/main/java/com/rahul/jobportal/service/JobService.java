package com.rahul.jobportal.service;

import com.rahul.jobportal.entity.Job;
import com.rahul.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job createJob(Job job) {
        job.setCreatedAt(LocalDateTime.now());
        job.setStatus("OPEN");
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}