package com.modive.llm.service;

import com.modive.llm.dto.FeedbackRequest;
import com.modive.llm.dto.FeedbackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LLMService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public String getFeedbacks(String message) {

        FeedbackRequest requestBody = FeedbackRequest.builder()
                .contents(List.of(
                        FeedbackRequest.Content.builder()
                                .parts(List.of(
                                        FeedbackRequest.Part.builder()
                                                .text(message)
                                                .build()
                                ))
                                .build()
                ))
                .build();

        FeedbackResponse response = webClient.post()
                .uri(apiUrl + "?key=" + geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(FeedbackResponse.class)
                .block();

        String feedback = response.getCandidates()
                .get(0)
                .getContent()
                .getParts()
                .get(0)
                .getText();

        return feedback;
    }
}
