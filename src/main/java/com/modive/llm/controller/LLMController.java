package com.modive.llm.controller;

import com.modive.llm.domain.FeedbackType;
import com.modive.llm.dto.request.DrivingSummaryRequest;
import com.modive.llm.dto.request.PromptRequest;
import com.modive.llm.dto.request.WeekFeedbackRequest;
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

    @PostMapping("/week-feedbacks/{type}")
    public ResponseEntity<?> weekFeedback(@PathVariable FeedbackType type,
                                          @RequestBody WeekFeedbackRequest body) {
        return ResponseEntity.ok(
                llmService.getWeekFeedbackByType(type, body.getParams())
        );
    }
}
