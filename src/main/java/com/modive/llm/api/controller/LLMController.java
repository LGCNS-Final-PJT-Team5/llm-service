package com.modive.llm.api.controller;

import com.modive.llm.domain.FeedbackType;
import com.modive.llm.dto.request.SingleDriveFeedbackRequest;
import com.modive.llm.dto.request.PromptRequest;
import com.modive.llm.dto.request.TotalDriveFeedbackRequest;
import com.modive.llm.api.service.LLMService;
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
    public ResponseEntity<?> getPostFeedback(@RequestBody SingleDriveFeedbackRequest request) {
        return ResponseEntity.ok().body(llmService.getPostFeedback(request.getParams()));
    }

    @PostMapping("/week-feedbacks/{type}")
    public ResponseEntity<?> weekFeedback(@PathVariable FeedbackType type,
                                          @RequestBody TotalDriveFeedbackRequest request) {
        return ResponseEntity.ok(
                llmService.getWeekFeedbackByType(type, request.getParams())
        );
    }
}
