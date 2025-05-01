package com.modive.llm.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PromptRequest {
    private String message;
}
