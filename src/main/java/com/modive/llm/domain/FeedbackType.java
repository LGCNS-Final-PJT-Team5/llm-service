package com.modive.llm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeedbackType {

    // prompts/single-drive
    DRIVE("prompts/single-drive/multi-metric-feedback.txt"),

    // prompts/total-drive
    ECO("prompts/total-drive/eco-driving-summary.txt"),
    INSURANCE("prompts/total-drive/insurance-driving-summary.txt"),
    BEGINNER("prompts/total-drive/beginner-driving-summary.txt"),
    REWARD("prompts/total-drive/reward-driving-summary.txt"),
    MAINTENANCE("prompts/total-drive/maintenance-driving-summary.txt"),
    CARBON("prompts/total-drive/carbon-driving-summary.txt"),
    DRIVESTAR("prompts/total-drive/drive-star-driving-summary.txt"),
    TECHNIQUE("prompts/total-drive/technique-driving-summary.txt");

    private final String templatePath;   // 클래스패스 상대 경로
}
