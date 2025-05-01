package com.modive.llm.service;

import com.modive.llm.dto.request.FeedbackRequest;
import com.modive.llm.dto.response.FeedbackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeminiClientService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String apiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    public String callGemini(String prompt) {
        FeedbackRequest body = FeedbackRequest.builder()
                .contents(List.of(
                        FeedbackRequest.Content.builder()
                                .parts(List.of(
                                        FeedbackRequest.Part.builder()
                                                .text(prompt)
                                                .build()
                                ))
                                .build()
                ))
                .build();

        FeedbackResponse res = webClient.post()
                .uri(apiUrl + "?key=" + geminiApiKey)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(FeedbackResponse.class)
                .block();

        return res.getCandidates()
                .get(0)
                .getContent()
                .getParts()
                .get(0)
                .getText();
    }
}

