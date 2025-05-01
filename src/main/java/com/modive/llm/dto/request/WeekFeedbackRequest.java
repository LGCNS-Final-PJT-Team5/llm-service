package com.modive.llm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeekFeedbackRequest {
    private Map<String, Object> params;   // ex) { "weeklyAvgFuel": 13.5, "ecoScore": 72 }
}
