package com.modive.llm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrivingSummaryRequest {
    private int rapidAccelerationDecelerationCount;
    private int sharpTurnCount;
    private int overspeedCount;
    private int idlingTimeMinutes;
    private int steadySpeedLowRatio;
    private int steadySpeedMiddleRatio;
    private int steadySpeedHighRatio;
    private double averageReactionTimeSeconds;
    private int laneDepartureCount;
    private int safeDistanceMaintainMinutes;
    private int totalDrivingMinutes;
    private int inactivityTimeMinutes;
}
