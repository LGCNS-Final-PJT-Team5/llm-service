package com.modive.llm.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PromptRequest {
    private String message;
}
