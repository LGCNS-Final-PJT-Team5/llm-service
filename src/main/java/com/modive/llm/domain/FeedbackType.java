package com.modive.llm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeedbackType {

    // prompts/single-drive
    DRIVE("prompts/single-drive/multi-metric-feedback.txt"),

    // prompts/total-drive
    ECO("prompts/total-drive/1-eco-driving-summary.txt"),
    INSURANCE("prompts/total-drive/2-insurance-driving-summary.txt"),
    BEGINNER("prompts/total-drive/3-beginner-driving-summary.txt"),
    REWARD("prompts/total-drive/4-reward-driving-summary.txt"),
    MAINTENANCE("prompts/total-drive/5-maintenance-driving-summary.txt"),
    CARBON("prompts/total-drive/6-carbon-driving-summary.txt"),
    DRIVESTAR("prompts/total-drive/drive-star-driving-summary.txt"),
    TECHNIQUE("prompts/total-drive/8-technique-driving-summary.txt");

    private final String templatePath;   // 클래스패스 상대 경로
}
