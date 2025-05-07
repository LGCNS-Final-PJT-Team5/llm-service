package com.modive.llm.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modive.llm.common.exception.ErrorCode;
import com.modive.llm.common.exception.ModiveException;
import com.modive.llm.domain.FeedbackType;
import com.modive.llm.dto.response.SingleDriveFeedbackResponse;
import com.modive.llm.dto.response.TotalDriveFeedbackResponse;
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

import static com.modive.llm.domain.FeedbackType.DRIVE;

@Service
@RequiredArgsConstructor
public class LLMService {

    @Value("classpath:prompts/multi-metric-feedback.txt")
    private Resource drivingSummaryTmpl;
    private final ObjectMapper objectMapper;
    private final GeminiClientService geminiClientService;
    private final ResourceLoader resourceLoader;

    // LLM 응답 테스트 용
    public String getFeedbacks(String message) {
        return geminiClientService.callGemini(message);
    }

    // 주행 후 피드백 생성 메서드
    public SingleDriveFeedbackResponse getPostFeedback(Map<String, Object> params) {

        // ① 템플릿 읽기
        String promptTemplate = readTemplate(DRIVE.getTemplatePath());

        // ② 변수 치환
        String prompt = new StringSubstitutor(params).replace(promptTemplate);

        // ③ LLM 호출
        String rawJson = geminiClientService.callGemini(prompt);
        return parseJsonToDto(rawJson, SingleDriveFeedbackResponse.class);
    }

    // 타입 별 주간 피드백 생성 메서드
    public TotalDriveFeedbackResponse getWeekFeedbackByType(FeedbackType type,
                                                            Map<String, Object> params) {
        // ① 템플릿 읽기
        String promptTemplate = readTemplate(type.getTemplatePath());

        // ② 변수 치환
        String prompt = new StringSubstitutor(params).replace(promptTemplate);

        // ③ LLM 호출
        String rawJson = geminiClientService.callGemini(prompt);
        return parseJsonToDto(rawJson, TotalDriveFeedbackResponse.class);
    }

    // 템플릿 파일 로드
    private String readTemplate(String classpath) {
        try (InputStream in = resourceLoader.getResource("classpath:" + classpath).getInputStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ModiveException(ErrorCode.TEMPLATE_LOAD_FAIL);
        }
    }

    // JSON -> DTO
    private <T> T parseJsonToDto(String json, Class<T> clazz) {
        try {
            String cleaned = json
                    .replaceAll("(?s)```json", "")
                    .replaceAll("(?s)```", "")
                    .trim();
            return objectMapper.readValue(cleaned, clazz);
        } catch (IOException e) {
            throw new ModiveException(ErrorCode.GEMINI_PARSE_ERROR);
        }
    }
}
