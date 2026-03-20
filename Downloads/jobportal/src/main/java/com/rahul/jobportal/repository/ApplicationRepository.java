package com.rahul.jobportal.repository;

import com.rahul.jobportal.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByUserIdAndJobId(Long userId, Long jobId);

    List<Application> findByJobIdOrderByMatchScoreDesc(Long jobId);

    Page<Application> findByJobId(Long jobId, Pageable pageable);

    List<Application> findByJobIdAndMatchScoreGreaterThanEqualOrderByMatchScoreDesc(
            Long jobId, Double score);
}