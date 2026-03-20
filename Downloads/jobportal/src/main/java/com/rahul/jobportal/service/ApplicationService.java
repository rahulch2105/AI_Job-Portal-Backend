package com.rahul.jobportal.service;

import com.rahul.jobportal.entity.Application;
import com.rahul.jobportal.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private AIService aiService;

    // ✅ APPLY WITH RESUME
    public String applyWithResume(Long userId, Long jobId, MultipartFile file) {

        try {
            // 🔴 Duplicate check
            if (applicationRepository.findByUserIdAndJobId(userId, jobId).isPresent()) {
                return "Already applied";
            }

            // 🔴 Create upload folder
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File dir = new File(uploadDir);

            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 🔴 Save file
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String path = uploadDir + fileName;

            file.transferTo(new File(path));

            // 🔥 Extract text
            String resumeText = extractTextFromPDF(path);

            // 🔥 AI skill extraction
            String resumeSkills = aiService.extractSkills(resumeText);
            System.out.println("Extracted Skills: " + resumeSkills);

            // 🔥 Job skills (temporary)
            String jobSkills = "Java, Spring Boot, SQL";

            // 🔥 Score calculation
            double score = calculateMatchScore(resumeSkills, jobSkills);
            System.out.println("Match Score: " + score);

            // 🔥 Save application
            Application app = new Application(userId, jobId, path);
            app.setMatchScore(score);
            app.setStatus("APPLIED");
            app.setCreatedAt(LocalDateTime.now());

            applicationRepository.save(app);

            return "Applied successfully with score: " + score;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    // ✅ PDF TEXT EXTRACTION
    private String extractTextFromPDF(String filePath) {
        try {
            org.apache.pdfbox.pdmodel.PDDocument document =
                    org.apache.pdfbox.pdmodel.PDDocument.load(new File(filePath));

            org.apache.pdfbox.text.PDFTextStripper stripper =
                    new org.apache.pdfbox.text.PDFTextStripper();

            String text = stripper.getText(document);

            document.close();

            return text;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // ✅ MATCH SCORE LOGIC
    private double calculateMatchScore(String resumeSkills, String jobSkills) {

        String[] resumeArray = resumeSkills.toLowerCase().split(",");
        String[] jobArray = jobSkills.toLowerCase().split(",");

        int matchCount = 0;

        for (String jobSkill : jobArray) {
            for (String resumeSkill : resumeArray) {
                if (jobSkill.trim().equals(resumeSkill.trim())) {
                    matchCount++;
                }
            }
        }

        return ((double) matchCount / jobArray.length) * 100;
    }

    // ✅ TOP CANDIDATES (NO HARD FILTER — IMPORTANT)
    public List<Application> getTopCandidates(Long jobId) {
        return applicationRepository.findByJobIdOrderByMatchScoreDesc(jobId);
    }

    // ✅ PAGINATION (FIXED — NO MORE NULL)
    public Page<Application> getApplicationsPaginated(Long jobId, Pageable pageable) {
        return applicationRepository.findByJobId(jobId, pageable);
    }

    // ✅ FILTER BY SCORE (ADVANCED FEATURE)
    public List<Application> getTopCandidatesWithFilter(Long jobId, Double minScore) {
        return applicationRepository
                .findByJobIdAndMatchScoreGreaterThanEqualOrderByMatchScoreDesc(jobId, minScore);
    }
}