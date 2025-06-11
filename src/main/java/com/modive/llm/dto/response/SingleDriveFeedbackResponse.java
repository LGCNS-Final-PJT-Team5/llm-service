package com.modive.llm.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleDriveFeedbackResponse {
    private String rapidAccelerationDecelerationCountFeedback;
    private String sharpTurnCountFeedback;
    private String overspeedCountFeedback;
    private String idlingTimeMinutesFeedback;
    private String steadySpeedRatioFeedback;
    private String reactionDelayCountFeedback;
    private String laneDepartureCountFeedback;
    private String safeDistanceNotMaintainCountFeedback;
    private String totalDrivingMinutesFeedback;
    private String inactivityCountFeedback;
    private String totalFeedback;
}
