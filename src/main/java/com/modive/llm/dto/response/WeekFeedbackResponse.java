package com.modive.llm.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeekFeedbackResponse {
    private String title;
    private String summary;
    private List<String> advices;   // size == 3
}
