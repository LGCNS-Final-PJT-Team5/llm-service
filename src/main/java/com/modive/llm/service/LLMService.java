package com.modive.llm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modive.llm.domain.FeedbackType;
import com.modive.llm.dto.request.DrivingSummaryRequest;
import com.modive.llm.dto.response.DrivingSummaryResponse;
import com.modive.llm.dto.response.WeekFeedbackResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LLMService {

    @Value("classpath:prompts/driving-summary-feedback.txt")
    private Resource drivingSummaryTmpl;
    private final ObjectMapper objectMapper;
    private final GeminiClientService geminiClientService;
    private final ResourceLoader resourceLoader;

    // LLM 응답 테스트 용
    public String getFeedbacks(String message) {
        return geminiClientService.callGemini(message);
    }

    // 주행 후 피드백 생성 메서드
    public DrivingSummaryResponse getPostFeedback(DrivingSummaryRequest req) {
        String prompt = buildPrompt(req);
        String rawJson = geminiClientService.callGemini(prompt);
        return toDto(rawJson);
    }

    // 타입 별 주간 피드백 생성 메서드
    public WeekFeedbackResponse getWeekFeedbackByType(FeedbackType type,
                                                      Map<String, Object> params) {
        // ① 템플릿 읽기
        String promptTemplate = readTemplate(type.getTemplatePath());

        // ② 변수 치환
        String prompt = new StringSubstitutor(params).replace(promptTemplate);

        // ③ LLM 호출
        String rawJson = geminiClientService.callGemini(prompt);
        return toWeekDto(rawJson);
    }

    // 템플릿 파일 로드 재사용
    private String readTemplate(String classpath) {
        try (InputStream in = resourceLoader.getResource("classpath:" + classpath).getInputStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("프롬프트 템플릿 읽기 실패: " + classpath, e);
        }
    }

    private String buildPrompt(DrivingSummaryRequest request) {
        String template;
        try (InputStream in = drivingSummaryTmpl.getInputStream()) {
            template = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("프롬프트 템플릿 읽기 실패", e);
        }

        Map<String, Object> values = Map.ofEntries(
                Map.entry("rapidAccelerationDecelerationCount", request.getRapidAccelerationDecelerationCount()),
                Map.entry("sharpTurnCount",                    request.getSharpTurnCount()),
                Map.entry("overspeedCount",                    request.getOverspeedCount()),
                Map.entry("idlingTimeMinutes",                 request.getIdlingTimeMinutes()),
                Map.entry("steadySpeedLowRatio",               request.getSteadySpeedLowRatio()),
                Map.entry("steadySpeedMiddleRatio",            request.getSteadySpeedMiddleRatio()),
                Map.entry("steadySpeedHighRatio",              request.getSteadySpeedHighRatio()),
                Map.entry("averageReactionTimeSeconds",        request.getAverageReactionTimeSeconds()),
                Map.entry("laneDepartureCount",                request.getLaneDepartureCount()),
                Map.entry("safeDistanceMaintainMinutes",       request.getSafeDistanceMaintainMinutes()),
                Map.entry("totalDrivingMinutes",               request.getTotalDrivingMinutes()),
                Map.entry("inactivityTimeMinutes",             request.getInactivityTimeMinutes())
        );

        return new StringSubstitutor(values).replace(template);
    }


    // JSON → DTO
    private DrivingSummaryResponse toDto(String json) {
        try {
            String cleaned = json
                    .replaceAll("(?s)```json", "")
                    .replaceAll("(?s)```", "")
                    .trim();

            return objectMapper.readValue(cleaned, DrivingSummaryResponse.class);
        } catch (IOException e) {
            throw new IllegalStateException("Gemini 응답 파싱 실패", e);
        }
    }

    private WeekFeedbackResponse toWeekDto(String json) {
        try {
            String cleaned = json
                    .replaceAll("(?s)```json", "")
                    .replaceAll("(?s)```", "")
                    .trim();
            return objectMapper.readValue(cleaned, WeekFeedbackResponse.class);
        } catch (IOException e) {
            throw new IllegalStateException("Gemini Week JSON 파싱 실패", e);
        }
    }
}
