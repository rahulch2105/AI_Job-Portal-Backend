package com.rahul.jobportal.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Map;

@Service
public class AIService {

    private final String API_KEY = "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";

    public String extractSkills(String resumeText) {

        try {
            String url = "https://api.openai.com/v1/chat/completions";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + API_KEY);
            headers.setContentType(MediaType.APPLICATION_JSON);

            String prompt = "Extract technical skills as comma-separated list:\n" + resumeText;

            Map<String, Object> body = Map.of(
                    "model", "gpt-4o-mini",
                    "messages", new Object[]{
                            Map.of("role", "user", "content", prompt)
                    }
            );

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

            // 🔥 SAFE PARSING
            if (response.getBody() == null) {
                return "Java, Spring"; // fallback
            }

            Object choicesObj = response.getBody().get("choices");

            if (choicesObj == null) {
                return "Java, Spring";
            }

            java.util.List choices = (java.util.List) choicesObj;

            if (choices.isEmpty()) {
                return "Java, Spring";
            }

            Map choice = (Map) choices.get(0);
            Map message = (Map) choice.get("message");
            System.out.println("AI response: " + response.getBody());
            return message.get("content").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Java, Spring"; // fallback
        }
    }

}