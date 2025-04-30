package com.modive.llm.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrivingSummaryResponse {
    private String rapidAccelerationDecelerationCountFeedback;
    private String sharpTurnCountFeedback;
    private String overspeedCountFeedback;
    private String idlingTimeMinutesFeedback;
    private String steadySpeedRatioFeedback;
    private String averageReactionTimeSecondsFeedback;
    private String laneDepartureCountFeedback;
    private String safeDistanceMaintainMinutesFeedback;
    private String totalDrivingMinutesFeedback;
    private String inactivityTimeMinutesFeedback;
}
