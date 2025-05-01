package com.modive.llm.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PromptTemplateService {

    private final ResourceLoader resourceLoader;

    public String load(String path, Map<String, Object> values) {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + path);
            String template = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            return new StringSubstitutor(values).replace(template);
        } catch (IOException e) {
            throw new IllegalStateException("프롬프트 템플릿 로딩 실패: " + path, e);
        }
    }
}
