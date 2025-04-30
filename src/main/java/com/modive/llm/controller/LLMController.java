package com.modive.llm.controller;

import com.modive.llm.dto.DrivingSummaryRequest;
import com.modive.llm.dto.PromptRequest;
import com.modive.llm.service.LLMService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequiredArgsConstructor
@RequestMapping("/llm")
public class LLMController {

    private final LLMService llmService;

    @PostMapping("/feedbacks")
    public ResponseEntity<?> getFeedback(@RequestBody PromptRequest request) {
        return ResponseEntity.ok().body(llmService.getFeedbacks(request.getMessage()));
    }

    @PostMapping("/post-feedbacks")
    public ResponseEntity<?> getPostFeedback(@RequestBody DrivingSummaryRequest request) {
        return ResponseEntity.ok().body(llmService.getPostFeedback(request));
    }
}
