package com.modive.llm.api.service;
import org.springframework.http.HttpStatusCode;

import com.modive.llm.common.exception.ModiveException;
import com.modive.llm.common.exception.ErrorCode;
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
                .onStatus(HttpStatusCode::isError, r ->
                        r.bodyToMono(String.class)
                                .defaultIfEmpty("")
                                .map(msg -> new ModiveException(ErrorCode.GEMINI_API_ERROR)))
                .bodyToMono(FeedbackResponse.class)
                .blockOptional()
                .orElseThrow(() ->
                        new ModiveException(ErrorCode.GEMINI_API_ERROR));

        return res.getCandidates().stream()
                .findFirst()
                .map(c -> c.getContent().getParts().get(0).getText())
                .orElseThrow(() ->
                        new ModiveException(ErrorCode.GEMINI_EMPTY_RESPONSE));
    }
}

