package com.modive.llm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeedbackType {
    ECO("prompts/eco-driving-summary.txt"),
    INSURANCE("prompts/insurance-driving-summary.txt"),
    BEGINNER("prompts/beginner-driving-summary.txt"),
    REWARD("prompts/reward-driving-summary.txt"),
    MAINTENANCE("prompts/maintenance-driving-summary.txt"),
    CARBON("prompts/carbon-driving-summary.txt"),
    DRIVESTAR("prompts/drive-star-driving-summary.txt"),
    TECHNIQUE("prompts/technique-driving-summary.txt");

    private final String templatePath;   // 클래스패스 상대 경로
}
